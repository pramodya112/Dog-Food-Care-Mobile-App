package com.example.puppyplace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    private EditText signupName, signupEmail, signupUsername, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Initialize views
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Set up click listener for the Sign Up button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Sign Up button clicked"); // Log button click for debugging

                // Get user input
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String username = signupUsername.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();

                // Simple validation
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Signup.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(Signup.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.contains(".") || username.contains("#") || username.contains("$") || username.contains("[") || username.contains("]")) {
                    Toast.makeText(Signup.this, "Username can't contain '.', '#', '$', '[', or ']'", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register the user using Firebase Authentication
                registerUser(name, email, username, password);
            }
        });

        // Set up click listener for the login redirect text
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the login page
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                finish(); // Close the SignUpActivity
            }
        });
    }

    private void registerUser(String name, String email, String username, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");

                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String userId = firebaseUser.getUid();

                            // Add password to User object for authentication purposes
                            User user = new User(userId, name, email, username, password);

                            usersRef.child(userId).setValue(user).addOnCompleteListener(userTask -> {
                                if (userTask.isSuccessful()) {
                                    Log.d(TAG, "User data saved successfully");
                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                    // Redirect to login page
                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.e(TAG, "Failed to save user data", userTask.getException());
                                    Toast.makeText(Signup.this, "Registration Failed: " + userTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(Signup.this, "This email is already registered", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    // Update the User class to include password (for now)
    public static class User {
        public String userId;
        public String name;
        public String email;
        public String username;
        public String password;

        public User() { /* Default constructor required for Firebase */ }

        public User(String userId, String name, String email, String username, String password) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }
    }
}