package com.example.shopfashion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SearchTermAdapter extends RecyclerView.Adapter<SearchTermAdapter.ViewHolder> {
    private Context context;
    private List<String> searchTerms;

    public SearchTermAdapter(Context context, List<String> searchTerms) {
        this.context = context;
        this.searchTerms = searchTerms;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String searchTerm = searchTerms.get(position);
        holder.textView.setText(searchTerm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCategory(searchTerm);
            }
        });
    }
    @Override
    public int getItemCount() {
        return searchTerms.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
    private void navigateToCategory(String searchTerm) {
        Intent intent = null;
        switch (searchTerm) {
            case "Dresses":
                intent = new Intent(context, Woman.class);
                break;
            case "Suit":
                intent = new Intent(context, Man.class);
                break;
            case "Accessories":
                intent = new Intent(context, Accessories.class);
                break;
            // Add more cases for other categories as needed

            default:
                // Handle unknown category (optional)
                // Could open a generic search results activity
                break;
        }
        if (intent != null) {
            context.startActivity(intent);
        }
    }
}

