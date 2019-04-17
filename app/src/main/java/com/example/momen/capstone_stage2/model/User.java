package com.example.momen.capstone_stage2.model;

/**
 * Created by Momen on 4/16/2019.
 */

public class User {
    private String name;
    private String password;
    private String phone;
    private Category category;

    public User(){}

    public User(String name,String password,String phone,Category category){
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
