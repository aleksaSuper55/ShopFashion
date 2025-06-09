package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Shop extends AppCompatActivity {
    ImageButton menu, search;
    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.shoppage) {
                    Intent intent = new Intent(Shop.this, Shop.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.category) {
                    Intent intent = new Intent(Shop.this, Category.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.cart) {
                    Intent intent = new Intent(Shop.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.profile) {
                    Intent intent = new Intent(Shop.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.sales) {
                    Intent intent = new Intent(Shop.this, Discounts.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
