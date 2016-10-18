// jnistringtest.cpp
#include <stdio.h>
#include <jni.h>
#include "com_jdojo_jni_JNIStringTest.h"

JNIEXPORT void JNICALL Java_com_jdojo_jni_JNIStringTest_printMsg
(JNIEnv *env, jobject obj, jstring msg) {
    const char *utfMsg;
    jboolean *iscopy = NULL;

    // Get the UTF string 
    utfMsg = env->GetStringUTFChars(msg, iscopy);
    if (utfMsg == NULL) {
        printf("Could not convert Java string to UTF-8 string.\n");
        return;
    }

    // Print the message on the standard output 
    printf("%s\n", utfMsg);

    // Release the memory 
    env->ReleaseStringUTFChars(msg, utfMsg);
}

JNIEXPORT jstring JNICALL Java_com_jdojo_jni_JNIStringTest_getMsg
(JNIEnv *env, jobject obj) {
    const char *utfMsg = "Hello from JNI to Java";
    jstring javaString = env->NewStringUTF(utfMsg);
    return javaString;
}
