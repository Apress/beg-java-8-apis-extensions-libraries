// hellojni.cpp
#include <stdio.h>
#include <jni.h>
#include "com_jdojo_jni_HelloJNI.h"

JNIEXPORT void JNICALL Java_com_jdojo_jni_HelloJNI_hello(JNIEnv *env, jobject obj) {
    printf("Hello JNI\n");
    return;
}
