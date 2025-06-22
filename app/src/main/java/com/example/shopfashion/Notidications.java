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
import com.example.shopfashion.Adapters.NotificationAdapter;
import com.example.shopfashion.Models.NotificationM;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Notidications extends AppCompatActivity {
    private Button returnToProfile;
    RecyclerView recyclerNotifications;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.notific);
        getAllNotifications();
        returnToProfile = findViewById(R.id.returnyprofile);
        returnToProfile.setOnClickListener(view -> {
            startActivity(new Intent(this, Profile.class));
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerNotifications = findViewById(R.id.recyclerNotifications);
    }
    private void getAllNotifications(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchAllNotifications(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllNotifications:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAllNotifications:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<NotificationM>>() {
                    }.getType();
                    List<NotificationM> notificationList = gson.fromJson(responseBody, type);
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getApplicationContext(), notificationList);
                    recyclerNotifications.setAdapter(notificationAdapter);
                    recyclerNotifications.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
