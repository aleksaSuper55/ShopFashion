package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopfashion.Adapters.DiscountAdapter;
import com.example.shopfashion.Models.Discount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
public class Discounts extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView discountsRec;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.discounts);
        getAllDiscounts();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        discountsRec = findViewById(R.id.discountsRec);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.shoppage) {
                    Intent intent = new Intent(Discounts.this, Shop.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.category) {
                    Intent intent = new Intent(Discounts.this, Category.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.cart) {
                    Intent intent = new Intent(Discounts.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.profile) {
                    Intent intent = new Intent(Discounts.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.sales) {
                    Intent intent = new Intent(Discounts.this, Discounts.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
    private void getAllDiscounts(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchAllDiscounts(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllDiscounts:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAllDiscounts:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Discount>>() {
                    }.getType();
                    List<Discount> discountList = gson.fromJson(responseBody, type);
                    DiscountAdapter discountAdapter = new DiscountAdapter(getApplicationContext(), discountList);
                    discountsRec.setAdapter(discountAdapter);
                    discountsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
