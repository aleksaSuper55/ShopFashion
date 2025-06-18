package com.example.shopfashion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PinCode extends AppCompatActivity {
    private EditText pinInput;
    private Button confirmButton;
    private final String CORRECT_PIN = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_code);
        pinInput = findViewById(R.id.pinInput);
        confirmButton = findViewById(R.id.confirmButton);
        setupPinInputListener();
        setupConfirmButton();
    }

    private void setupPinInputListener() {
        pinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirmButton.setEnabled(s.length() == 4); // Активируем кнопку только при 4 цифрах
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupConfirmButton() {
        confirmButton.setOnClickListener(v -> {
            String enteredPin = pinInput.getText().toString();
            if (enteredPin.equals(CORRECT_PIN)) {
                startActivity(new Intent(this, Shop.class));
                finish();
            } else {
                // Обработка неверного PIN
                pinInput.setError("Неверный PIN-код");
                pinInput.setText("");
            }
        });
    }
}