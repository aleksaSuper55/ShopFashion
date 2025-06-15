package com.example.shopfashion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class SearchTermAdapter extends RecyclerView.Adapter<SearchTermAdapter.ViewHolder> {
    private List<String> searchTerms;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String searchTerm);
    }
    public SearchTermAdapter(List<String> searchTerms, OnItemClickListener listener) {
        this.searchTerms = searchTerms;
        this.listener = listener;
    }
    public void updateList(List<String> newList) {
        searchTerms = new ArrayList<>(newList);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView searchTermTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTermTextView = itemView.findViewById(R.id.searchTermTextView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_term_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String searchTerm = searchTerms.get(position);
        holder.searchTermTextView.setText(searchTerm);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(searchTerm));
    }
    @Override
    public int getItemCount() {
        return searchTerms.size();
    }
}
