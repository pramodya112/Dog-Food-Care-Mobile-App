package com.example.puppyplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;
    private Runnable updateCartItemCount;

    public ItemAdapter(List<Item> itemList, Context context, Runnable updateCartItemCount) {
        this.itemList = itemList;
        this.context = context;
        this.updateCartItemCount = updateCartItemCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
        holder.imageView.setImageResource(item.getImageResId());

        holder.addToCart.setOnClickListener(v -> {
            // Handle add to cart
            CartManager.getInstance().addItem(item);
            Toast.makeText(context, item.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
            updateCartItemCount.run();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView imageView, addToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
            imageView = itemView.findViewById(R.id.item_image);
            addToCart = itemView.findViewById(R.id.add_to_cart);
        }
    }
}
