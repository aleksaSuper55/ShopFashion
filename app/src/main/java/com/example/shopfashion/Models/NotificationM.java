package com.example.shopfashion.Models;

public class NotificationM{
    public int id_notifications;
    public String title;
    public String message;
    public String id_orders;

    public int getId_notifications() {
        return id_notifications;
    }
    public void setId_notifications(int id_notifications) {
        this.id_notifications = id_notifications;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getId_orders() {
        return id_orders;
    }
    public void setId_orders(String id_orders) {
        this.id_orders = id_orders;
    }
}
