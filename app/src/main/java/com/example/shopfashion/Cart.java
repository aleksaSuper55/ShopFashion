package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Cart extends AppCompatActivity {

    private Button returnToPage;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.my_cart);
        returnToPage = findViewById(R.id.returnToPage);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        returnToPage.setOnClickListener(view -> {
            startActivity(new Intent(this, Profile.class));
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent = null;
            if (itemId == R.id.shoppage) {
                intent = new Intent(Cart.this, Shop.class);
            } else if (itemId == R.id.category) {
                intent = new Intent(Cart.this, Category.class);
            } else if (itemId == R.id.cart) {
                intent = new Intent(Cart.this, Cart.class);
            } else if (itemId == R.id.profile) {
                intent = new Intent(Cart.this, Profile.class);
            } else if (itemId == R.id.sales) {
                intent = new Intent(Cart.this, Discounts.class);
            }
            if (intent != null) {
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}