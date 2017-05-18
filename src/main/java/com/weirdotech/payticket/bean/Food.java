package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/5/11.
 */
public class Food {
    public int id;
    public String name = "";

    public Food(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
