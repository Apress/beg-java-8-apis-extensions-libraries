// jnijavaobjectaccesstest.cpp
#include <stdio.h>
#include <jni.h>
#include "com_jdojo_jni_JNIJavaObjectAccessTest.h"

JNIEXPORT void JNICALL Java_com_jdojo_jni_JNIJavaObjectAccessTest_callBack
(JNIEnv *env, jobject obj) {
    jclass cls;

    // Get the class reference for the object 
    cls = env->GetObjectClass(obj);
    if (cls == NULL) {
        return;
    }

    // Access the fields 
    jfieldID numFieldId = env->GetFieldID(cls, "num", "I");
    jfieldID countFieldId = env->GetStaticFieldID(cls, "count", "I");

    jint numValue = env->GetIntField(obj, numFieldId);
    jint countValue = env->GetStaticIntField(cls, countFieldId);

    numValue = numValue + 1;
    countValue = countValue + 1;

    env->SetIntField(obj, numFieldId, numValue);
    env->SetStaticIntField(cls, countFieldId, countValue);

    // Call the instance method 
    jmethodID instanceMethodID = env->GetMethodID(cls,
            "objectCallBack",
            "()V");
    if (instanceMethodID != 0) {
        env->CallVoidMethod(obj, instanceMethodID);
    }

    // Call the static method 
    jmethodID staticMethodID = env->GetStaticMethodID(cls,
            "classCallBack",
            "()V");
    if (staticMethodID != 0) {
        env->CallStaticVoidMethod(cls, staticMethodID);
    }

    return;
}