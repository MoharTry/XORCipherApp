package com.example.xorcipherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("xor-cipher");
    }

    private EditText inputText;
    private EditText keyText;
    private TextView resultText;
    private Button encryptButton;
    private Button decryptButton;

    private native String encryptNative(String text, String key);
    private native String decryptNative(String text, String key);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        keyText = findViewById(R.id.keyText);
        resultText = findViewById(R.id.resultText);
        encryptButton = findViewById(R.id.encryptButton);
        decryptButton = findViewById(R.id.decryptButton);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEncrypt();
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDecrypt();
            }
        });
    }

    private void handleEncrypt() {
        String text = inputText.getText().toString();
        String key = keyText.getText().toString();
        if (!text.isEmpty() && !key.isEmpty()) {
            resultText.setText(encryptNative(text, key));
        }
    }

    private void handleDecrypt() {
        String text = inputText.getText().toString();
        String key = keyText.getText().toString();
        if (!text.isEmpty() && !key.isEmpty()) {
            resultText.setText(decryptNative(text, key)); // XOR decryption is the same as encryption
        }
    }

//    private String encryptDecrypt(String text, String key, boolean isEncrypt) {
//        char[] result = new char[text.length()];
//        for (int i = 0; i < text.length(); i++) {
//            result[i] = (char) (text.charAt(i) ^ key.charAt(i % key.length()));
//        }
//        if(!isEncrypt){
//            for(int i = 0 ; i< text.length(); i++){
//                result[i] ^= key.charAt(i % key.length());
//            }
//        }
//        return new String(result);
//    }
}