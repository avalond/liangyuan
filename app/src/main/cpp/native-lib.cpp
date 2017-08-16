#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_xiaan_liangyuan_liangyuanapp_ui_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "hello liangyuan app demo ";
    return env->NewStringUTF(hello.c_str());
}
