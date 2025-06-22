package com.example.shopfashion;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class SupabaseClient {
    public interface SBC_Callback{
        public void onFailure(IOException e);
        public void onResponse(String responseBody);
    }
    public static String DOMAIN_NAME = "https://hnjbqkkfnxbuanxqawgp.supabase.co/";
    public static String REST_PATH = "rest/v1/";
    public static String AUTH_PATH = "auth/v1/";
    public static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg";
    //public static String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6ImFXQjZGY2cyb1N6c2x6bjQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2huamJxa2tmbnhidWFueHFhd2dwLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzUwMDYzMTY2LCJpYXQiOjE3NTAwNTk1NjYsImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsIjoidGVzdGljMkBlbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfdmVyaWZpZWQiOmZhbHNlLCJzdWIiOiJlZjI2MjBmYy1jOTljLTRkZDQtYmU2Ny1jZmQ1NTUyNDdmZWUifSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc1MDA1OTU2Nn1dLCJzZXNzaW9uX2lkIjoiYTEyYTU4ZGUtZDVlYi00MTRlLWFmNTYtYmM0NmM4YTA5OTEwIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.ZpAkP6OfvVkGIEFDr2lXgne__JFtvdgCF-sb6BJljaE";
    OkHttpClient client = new OkHttpClient();
    public  void login(LoginRequest loginRequest, final SBC_Callback callback){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        //RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"testic3@email.ru\",\n  \"password\": \"147258\"\n}");
        Request request = new Request.Builder()
                .url("https://hnjbqkkfnxbuanxqawgp.supabase.co/auth/v1/token?grant_type=password")
                .method("POST", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else{
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }
    public void registerUser(LoginRequest loginRequest, final SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "signup")
                .method("POST", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void update(String userId, String userName, SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = String.format("{\"username\": \"%s\"}", userName);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "profiles?id=eq." + DataBinding.getUuidUser())
                .method("PATCH", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void logout(SBC_Callback callBack){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "logout")
                .method("POST", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    DataBinding.saveBearerToken("");
                    DataBinding.saveUuidUser("");
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
    public void FetchCurrentUser(final SBC_Callback callBack){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "profiles?select=*id=eq." + DataBinding.getUuidUser())
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callBack.onResponse(responseBody);
                } else {
                    callBack.onFailure(new IOException("Server error:" + response));
                }
            }
        });
    }
public void fetchAllProducts(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Products?select=*")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
public void fetchWoman(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Products?select=*&id_categories=eq.1")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Range", "0-9")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
public void fetchMan(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Products?select=*&id_categories=eq.2")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Range", "0-9")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
public void fetchAccessories(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Products?select=*&id_categories=eq.3")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Range", "0-9")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
public void fetchAllDiscounts(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Discounts?select=*")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
public  void fetchAllNotifications(final SBC_Callback callback){
    Request request = new Request.Builder()
            .url(DOMAIN_NAME + REST_PATH +"Notifications?select=*")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuamJxa2tmbnhidWFueHFhd2dwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI2NzQsImV4cCI6MjA2NDYwODY3NH0.auLQScND91P8dRWOLO5paKRMmg5HtwBI53mDJU-BJXg")
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(e);
        }
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            if (response.isSuccessful()){
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }else{
                callback.onFailure(new IOException("Ошибка сервера: " + response));
            }
        }
    });
}
    public void fetchAllOrders(final SBC_Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH+ "Orders?select=*,Order_items(*)")
                .addHeader("apikey", API_KEY)
//                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else{
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }
}
