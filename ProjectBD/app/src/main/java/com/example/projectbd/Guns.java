package com.example.projectbd;

public class Guns {
    private int ID_gun;
    private String header;
    private String info;
    private String allinfo;

    public Guns(int ID_gun, String Header, String Info){
        this.ID_gun = ID_gun;
        header = Header;
        info = Info;
    }

    public String getAllinfo() {
        return allinfo;
    }
    public void setAllinfo(String allinfo) {
        this.allinfo = allinfo;
    }

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public int getID() {
        return ID_gun;
    }
    public void setID(int ID) {
        this.ID_gun = ID;
    }
}
