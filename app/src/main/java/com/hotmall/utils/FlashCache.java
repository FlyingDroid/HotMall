package com.hotmall.utils;

import com.hotmall.common.ConstantKey;
import com.hotmall.library.Remember;

/**
 * Created by zhsheng on 2016/3/17.
 */
public class FlashCache extends Remember {
    /**
     * 存储用户ID
     */
    public static void setUserId(String userId) {
        putString(ConstantKey.USER_ID, userId);
    }

    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return getString(ConstantKey.USER_ID, "");
    }
}
