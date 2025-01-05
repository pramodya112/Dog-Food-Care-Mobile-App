package com.example.puppyplace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView cartIcon;
    private TextView cartItemCount;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        cartIcon = view.findViewById(R.id.cart_icon);
        cartItemCount = view.findViewById(R.id.cart_item_count);

        // Initialize item list and adapter
        itemList = new ArrayList<>();
        loadItems(); // Method to load items into the list
        itemAdapter = new ItemAdapter(itemList, getActivity(), this::updateCartItemCount);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(itemAdapter);

        cartIcon.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getActivity(), "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, new CartFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    private void loadItems() {
        // Load items into the list

        itemList.add(new Item("Beneful Grain free", "$120", R.drawable.dogfood6));
        itemList.add(new Item("Grain free", "$100", R.drawable.dogfood3));
        itemList.add(new Item("Victor  ", "$75", R.drawable.dogfood7));
        itemList.add(new Item("Wellness", "$110", R.drawable.dogfood9));
        // Add more items as needed
    }

    public void updateCartItemCount() {
        int count = CartManager.getInstance().getCartItems().size();
        if (count > 0) {
            cartItemCount.setText(String.valueOf(count));
            cartItemCount.setVisibility(View.VISIBLE);
        } else {
            cartItemCount.setVisibility(View.GONE);
        }
    }
}
