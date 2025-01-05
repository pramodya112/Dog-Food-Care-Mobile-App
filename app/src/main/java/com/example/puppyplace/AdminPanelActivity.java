package com.example.puppyplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AdminPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel); // Reference the layout file
    }

    // Method to handle Add Items button click
    public void onAddItemsClicked(View view) {
        // Start AddProductActivity when Add Items button is clicked
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    // Method to handle Add Newsfeed button click
    public void onAddNewsfeedClicked(View view) {
        // Start AddNewsfeedActivity when Add Newsfeed button is clicked
        Intent intent = new Intent(this, AddNewsfeedActivity.class);
        startActivity(intent);
    }

    // Method to handle Back to Login button click
    public void onBackToLoginClicked(View view) {
        // Finish this activity to go back to the previous screen
        finish();
        // Optional: Uncomment below to explicitly start the LoginActivity if desired
        // Intent intent = new Intent(this, LoginActivity.class);
        // startActivity(intent);
    }
}
