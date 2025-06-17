package com.example.shopfashion;

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
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.my_order);
        getAllOrders();
        init();
        // TODO
        DataBinding.saveBearerToken("Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6ImFXQjZGY2cyb1N6c2x6bjQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2huamJxa2tmbnhidWFueHFhd2dwLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzUwMDYzMTY2LCJpYXQiOjE3NTAwNTk1NjYsImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfdmVyaWZpZWQiOmZhbHNlLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUifSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc1MDA1OTU2Nn1dLCJzZXNzaW9uX2lkIjoiYTEyYTU4ZGUtZDVlYi00MTRlLWFmNTYtYmM0NmM4YTA5OTEwIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.ZpAkP6OfvVkGIEFDr2lXgne__JFtvdgCF-sb6BJljaE");
        // TODO
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

