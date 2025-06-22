package com.example.shopfashion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopfashion.Models.Discount;
import com.example.shopfashion.R;
import java.util.List;
public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolder> {
    private Context context;
    private List<Discount> discountList;
    public DiscountAdapter(Context context, List<Discount> discountList) {
        this.context = context;
        this.discountList = discountList;
    }
    @NonNull
    @Override
    public DiscountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discount_item, parent, false);
        return new DiscountAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DiscountAdapter.ViewHolder holder, int position) {
        Discount discount = discountList.get(position);
        holder.discountNameTextView.setText(discount.getName());
        holder.DescriptionTextView.setText(discount.getDescription());
    }
    @Override
    public int getItemCount() {
        return discountList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView discountNameTextView, DescriptionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            discountNameTextView = itemView.findViewById(R.id.discountNameTextView);
            DescriptionTextView = itemView.findViewById(R.id.DescriptionTextView);
        }
    }
}