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

public class Error extends AppCompatActivity {
    private ImageButton returnToCart;
    private Button returnToShop;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.error);
        returnToCart = findViewById(R.id.cartt);
        returnToShop = findViewById(R.id.btn);
        returnToCart.setOnClickListener(view -> {
            startActivity(new Intent(this, Cart.class));
            finish();
        });
        returnToShop.setOnClickListener(view -> {
            startActivity(new Intent(this, Shop.class));
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }}

