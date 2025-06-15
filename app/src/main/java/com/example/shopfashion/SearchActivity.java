package com.example.shopfashion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

 public class SearchActivity extends AppCompatActivity {
        private List<String> popularSearchTerms;
        private RecyclerView searchTermRecyclerView;
        private EditText searchEditText;
        private ImageView clearSearchButton;
        private SearchTermAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.search);
            // Инициализация элементов UI
            initViews();
            // Инициализация RecyclerView и данных
            initRecyclerView();
            // Настройка обработчиков
            setupListeners();
        }
        private void initViews() {
            searchEditText = findViewById(R.id.searchEditText);
            clearSearchButton = findViewById(R.id.clearSearchButton);
            searchTermRecyclerView = findViewById(R.id.popularSearchTermsRecyclerView);
        }
        private void initRecyclerView() {
            // Заполнение списка популярных запросов
            popularSearchTerms = new ArrayList<>();
            popularSearchTerms.add("Dress");
            popularSearchTerms.add("Suit");
            popularSearchTerms.add("Accessories");
            popularSearchTerms.add("T-shirt");
            // Настройка RecyclerView
            searchTermRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            // Создание адаптера
            adapter = new SearchTermAdapter(popularSearchTerms, searchTerm -> {
                searchEditText.setText(searchTerm);
                performSearch(searchTerm);
            });
            searchTermRecyclerView.setAdapter(adapter);
            // Добавление разделителя между элементами
            searchTermRecyclerView.addItemDecoration(
                    new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            );
        }
        private void setupListeners() {
            // Обработчик очистки текста
            clearSearchButton.setOnClickListener(v -> {
                searchEditText.setText("");
                clearSearchButton.setVisibility(View.GONE);
                adapter.updateList(popularSearchTerms); // Восстанавливаем полный список
            });

            // Отслеживание ввода текста
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    clearSearchButton.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                    performSearch(s.toString()); // Фильтрация в реальном времени
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
            // Обработка нажатия Enter
            searchEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(searchEditText.getText().toString());
                    return true;
                }
                return false;
            });
            // Первоначально скрываем кнопку очистки
            clearSearchButton.setVisibility(View.GONE);
        }
        private void performSearch(String query) {
            if (query.isEmpty()) {
                adapter.updateList(popularSearchTerms);
                return;
            }
            List<String> filteredList = new ArrayList<>();
            for (String term : popularSearchTerms) {
                if (term.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(term);
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            }
            adapter.updateList(filteredList);
        }
    }

