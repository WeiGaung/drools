package com.wenba.rest.controller.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;//姓名

    private int level;//用户级别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
