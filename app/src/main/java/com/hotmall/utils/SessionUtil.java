package com.hotmall.utils;

import android.text.TextUtils;

import com.hotmall.common.UtilCtInit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhsheng on 2016/3/17.
 */
public class SessionUtil {
    public static Map<String, String> getHttpHeaders() {
        HashMap<String, String> sessionMap = new HashMap<>();
        sessionMap.put("LOC", DeviceUtil.getLanguageInfo(UtilCtInit.getContext()));
        sessionMap.put("USER_ID", SharedPre.getUserId());
        sessionMap.put("DEVICE_ID", new DeviceUuidFactory(UtilCtInit.getContext()).getDeviceId());
        sessionMap.put("TOKEN", TextUtils.isEmpty(SharedPre.getUserToken()) ? "_android" : SharedPre.getUserToken());
        sessionMap.put("VERSION", "9");
        return sessionMap;
    }
}
