package com.tboa.learning001;

import android.content.Context;

public class SignatureUtils {
    // Used to load the 'learning001' library on application startup.
    static {
        System.loadLibrary("learning001");
    }

    /**
     * native 方法签名常数 方法是不能被混淆的
     *
     * @param params
     * @return
     */
    public static native String signatureParams(String params);

    /**
     * 校验签名，只允许自己的 APP可以使用这个 so 文件
     *
     * @param context
     * @return
     */
    public static native void signatureVerify(Context context);
}
