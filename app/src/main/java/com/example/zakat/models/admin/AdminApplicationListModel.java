package com.example.zakat.models.admin;

public class AdminApplicationListModel {
    private String uid;
    private String name;
    private String ic;
    private String address;
    private String postCode;
    private String city;
    private String state;
    private String amount;
    private String date;
    private String key;
    private String zakatType;


    public AdminApplicationListModel() {
    }

    public AdminApplicationListModel(String uid, String name, String date, String key) {
        this.uid = uid;
        this.name = name;
        this.date = date;
        this.key = key;
    }

    public AdminApplicationListModel(String uid, String name, String ic, String address, String postCode, String city, String state, String amount, String date, String key, String zakatType) {
        this.uid = uid;
        this.name = name;
        this.ic = ic;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.state = state;
        this.amount = amount;
        this.date = date;
        this.key = key;
        this.zakatType = zakatType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getZakatType() {
        return zakatType;
    }

    public void setZakatType(String zakatType) {
        this.zakatType = zakatType;
    }
}
