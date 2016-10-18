// jniarraytest.cpp
#include <jni.h>
#include <cstring>
#include "com_jdojo_jni_JNIArrayTest.h"

JNIEXPORT jint JNICALL Java_com_jdojo_jni_JNIArrayTest_sum
(JNIEnv *env, jobject obj, jintArray num) {
    jint sum = 0;
    const jsize count = env->GetArrayLength(num);

    jboolean isCopy;
    jint *intNum = env->GetIntArrayElements(num, &isCopy);

    for (jsize i = 0; i < count; i++) {
        sum += intNum[i];
    }

    // Release the intNum buffer without copying back any changes made to the array elements 
    env->ReleaseIntArrayElements(num, intNum, JNI_ABORT);

    return sum;
}

JNIEXPORT jstring JNICALL Java_com_jdojo_jni_JNIArrayTest_concat
(JNIEnv *env, jobject obj, jobjectArray strArray) {
    const int MAX_LENGTH = 500;
    char dest[MAX_LENGTH];

    for (int i = 0; i < MAX_LENGTH; i++) {
        dest[i] = (char)NULL;
    }

    const jsize count = env->GetArrayLength(strArray);

    for (jsize i = 0; i < count; i++) {
        // Get the string object from the array 
        jstring strElement =
                (jstring) env->GetObjectArrayElement(strArray, i);
        const char *tempStr = env->GetStringUTFChars(strElement, NULL);

        if (tempStr == NULL) {
            printf("Could not convert Java string to UTF-8 string.\n");
            return NULL;
        }

        // Concatenate tempStr to dest 
        strcat(dest, tempStr);

        // Release the memory used by tempStr 
        env->ReleaseStringUTFChars(strElement, tempStr);

        // Delete the local reference of jstring 
        env->DeleteLocalRef(strElement);
    }

    jstring returnStr = env->NewStringUTF(dest);
    return returnStr;
}

JNIEXPORT jintArray JNICALL Java_com_jdojo_jni_JNIArrayTest_increment
(JNIEnv *env, jobject obj, jintArray num, jint incrementBy) {

    const jsize count = env->GetArrayLength(num);

    jboolean isCopy;
    jint *intNum = env->GetIntArrayElements(num, &isCopy);

    jintArray modifiedNumArray = env->NewIntArray(count);
    jboolean isNewArrayCopy;
    jint *modifiedNumElements =
            env->GetIntArrayElements(modifiedNumArray, &isNewArrayCopy);

    for (jint i = 0; i < count; i++) {
        modifiedNumElements[i] = intNum[i] + incrementBy;
    }

    if (isCopy == JNI_TRUE) {
        env -> ReleaseIntArrayElements(num, intNum, JNI_COMMIT);
    }

    if (isNewArrayCopy == JNI_TRUE) {
        env -> ReleaseIntArrayElements(modifiedNumArray,
                modifiedNumElements,
                JNI_COMMIT);
    }

    return modifiedNumArray;
}
