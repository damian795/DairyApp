package com.example.diary;

public class Note {
    private String id;
    private String title;
    private String note;
    private String time;
    private String date;

    public Note(String id, String title, String note, String time, String date) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.time = time;
        this.date = date;
    }

    public Note(String id, String title, String time, String date) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.date = date;
    }

    Note(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
