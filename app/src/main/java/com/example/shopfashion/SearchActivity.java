package com.example.shopfashion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

 public class SearchActivity extends AppCompatActivity {
     private ImageButton returnButton;
     private EditText searchEditText;
     private ImageView clearSearchButton;
     private TextView SearchTermsTextView;
     private RecyclerView SearchTermsRecyclerView;

     private SearchTermAdapter adapter;
     private List<String> SearchTermList = new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.search);

         returnButton = findViewById(R.id.returnyprofile);
         searchEditText = findViewById(R.id.searchEditText);
         clearSearchButton = findViewById(R.id.clearSearchButton);
         SearchTermsTextView = findViewById(R.id.popularSearchTermsTextView);
         SearchTermsRecyclerView = findViewById(R.id.popularSearchTermsRecyclerView);

         SearchTermsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         populatePopularSearchTerms();
         adapter = new SearchTermAdapter(this, SearchTermList);
         SearchTermsRecyclerView.setAdapter(adapter);

         returnButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         searchEditText.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if (s.length() > 0) {
                     clearSearchButton.setVisibility(View.VISIBLE);
                 } else {
                     clearSearchButton.setVisibility(View.GONE);
                 }
             }
             @Override
             public void afterTextChanged(Editable s) {
             }
         });
         clearSearchButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 searchEditText.setText(""); // Clear the text
             }
         });
     }
     private void populatePopularSearchTerms() {
         SearchTermList.add("Dresses");
         SearchTermList.add("Suit");
         SearchTermList.add("Accessories");
     }
 }

