package com.example.puppyplace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    private EditText etNewEmail;
    private EditText etNewPassword;
    private Button btnUpdate;
    private Button btnLogout;

    private DatabaseReference mDatabase;
    private String loggedInUsername;

    private static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etNewEmail = view.findViewById(R.id.et_new_email);
        etNewPassword = view.findViewById(R.id.et_new_password);
        btnUpdate = view.findViewById(R.id.btn_update);
        btnLogout = view.findViewById(R.id.btn_logout);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", getActivity().MODE_PRIVATE);
        loggedInUsername = sharedPreferences.getString("logged_in_user", null);

        if (loggedInUsername == null) {
            Log.d(TAG, "User not logged in");
            Toast.makeText(getActivity(), "User not logged in", Toast.LENGTH_SHORT).show();
            redirectToLogin();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = etNewEmail.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();

                if (newEmail.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    updateUserProfile(newEmail, newPassword);
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return view;
    }

    private void updateUserProfile(String newEmail, String newPassword) {
        if (loggedInUsername != null) {
            mDatabase.child(loggedInUsername).child("email").setValue(newEmail);
            mDatabase.child(loggedInUsername).child("password").setValue(newPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Profile updated successfully");
                } else {
                    Toast.makeText(getActivity(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed to update profile");
                }
            });
        } else {
            Toast.makeText(getActivity(), "User not logged in", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "User not logged in");
        }
    }

    private void logout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("logged_in_user");
        editor.apply();

        Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Logged out successfully");
        redirectToLogin();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
