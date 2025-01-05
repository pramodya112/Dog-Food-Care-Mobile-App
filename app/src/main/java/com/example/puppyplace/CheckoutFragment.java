package com.example.puppyplace;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CheckoutFragment extends Fragment {

    private EditText cardNumber;
    private EditText cvc;
    private EditText expiryMonth;
    private EditText expiryYear;
    private Button confirmPaymentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        cardNumber = view.findViewById(R.id.card_number);
        cvc = view.findViewById(R.id.cvc);
        expiryMonth = view.findViewById(R.id.expiry_month);
        expiryYear = view.findViewById(R.id.expiry_year);
        confirmPaymentButton = view.findViewById(R.id.confirm_payment_button);

        confirmPaymentButton.setOnClickListener(v -> {
            if (validateInputs()) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, new PaymentDoneFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(cardNumber.getText())) {
            Toast.makeText(getActivity(), "Enter Card Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(cvc.getText())) {
            Toast.makeText(getActivity(), "Enter CVC", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(expiryMonth.getText())) {
            Toast.makeText(getActivity(), "Enter Expiry Month", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(expiryYear.getText())) {
            Toast.makeText(getActivity(), "Enter Expiry Year", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
