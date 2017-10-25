package com.example.cashj.wsu;

import java.io.Serializable;

/**
 * Created by cashj on 10/25/2017.
 */

public class UserData implements Serializable{
    //Holds profile data as serializable
    private String name;
    private String team;
    private String year;
    private String position;
    private int num;
    private int phone;
    private int height;
    private int weight;

    UserData(){
        this.name = "";
        this .team = "";
        this.year = "";
        this.position = "";
        this.num = -1;
        this.phone = -1;
        this.height = -1;
        this.weight = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
