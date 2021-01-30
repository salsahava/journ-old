#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_id_ac_ui_cs_mobileprogramming_salsahava_journ_ui_RouteTimerActivity_stringArrayFromJNI(
        JNIEnv *env,
        jobject) {
    char *greetings[] = {
            "Be careful on your way!",
            "Focus on the road if you're driving",
            "Take it slow",
            "Make sure to be on time!"
    };

    jstring str;
    jobjectArray greeting = 0;
    jsize len = 4;
    int i;

    greeting = env->NewObjectArray(len, env->FindClass("java/lang/String"), 0);

    for (i = 0; i < len; i++) {
        str = env->NewStringUTF(greetings[i]);
        env->SetObjectArrayElement(greeting, i, str);
    }

    return greeting;
}

extern "C"
JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_salsahava_journ_ui_RouteTimerActivity_randomNumber(
        JNIEnv *env,
        jobject) {
    int n;
    n = rand() % 4 +1;

    return n;
}


