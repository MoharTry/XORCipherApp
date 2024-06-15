#include <jni.h>
#include <string>

// Encryption function
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_xorcipher_MainActivity_encryptNative(JNIEnv *env, jobject, jstring text, jstring key) {
    const char *textChars = env->GetStringUTFChars(text, nullptr);
    const char *keyChars = env->GetStringUTFChars(key, nullptr);

    if (!textChars || !keyChars) {
        return nullptr;
    }

    std::string result;
    size_t textLength = strlen(textChars);
    size_t keyLength = strlen(keyChars);

    for (size_t i = 0; i < textLength; ++i) {
        result += textChars[i] ^ keyChars[i % keyLength];
    }

    env->ReleaseStringUTFChars(text, textChars);
    env->ReleaseStringUTFChars(key, keyChars);

    return env->NewStringUTF(result.c_str());
}

// Decryption function
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_xorcipher_MainActivity_decryptNative(JNIEnv *env, jobject, jstring text, jstring key) {
    // XOR decryption is the same as encryption
    return Java_com_example_xorcipher_MainActivity_encryptNative(env, nullptr, text, key);
}