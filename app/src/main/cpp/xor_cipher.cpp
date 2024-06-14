#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_xorcipherapp_MainActivity_encrypt(JNIEnv* env, jobject, jstring input, jstring key) {
    const char* inputChars = env->GetStringUTFChars(input, 0);
    const char* keyChars = env->GetStringUTFChars(key, 0);

    std::string inputStr(inputChars);
    std::string keyStr(keyChars);
    std::string result = inputStr;

    for (size_t i = 0; i < inputStr.length(); ++i) {
        result[i] = inputStr[i] ^ keyStr[i % keyStr.length()];
    }

    env->ReleaseStringUTFChars(input, inputChars);
    env->ReleaseStringUTFChars(key, keyChars);

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_xorcipherapp_MainActivity_decrypt(JNIEnv* env, jobject, jstring input, jstring key) {
    return Java_com_example_xorcipherapp_MainActivity_encrypt(env, nullptr, input, key);
}
