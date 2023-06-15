package com.example.paperbd;

public class Guns {
    private String header;
    private String info;
    private String allinfo;

    public Guns(String Header, String Info){
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
}
