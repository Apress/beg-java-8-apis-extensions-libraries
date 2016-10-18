// createjvm.cpp
#include <jni.h>
#include <iostream>
#include <string>

int main(int argc, char **argv) {   
    std::string classpath("");
    
    if (argc < 2) {
        std::cout << "You did not pass the classpath."
                  << " Using the current directory as the classpath.\n";
        classpath = ".";
    }
    else {
        classpath = argv[1];
    }
    
    std::string classpathOption("-Djava.class.path=");
    
    classpathOption = classpathOption + classpath;
    
    // Pass the classpath as an argument to the JVM 
    const jint MAX_OPTIONS = 1;
    JavaVMOption options[MAX_OPTIONS];
    options[0].optionString = (char *)(classpathOption.c_str());;
    
    // Prepare the JVM initial arguments 
    JavaVMInitArgs vm_args;
    vm_args.version = JNI_VERSION_1_2;
    vm_args.nOptions = MAX_OPTIONS;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = true;

    // Create the JVM 
    JavaVM *jvm;
    JNIEnv *env;
    long status = JNI_CreateJavaVM(&jvm, (void**) &env, &vm_args);
    if (status == JNI_ERR) {
        std::cout << "Could not create VM. Exiting application...\n";
        return 1;
    }

    const char *className = "com/jdojo/jni/EmbeddedJVMJNI";
    jclass cls = env->FindClass(className);
    if (cls == NULL) {
        // Print exception stack trace and destroy the JVM 
        env->ExceptionDescribe();
        jvm->DestroyJavaVM();
        return 1;
    }

    if (cls != NULL) {
        jmethodID mid = env->GetStaticMethodID(cls, "printMsg",
                "(Ljava/lang/String;)V");
        if (mid != NULL) {
            jstring m = env->NewStringUTF("Hello from C++...\n");
            env->CallStaticVoidMethod(cls, mid, m);
            if (env->ExceptionCheck()) {
                env->ExceptionDescribe();
                env->ExceptionClear();
            }
        }
    }

    // Destroy JVM 
    jvm->DestroyJavaVM();
    return 0;
}
