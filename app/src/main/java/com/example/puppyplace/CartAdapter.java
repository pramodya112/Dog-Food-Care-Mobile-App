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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;
    private CartUpdateListener cartUpdateListener;

    public CartAdapter(List<Item> itemList, Context context, CartUpdateListener cartUpdateListener) {
        this.itemList = itemList;
        this.context = context;
        this.cartUpdateListener = cartUpdateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
        holder.imageView.setImageResource(item.getImageResId());

        holder.deleteButton.setOnClickListener(v -> {
            CartManager.getInstance().removeItem(item);
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());
            Toast.makeText(context, item.getTitle() + " removed from cart", Toast.LENGTH_SHORT).show();

            // Update total amount
            cartUpdateListener.updateTotalAmount();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView imageView, deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_item_title);
            price = itemView.findViewById(R.id.cart_item_price);
            imageView = itemView.findViewById(R.id.cart_item_image);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
