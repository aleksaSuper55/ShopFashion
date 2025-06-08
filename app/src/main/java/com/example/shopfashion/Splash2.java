package com.example.shopfashion;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class Splash2 extends AppCompatActivity {
    public Button nxt2;
    public Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);

        Button nextButton = findViewById(R.id.get_started2);

        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("splashShown", true);
                    editor.apply();
                    Intent intent = new Intent(Splash2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            nxt2 = findViewById(R.id.nxt2);
            nxt2.setOnClickListener(v -> startActivity(new Intent(this, SignIn.class)));
            bb = findViewById(R.id.bb);
            bb.setOnClickListener(v -> startActivity(new Intent(this, Splash1.class)));

        }
    }}