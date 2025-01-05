package com.example.puppyplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private Uri imageUri;

    private EditText itemName, itemDescription, itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Initialize the fields
        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemPrice);
    }

    // Handle the upload image button click
    public void onUploadImageClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    // Handle the image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            Toast.makeText(this, "Image selected!", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle the submit button click
    public void onSubmitClicked(View view) {
        String name = itemName.getText().toString().trim();
        String description = itemDescription.getText().toString().trim();
        String price = itemPrice.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || price.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please fill out all fields and select an image", Toast.LENGTH_SHORT).show();
        } else {
            // Passing data to DisplayProductActivity
            Intent intent = new Intent(this, DisplayProductActivity.class);
            intent.putExtra("itemName", name);
            intent.putExtra("itemDescription", description);
            intent.putExtra("itemPrice", price);
            intent.putExtra("itemImageUri", imageUri.toString());
            startActivity(intent);
            Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
