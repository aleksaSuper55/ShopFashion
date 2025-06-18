package com.example.shopfashion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {
    private Context context;
    private List<Discount> discountList;

    public DiscountAdapter(Context context, List<Discount> discountList) {
        this.context = context;
        this.discountList = discountList != null ? discountList : new ArrayList<>();
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.discount_item, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = discountList.get(position);

        // Защита от null
        holder.discountTitleTextView.setText(discount.getName() != null ? discount.getName() : "");
        holder.discountDescriptionTextView.setText(discount.getDescription() != null ? discount.getDescription() : "");
    }

    @Override
    public int getItemCount() {
        return discountList.size();
    }
    public static class DiscountViewHolder extends RecyclerView.ViewHolder {
        ImageView discountImageView;
        TextView discountTitleTextView;
        TextView discountDescriptionTextView;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            discountImageView = itemView.findViewById(R.id.discountImageView);
            discountTitleTextView = itemView.findViewById(R.id.discountTitleTextView);
            discountDescriptionTextView = itemView.findViewById(R.id.discountDescriptionTextView);
            // Проверка View в логах
            if (discountTitleTextView == null) {
                Log.e("ViewHolder", "discountTitleTextView не найден!");
            }
        }
    }
}