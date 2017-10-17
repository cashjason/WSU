package com.example.cashj.wsu;

/**
 * Created by cashj on 10/16/2017.
 */

public class Values {
    private String name;
    private String number;
    private String phone;
    private String team;
    private String year;
    private String position;
    private String Height;
    private String Weight;

    public String getName() {
        //get name from db
        return name;
    }

    public void setName(String name) {
        //Set name in db
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
