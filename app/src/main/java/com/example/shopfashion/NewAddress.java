package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewAddress extends AppCompatActivity {
    private ImageButton returnToCart, search;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.newaddress);
        search = findViewById(R.id.search);
        search.setOnClickListener(view -> {
            startActivity(new Intent(this, SearchActivity.class));
            finish();
        });
        returnToCart = findViewById(R.id.cartt);
        returnToCart.setOnClickListener(view -> {
            startActivity(new Intent(this, Cart.class));
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
}}
