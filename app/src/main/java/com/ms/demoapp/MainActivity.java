package com.ms.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button BtnSignIn;
    private Button BtnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnSkip = findViewById(R.id.button_skip);
        BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNearme();
            };
        });

        BtnSignIn = findViewById(R.id.button_next);
        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSignIn();
            };
        });
        Log.d("MainActivity", "***********************Hello World");
    }

    private void moveToSignIn() {
        Intent intent = new Intent(MainActivity.this, IntoActivity.class);
        startActivity(intent);

    }

    private void moveToNearme() {
        Intent intent = new Intent(MainActivity.this, NearmeActivity.class);
        startActivity(intent);

    }

}
