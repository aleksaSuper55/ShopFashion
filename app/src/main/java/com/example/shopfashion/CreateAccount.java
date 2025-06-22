package com.example.shopfashion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {
    private static final int ERROR_COLOR = Color.rgb(255, 1, 1);
    private EditText firstName, lastName, emailAddress, passwordNew, passwordAgain;
    private Button contAcc, returntoPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_accounte);

        // Инициализация view
        initViews();
        returntoPage.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
            finish();
        });
        contAcc.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(this, "Account has been created!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Shop.class));
                finish();
            }
        });
    }
    private void initViews() {
        returntoPage = findViewById(R.id.returnToPage);
        contAcc = findViewById(R.id.contacc);
        firstName = findViewById(R.id.firstn);
        lastName = findViewById(R.id.lastn);
        emailAddress = findViewById(R.id.email);
        passwordNew = findViewById(R.id.pas1);
        passwordAgain = findViewById(R.id.pas2);
    }
    private boolean validateInputs() {
        String firstnameInput = firstName.getText().toString().trim();
        String lastnameInput = lastName.getText().toString().trim();
        String emailInput = emailAddress.getText().toString().trim();
        String pass1Input = passwordNew.getText().toString().trim();
        String pass2Input = passwordAgain.getText().toString().trim();
        return isNameValid(firstnameInput) &&
                isNameValid(lastnameInput) &&
                isEmailValid(emailInput) &&
                isPasswordValid(pass1Input, pass2Input);
    }
    private boolean isNameValid(String name) {
        if (TextUtils.isEmpty(name)) {
            showError(firstName, "Needs a name");
            return false;
        }
        return true;
    }
    private boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            showError(emailAddress, "Put your email address");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(emailAddress, "Incorrect email format");
            return false;
        }
        return true;
    }
    private boolean isPasswordValid(String pass1, String pass2) {
        if (TextUtils.isEmpty(pass1)) {
            showError(passwordNew, "Put a password");
            return false;
        }
        if (pass1.length() < 8) {
            showError(passwordNew, "Password too short (min 8 chars)");
            return false;
        }
        if (!pass1.equals(pass2)) {
            showError(passwordAgain, "Passwords don't match");
            return false;
        }
        return true;
    }
    private void showError(EditText view, String message) {
        view.setHint(message);
        view.setHintTextColor(ERROR_COLOR);
        view.requestFocus();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}