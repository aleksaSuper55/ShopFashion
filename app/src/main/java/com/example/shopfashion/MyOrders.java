package com.example.shopfashion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopfashion.Adapters.OrdersAdapter;
import com.example.shopfashion.Models.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.IOException;
import java.util.List;

public class MyOrders extends AppCompatActivity {
    Button returnToPage;
    RecyclerView ordersRecyclerView;
    private void init() {
        returnToPage = findViewById(R.id.returnToPage);
        returnToPage.setOnClickListener(view -> {
            startActivity(new Intent(this, Profile.class));
            finish();
        });
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.my_order);
        getAllOrders();
        init();

    }
    private void getAllOrders(){
        SupabaseClient supabaseClient = new SupabaseClient();
        try {
            supabaseClient.fetchAllOrders(new SupabaseClient.SBC_Callback() {
                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(()->{
                        Log.e("getAllOrders:onFailure", e.getLocalizedMessage());
                    });
                }
                @Override
                public void onResponse(String responseBody) {
                    runOnUiThread(()->{
                        Log.e("getAllOrders:onResponse", responseBody);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Order>>(){}.getType();
                        List<Order> orderList = gson.fromJson(responseBody, type);
                        OrdersAdapter ordersAdapter = new OrdersAdapter(getApplicationContext(), orderList);
                        ordersRecyclerView.setAdapter(ordersAdapter);
                        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    });
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

