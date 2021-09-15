package com.example.voda_handy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText Et_email, Et_password;
    private Button Btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


    }

    public void init() {
        Et_email = findViewById(R.id.Et_email);
        Et_password = findViewById(R.id.Et_password);
        Btn_login = findViewById(R.id.Btn_login);
    }
}