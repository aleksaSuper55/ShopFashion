package com.example.shopfashion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {
    private EditText EnterEmailAddress, EnterPassword;
    private Button returnsing, contpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        // Инициализация элементов
        returnsing = findViewById(R.id.returnsing);
        EnterEmailAddress = findViewById(R.id.EnterEmailAddress);
        EnterPassword = findViewById(R.id.EnterPassword);
        contpass = findViewById(R.id.contpass);
        // Навигация назад к экрану входа
        returnsing.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
            finish();
        });

        // Обработка кнопки "Start Shopping"
        contpass.setOnClickListener(v -> {
            String email = EnterEmailAddress.getText().toString().trim();
            String newPassword = EnterPassword.getText().toString().trim();

            if (validateInputs(email, newPassword)) {
                // Здесь логика сброса пароля
                resetPassword(email, newPassword);
                // Переход в магазин после успешного сброса
                startActivity(new Intent(this, Shop.class));
                finish();
            }
        });
    }
    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            EnterEmailAddress.setError("Please enter email");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EnterEmailAddress.setError("Invalid email format");
            return false;
        }
        if (password.isEmpty()) {
            EnterPassword.setError("Please enter new password");
            return false;
        }
        if (password.length() < 8) {
            EnterPassword.setError("Password must be at least 8 characters");
            return false;
        }
        return true;
    }
    private void resetPassword(String email, String newPassword) {
        // Реализация сброса пароля (Firebase, ваш бэкенд и т.д.)
        Toast.makeText(ForgotPassword.this, "Password reset successful", Toast.LENGTH_SHORT).show();
    }
}

