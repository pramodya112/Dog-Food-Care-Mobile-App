package com.example.puppyplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayProductActivity extends AppCompatActivity {

    private TextView displayName, displayDescription, displayPrice;
    private ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);

        // Initialize display fields
        displayName = findViewById(R.id.displayName);
        displayDescription = findViewById(R.id.displayDescription);
        displayPrice = findViewById(R.id.displayPrice);
        displayImage = findViewById(R.id.displayImage);

        // Get data from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("itemName");
        String description = intent.getStringExtra("itemDescription");
        String price = intent.getStringExtra("itemPrice");
        Uri imageUri = Uri.parse(intent.getStringExtra("itemImageUri"));

        // Set the data on the display fields
        displayName.setText(name);
        displayDescription.setText(description);
        displayPrice.setText(price);
        displayImage.setImageURI(imageUri);
    }
}
