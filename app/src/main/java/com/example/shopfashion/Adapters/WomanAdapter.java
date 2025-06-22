package com.example.shopfashion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.shopfashion.Models.Product;
import com.example.shopfashion.R;
import java.util.List;

public class WomanAdapter extends RecyclerView.Adapter<WomanAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    public WomanAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    @NonNull
    @Override
    public WomanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_item, parent, false);
        return new WomanAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull WomanAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(String.valueOf(product.getName()));
        holder.productPrice.setText(product.getPrice());
        String url = "https://hnjbqkkfnxbuanxqawgp.supabase.co/storage/v1/object/public/avatarproduct//";
        Glide.with(context)
                .load(url + product.getImages())
                .into(holder.productImage);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
