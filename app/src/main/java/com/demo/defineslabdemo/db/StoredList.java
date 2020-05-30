package com.demo.defineslabdemo.db;

public class StoredList {
    public void setId(int id) {
        this.id = id;
    }

    String sid;
    int id;
    String name;

    public StoredList() {
    }

    public StoredList(String sid, String name) {
        this.sid=sid;
        this.name=name;
    }

    public StoredList(int id, String sid, String name) {
        this.id=id;
        this.sid=sid;
        this.name=name;

    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
