package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    public ImageButton openNotific;
    TextView aboutMe, myCart, myAddress, cards, history;
    public BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        aboutMe = findViewById(R.id.AboutMe);
//        openNotific.setOnClickListener(view -> {
//            startActivity(new Intent(this, Notidications.class));
//            finish();
//        });
        myCart = findViewById(R.id.Orders);
        myCart.setOnClickListener(view -> {
            startActivity(new Intent(this, Cart.class));
            finish();
        });
        myAddress = findViewById(R.id.Adress);
        myAddress.setOnClickListener(view -> {
            startActivity(new Intent(this, NewAddress.class));
            finish();
        });
        cards = findViewById(R.id.Cards);
        cards.setOnClickListener(view -> {
            startActivity(new Intent(this, New_card.class));
            finish();
        });
        history = findViewById(R.id.History);
        history.setOnClickListener(view -> {
            startActivity(new Intent(this, MyOrders.class));
            finish();
        });
        openNotific = findViewById(R.id.sear);
        openNotific.setOnClickListener(view -> {
            startActivity(new Intent(this, Notidications.class));
            finish();
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.shoppage) {
                    Intent intent = new Intent(Profile.this, Shop.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.category) {
                    Intent intent = new Intent(Profile.this, Category.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.cart) {
                    Intent intent = new Intent(Profile.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.profile) {
                    Intent intent = new Intent(Profile.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.sales) {
                    Intent intent = new Intent(Profile.this, Discounts.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
}}
