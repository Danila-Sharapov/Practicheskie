package com.example.firecloudbase;

import java.util.ArrayList;

public class Users {
    private ArrayList<String> notes;
    private String email;
    private String key;
    public Users(String email, String key, ArrayList<String> notes)
    {

        this.email = email;
        this.key = key;
        this.notes = notes;

    }

    public ArrayList<String> getNotes() {

        return notes;
    }

    public void setNotes(ArrayList<String> notes) {

        this.notes = notes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
