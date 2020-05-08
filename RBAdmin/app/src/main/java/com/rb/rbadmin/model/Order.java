package com.rb.rbadmin.model;

public class Order {
    private String title, quantity, note, category, delivered, datePlaced, address, fee, reason;

    public Order() {
    }

    public Order(String title, String quantity, String note, String category, String datePlaced, String address, String fee, String reason) {
        this.title = title;
        this.quantity = quantity;
        this.note = note;
        this.category = category;
        this.delivered = "N";
        this.datePlaced = datePlaced;
        this.address = address;
        this.fee = fee;
        this.reason = reason;
    }

    public Order(String title, String quantity, String note, String category, String delivered, String datePlaced, String address, String fee, String reason) {
        this.title = title;
        this.quantity = quantity;
        this.note = note;
        this.category = category;
        this.delivered = delivered;
        this.datePlaced = datePlaced;
        this.address = address;
        this.fee = fee;
        this.reason = reason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
