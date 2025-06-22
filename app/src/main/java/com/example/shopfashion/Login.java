package com.example.shopfashion;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopfashion.Models.AuthResponse;
import com.google.gson.Gson;
import java.io.IOException;

public class Login extends AppCompatActivity {
    private EditText userName, emailAddress, password;
    private TextView resetPassword;
    private Button loginButton, registerButton, pinCodeButton;
    private final int errorColor = Color.RED;
    private int defaultHintColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.Password);
        resetPassword = findViewById(R.id.reset);
        loginButton = findViewById(R.id.autor);
        registerButton = findViewById(R.id.reg);
        pinCodeButton = findViewById(R.id.contsign);
        defaultHintColor = userName.getCurrentHintTextColor();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = userName.getText().toString().trim();
                String emailInput = emailAddress.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                if (isValueName(userInput) || isValueEmail(emailInput) || isValuePass(passwordInput)){
                    loginUser(userInput, emailInput, passwordInput);
                }
            }
        });
        resetPassword.setOnClickListener(v -> startActivity(new Intent(this, ForgotPassword.class)));
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = userName.getText().toString().trim();
                String emailInput = emailAddress.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                if (isValueName(userInput) || isValueEmail(emailInput) || isValuePass(passwordInput)) {
                    authUser(userInput, emailInput, passwordInput);
                }
            }
        });
        pinCodeButton.setOnClickListener(v -> startActivity(new Intent(this, PinCode.class)));
    }
    private boolean isValueName(String input){
        if(TextUtils.isEmpty(input)){
            userName.setHintTextColor(errorColor);
            Toast.makeText(Login.this, "Введите имя", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(input.length() < 3){
            userName.setHintTextColor(errorColor);
            Toast.makeText(Login.this, "There should be more than 3 symbols", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean isValueEmail(String input){
        if(TextUtils.isEmpty(input)){
            emailAddress.setHintTextColor(errorColor);
            Toast.makeText(Login.this, "Введите почту", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            return true;
        }
        emailAddress.setHintTextColor(errorColor);
        Toast.makeText(Login.this, "Неправельная почта", Toast.LENGTH_SHORT).show();
        return false;
    }
    private boolean isValuePass (String input){
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(Login.this, "Введите пароль", Toast.LENGTH_SHORT).show();
            password.setHintTextColor(errorColor);
            return false;
        } else if (input.length() < 8) {
            Toast.makeText(Login.this, "Длинна пароля 8 или более символов", Toast.LENGTH_SHORT).show();
            password.setHintTextColor(errorColor);
            return false;
        }
        return true;
    }
    private void resetFieldHints() {
        // Восстанавливаем стандартные подсказки
        userName.setHint("User Name");
        emailAddress.setHint("Email Address");
        password.setHint("Password");
        userName.setHintTextColor(defaultHintColor);
        emailAddress.setHintTextColor(defaultHintColor);
        password.setHintTextColor(defaultHintColor);
    }
    public void loginUser (String userName, String email, String password){
        SupabaseClient supabaseClient = new SupabaseClient();
        LoginRequest loginRequest = new LoginRequest(
                email, password
        );
            supabaseClient.login(loginRequest, new SupabaseClient.SBC_Callback() {
                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(()->{
                        Log.e("loginUser:onFailure", e.getLocalizedMessage());
                    });
                }
                @Override
                public void onResponse(String responseBody) {
                    runOnUiThread(()->{
                        Log.e("loginUser:onResponse", responseBody);
                        Gson gson = new Gson();
                        AuthResponse auth = gson.fromJson(responseBody, AuthResponse.class);
                        // TODO
//                      DataBinding.saveBearerToken("Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6ImFXQjZGY2cyb1N6c2x6bjQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2huamJxa2tmbnhidWFueHFhd2dwLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzUwMDYzMTY2LCJpYXQiOjE3NTAwNTk1NjYsImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfdmVyaWZpZWQiOmZhbHNlLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUifSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc1MDA1OTU2Nn1dLCJzZXNzaW9uX2lkIjoiYTEyYTU4ZGUtZDVlYi00MTRlLWFmNTYtYmM0NmM4YTA5OTEwIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.ZpAkP6OfvVkGIEFDr2lXgne__JFtvdgCF-sb6BJljaE");
                        DataBinding.saveBearerToken("Bearer" + auth.getAccess_token());
                        DataBinding.saveUuidUser(auth.getUser().getId());
                        // TODO
                        startActivity(new Intent(getApplicationContext(), Shop.class));
                        Log.e("loginUser:onResponse", auth.getUser().getId());
                    });
                }
            });
        }
    public void updateAccount(String userName){
        SupabaseClient supabaseClient = new SupabaseClient();
        String userId = DataBinding.getUuidUser();
        supabaseClient.update(userId, userName, new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("updateAccount:onFailure", e.getLocalizedMessage());
                    Toast.makeText(Login.this, "Sorry, we can't add your username to account right now:(", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("updateAccount:onResponse", responseBody);
                    startActivity(new Intent(getApplicationContext(), Shop.class));
                });
            }
        });
    }
    public void authUser(String userFrName, String email, String passwordE){
        SupabaseClient supabaseClient = new SupabaseClient();
        LoginRequest loginRequest = new LoginRequest(
                email,
                passwordE
        );
        supabaseClient.registerUser(loginRequest ,new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("authUser:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("authUser:onResponse", responseBody);
                    Gson gson = new Gson();
                    AuthResponse auth = gson.fromJson(responseBody, AuthResponse.class);
                    DataBinding.saveBearerToken("Bearer " + auth.getAccess_token());
                    DataBinding.saveUuidUser(auth.getUser().getId());
                    updateAccount(userFrName);
                });
            }
        });
    }
}
