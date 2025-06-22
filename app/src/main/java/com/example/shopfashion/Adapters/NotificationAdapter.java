package com.example.shopfashion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopfashion.Models.NotificationM;
import com.example.shopfashion.R;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private List<NotificationM> notificationList;
    public NotificationAdapter(Context context, List<NotificationM> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }
    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notofoc_items, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationM notificationM = notificationList.get(position);
        holder.notiname.setText(notificationM.getTitle());
        holder.notimessage.setText(notificationM.getMessage());
    }
    @Override
    public int getItemCount() {
        return notificationList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notiname, notimessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notiname = itemView.findViewById(R.id.notiname);
            notimessage = itemView.findViewById(R.id.notimessage);
        }
    }
}
