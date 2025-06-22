package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfashion.Adapters.AccessoriesAdapter;
import com.example.shopfashion.Adapters.ManAdapter;
import com.example.shopfashion.Models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Accessories extends AppCompatActivity {
    Button returnycategory;
    RecyclerView recyclerAccessories;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.accessories);
        getAccessories();
        recyclerAccessories = findViewById(R.id.recyclerAccessories);
        returnycategory = findViewById(R.id.returnycategory);
        returnycategory.setOnClickListener(view -> {
            startActivity(new Intent(this, Category.class));
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getAccessories() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchAccessories(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAccessories:onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAccessories:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Product>>() {
                    }.getType();
                    List<Product> productList = gson.fromJson(responseBody, type);
                    AccessoriesAdapter accessoriesAdapter = new AccessoriesAdapter(getApplicationContext(), productList);
                    recyclerAccessories.setAdapter(accessoriesAdapter);
                    recyclerAccessories.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
