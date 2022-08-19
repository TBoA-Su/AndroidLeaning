#include <jni.h>
#include <string>
#include <android/log.h>
#include "md5.h"

using namespace std;
// 额外附加的字符串
static char *EXTRA_SIGNATURE = "DARREN";
// 校验签名
static int is_verify = 0;
static char *PACKAGE_NAME = "com.tboa.learning001";
static char *APP_SIGNATURE = "308202e4308201cc020101300d06092a864886f70d010105050030373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b30090603550406130255533020170d3231313130393131323830305a180f32303531313130323131323830305a30373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b300906035504061302555330820122300d06092a864886f70d01010105000382010f003082010a0282010100823cfda87345418dad1629c1de6e029eac87422b670bee5a0efa8fbae53fe604ee5cb9d7ef542475dee0c51eb99f2b1b36d4c1529a3822cbd3fb00abfc1d8163363e546c801111f8d45a8c3e83f97104a6aff9ecfe9a3a36d5261411e5cd34f54b046491c98c1b507078dc418976f72bcc93185bb3d2ba9c878fc65bd9505091df61dc0942f54bdcb0bda0fa89fd0406f50111e6dd2f0e2678d7b8b77ef9100bf5c57f42231e3d9cf7db81de70db7bc7282090306160da7c7324dc727a353fe9d6deaa9881f2baa8b238a30e0afd0858d44f7caec421a84effc802ab9e0905d3e666588e6e9dd6197c3bf36de654e06c27682bd93b19b6558b5fd4728c5b405f0203010001300d06092a864886f70d01010505000382010100083e8499aa717be38ff025e800682eeb3a1f101b690b7c27a04bab8a949588d5fea0847822690361914bba43fd3cb01f255b6a8da78f6e36852a50e6ca63852bd713a52406ba061bc85d6333ccba308fb1a9afe2d12c4774b1ffccb160cf65cd17ad71f8e3c64f8107bece604c4bc06f8f232c77885fa1155f673f8824132181580ea309ef314c6e2b51ef4f2d3222d3888802f95dfbf7d25e12b12c6963ab2d55200bfb1c540d0df245654057f3284782aba544002765612faa7d8d7cfa4a7a9827991afbdbb97abaa92a3692b89f0cbc99618cca49cae06e58813a0cef31435c429cbe2cc0c1d350119d282ecdca185f6f9a249a7c46ed374e1b62128513cc";

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tboa_learning001_SignatureUtils_signatureParams(JNIEnv *env, jclass clazz,
                                                         jstring params_) {
    // TODO: implement signatureParams()
    if (is_verify == 0) {
        return env->NewStringUTF("error_signature");
    } else {
        const char *params = env->GetStringUTFChars(params_, 0);

        // MD5签名，加点料
        // 1.字符串前面加点料
        string signature_str(params);
        signature_str.insert(0, EXTRA_SIGNATURE);

        // 2.后面去掉两位
        signature_str.substr(0, signature_str.length() - 2);

        // 3.MD5 去加密
        MD5 *md5 = new MD5();
        md5->update(signature_str.c_str(), signature_str.length());
        md5->finalize();
        string string1;
        string1 = md5->hexdigest();

        // 释放资源
        env->ReleaseStringUTFChars(params_, params);

        return env->NewStringUTF(string1.c_str());
    }
}

// C 调用 Java 代码 JNI 基础
extern "C"
JNIEXPORT void JNICALL
Java_com_tboa_learning001_SignatureUtils_signatureVerify(JNIEnv *env, jclass clazz,
                                                         jobject context) {
    // TODO: implement signatureVerify()
    // 1.获取包名
    jclass j_clz = env->GetObjectClass(context);
    jmethodID j_mid = env->GetMethodID(j_clz, "getPackageName", "()Ljava/lang/String;");
    jstring j_package_name = (jstring) env->CallObjectMethod(context, j_mid);
    // 2.比对包名是否一样
    const char *c_package_name = env->GetStringUTFChars(j_package_name, NULL);
    __android_log_print(ANDROID_LOG_ERROR, "JNI_TAG", "包名一致: %s", c_package_name);
    if (strcmp(c_package_name, PACKAGE_NAME) != 0) {
        return;
    }
    // 3.获取签名
    // 3.1获取PackageName
    j_mid = env->GetMethodID(j_clz, "getPackageManager",
                             "()Landroid/content/pm/PackageManager;");
    jobject pack_manage = env->CallObjectMethod(context, j_mid);
    // 3.2获取PackageInfo
    j_clz = env->GetObjectClass(pack_manage);
    j_mid = env->GetMethodID(j_clz, "getPackageInfo",
                             "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jobject pack_info = env->CallObjectMethod(pack_manage, j_mid, j_package_name, 64);
    // 3.3获取signature数组
    j_clz = env->GetObjectClass(pack_info);
    jfieldID j_fid = env->GetFieldID(j_clz, "signatures", "[Landroid/content/pm/Signature;");
    jobjectArray signatures = (jobjectArray) env->GetObjectField(pack_info, j_fid);
    // 3.4获取signature[0]
    jobject signatures_first = env->GetObjectArrayElement(signatures, 0);
    // 3.5调用signature[0].toCharString()
    j_clz = env->GetObjectClass(signatures_first);
    j_mid = env->GetMethodID(j_clz, "toCharsString", "()Ljava/lang/String;");
    jstring j_signature_str = (jstring) env->CallObjectMethod(signatures_first, j_mid);
    const char *c_signature_str = env->GetStringUTFChars(j_signature_str, NULL);
    // 4.比对签名是否一样
    __android_log_print(ANDROID_LOG_ERROR, "JNI_TAG", "签名一致: %s", c_signature_str);
    if (strcmp(c_signature_str, APP_SIGNATURE) != 0) {
        return;
    }
    // 签名认证成功
    is_verify = 1;
}