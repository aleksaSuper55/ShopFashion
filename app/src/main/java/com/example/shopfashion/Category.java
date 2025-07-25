package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Category extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button woman, man, accessories;
    ImageButton search;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        woman = findViewById(R.id.woman);
        woman.setOnClickListener(v -> startActivity(new Intent(this, Woman.class)));
        man = findViewById(R.id.man);
        man.setOnClickListener(v -> startActivity(new Intent(this, Man.class)));
        accessories = findViewById(R.id.accessories);
        accessories.setOnClickListener(v -> startActivity(new Intent(this, Accessories.class)));
        search = findViewById(R.id.search);
        search.setOnClickListener(view -> {
            startActivity(new Intent(this, SearchActivity.class));
            finish();
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.shoppage) {
                    Intent intent = new Intent(Category.this, Shop.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.category) {
                    Intent intent = new Intent(Category.this, Category.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.cart) {
                    Intent intent = new Intent(Category.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.profile) {
                    Intent intent = new Intent(Category.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.sales) {
                    Intent intent = new Intent(Category.this, Discounts.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }}
