package com.example.xorcipherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("xor_cipher");
    }

    private native String encrypt(String input, String key);
    private native String decrypt(String input, String key);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputText = findViewById(R.id.inputText);
        EditText keyText = findViewById(R.id.keyText);
        TextView resultText = findViewById(R.id.keyText);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                String key = keyText.getText().toString();
                String encrypted = encrypt(input, key);
                resultText.setText(encrypted);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                String key = keyText.getText().toString();
                String decrypted = decrypt(input, key);
                resultText.setText(decrypted);
            }
        });
    }
}
