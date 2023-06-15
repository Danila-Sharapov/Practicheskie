package com.example.firecloudbase;

public class Notes {
    private String title;
    private String sodd;
    private String email;

    public Notes(String title, String content, String email) {
        this.title = title;
        this.sodd = content;
        this.email = email;
    }



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return sodd;
    }
    public void setContent(String content) {
        this.sodd = content;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
