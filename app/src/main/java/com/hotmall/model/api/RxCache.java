package com.hotmall.model.api;

import com.bluelinelabs.logansquare.LoganSquare;
import com.hotmall.utils.StorageUtil;

import java.io.FilterInputStream;
import java.io.IOException;

import okhttp3.internal.DiskLruCache;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okio.BufferedSource;
import okio.Okio;
import rx.Observable;
import rx.Subscriber;


public class RxCache {
    public static int CACHE_MAX_SIZE = 10 * 1024 * 1024;

    static <T> Observable<T> getCache(String key, Class<T> classType) {

        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    FilterInputStream stream = getFromCache(key);
                    if (stream == null) {
                        subscriber.onError(new Throwable("no cache"));
                    } else {
                        subscriber.onNext(LoganSquare.parse(getFromCache(key), classType));
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    static FilterInputStream getFromCache(String url) throws Exception {
        DiskLruCache cache = DiskLruCache.create(FileSystem.SYSTEM, StorageUtil.getCacheDir(), 201105, 2, CACHE_MAX_SIZE);
        cache.flush();
        String key = Util.md5Hex(url);
        final DiskLruCache.Snapshot snapshot;
        try {
            snapshot = cache.get(key);
            if (snapshot == null) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        okio.Source source = snapshot.getSource(1);
        BufferedSource metadata = Okio.buffer(source);
        return new FilterInputStream(metadata.inputStream()) {
            @Override
            public void close() throws IOException {
                snapshot.close();
                super.close();
            }
        };
    }
}
