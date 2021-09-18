package com.example.zakat.models;

public class Progress {
    private String uid;
    private String id;
    private String date;
    private String progress;
    private String status;

    public Progress() {
    }

    public Progress(String uid, String id, String date, String progress,String status) {
        this.uid = uid;
        this.id = id;
        this.date = date;
        this.progress = progress;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
