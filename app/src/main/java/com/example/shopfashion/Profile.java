package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;
import com.example.shopfashion.Models.Users;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Profile extends AppCompatActivity {
    public ImageButton openNotific;
    TextView myCart, myAddress, cards, history, SingOut;
    TextView name;
    ImageView banner;
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
        name = findViewById(R.id.name);
        banner = findViewById(R.id.banner);
        SingOut = findViewById(R.id.SingOut);
        myCart = findViewById(R.id.Orders);
        myCart.setOnClickListener(view -> {
            startActivity(new Intent(this, Cart.class));
            finish();
        });
        myAddress = findViewById(R.id.Adress);
        getCurrentUser();
        SingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
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
                    return true;
                } else if (itemId == R.id.cart) {
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
    }
    private void logoutUser(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.logout(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getCurrentUser:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getCurrentUser:onResponse", responseBody);
                    startActivity(new Intent(getApplicationContext(), Login.class));
                });
            }
        });
    }
        private void getCurrentUser(){
            SupabaseClient supabaseClient = new SupabaseClient();
            supabaseClient.FetchCurrentUser(new SupabaseClient.SBC_Callback() {
                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(() -> {
                        Log.e("getCurrentUser:onFailure", e.getLocalizedMessage());
                    });
                }
                @Override
                public void onResponse(String responseBody) {
                    runOnUiThread(() -> {
                        Log.e("getCurrentUser:onResponse", responseBody);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Users>>() {}.getType();
                        List<Users> userlist = gson.fromJson(responseBody, type);
                        String url = "https://hnjbqkkfnxbuanxqawgp.supabase.co/storage/v1/object/public/avatars/";
                        if (userlist != null && !userlist.isEmpty()){
                            String loggedInUserId = DataBinding.getUuidUser();
                            Users user = null;
                            for (Users u : userlist) {
                                if (u.getId().equals(loggedInUserId)) {
                                    user = u;
                                    break;
                                }
                            }
                            String getav = user.getAvatar_url();
                            Glide.with(Profile.this)
                                    .load(url + getav)
                                    .placeholder(R.drawable.proff)
                                    .error(R.drawable.proff)
                                    .into(banner);
                            name.setText(user.getUsername());
                        }
                    });
                }
            });
        }

}
