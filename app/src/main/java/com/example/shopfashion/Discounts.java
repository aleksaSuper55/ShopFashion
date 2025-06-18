package com.example.shopfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Discounts extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView discountsRecyclerView;
    DiscountAdapter discountAdapter;
    List<Discount> discountList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.discounts);

        // Initialize views
        discountsRecyclerView = findViewById(R.id.discountsRecyclerView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Проверка layout item'а RecyclerView
        View testView = LayoutInflater.from(this).inflate(R.layout.discount_item, null);
        if (testView.findViewById(R.id.discountTitleTextView) == null) {
            Log.e("LAYOUT_CHECK", "ОШИБКА: discountTitleTextView не найден в разметке");
        } else {
            Log.d("LAYOUT_CHECK", "Все элементы разметки найдены");
        }

        // Set up RecyclerView
        discountsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        discountAdapter = new DiscountAdapter(this, discountList);
        discountsRecyclerView.setAdapter(discountAdapter);

        // Проверка инициализации RecyclerView
        discountsRecyclerView.post(() -> {
            if (discountsRecyclerView.getChildCount() == 0) {
                Log.e("RV_CHECK", "RecyclerView пуст! Проверьте:");
                Log.e("RV_CHECK", "1. Данные: " + discountList.size() + " элементов");
                Log.e("RV_CHECK", "2. LayoutManager: " + (discountsRecyclerView.getLayoutManager() != null));
                Log.e("RV_CHECK", "3. Адаптер: " + (discountsRecyclerView.getAdapter() != null));
            } else {
                Log.d("RV_CHECK", "RecyclerView успешно отображает элементы");
            }
        });

        // Fetch data from Supabase
        fetchDiscountsFromSupabase();

        // Set up listeners
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent = null;
            if (itemId == R.id.shoppage) {
                intent = new Intent(Discounts.this, Shop.class);
            } else if (itemId == R.id.category) {
                intent = new Intent(Discounts.this, Category.class);
            } else if (itemId == R.id.cart) {
                intent = new Intent(Discounts.this, Cart.class);
            } else if (itemId == R.id.profile) {
                intent = new Intent(Discounts.this, Profile.class);
            } else if (itemId == R.id.sales) {
                intent = new Intent(Discounts.this, Discounts.class);
            }
            if (intent != null) {
                startActivity(intent);
            }
            return true;
        });
    }

    private void fetchDiscountsFromSupabase() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://YOUR_PROJECT_ID.supabase.co/rest/v1/Discounts?select=*";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", "your-anon-key")
                .addHeader("Authorization", "Bearer your-supabase-token")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Supabase", "Error: " + e.getMessage());
                runOnUiThread(() -> showError("Connection error"));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("Supabase", "Error: " + response.code());
                    runOnUiThread(() -> showError("Server error"));
                    return;
                }

                try {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Discount>>(){}.getType();
                    List<Discount> newDiscounts = gson.fromJson(responseData, listType);

                    runOnUiThread(() -> {
                        // Проверка полученных данных
                        Log.d("DATA_CHECK", "Получено элементов: " + newDiscounts.size());
                        if (!newDiscounts.isEmpty()) {
                            Log.d("SAMPLE_DATA", "Первый элемент: " + newDiscounts.get(0).getName());
                        }

                        discountList.clear();
                        discountList.addAll(newDiscounts);
                        discountAdapter.notifyDataSetChanged();

                        // Дополнительная проверка после обновления
                        discountsRecyclerView.post(() -> {
                            Log.d("POST_UPDATE_CHECK", "Дочерних элементов: " + discountsRecyclerView.getChildCount());
                        });

                        if (discountList.isEmpty()) {
                            showEmptyState();
                        }
                    });
                } catch (Exception e) {
                    Log.e("Supabase", "Parse error: " + e.getMessage());
                    runOnUiThread(() -> showError("Data error"));
                }
            }
        });
    }

    private void showError(String message) {
        Log.e("UI_ERROR", message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showEmptyState() {
        Log.d("UI_STATE", "Отображаем пустое состояние");
        // Реализуйте отображение пустого состояния
    }
}
