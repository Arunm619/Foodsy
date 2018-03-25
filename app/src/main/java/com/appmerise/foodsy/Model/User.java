package com.appmerise.foodsy.Model;

/**
 * Created by root on 22/3/18.
 */

public class User {
    String name;
    String email;
    String mobilenumber;
    String password;

    String imagepath;


    public User(String name, String email, String mobilenumber, String password, String imagepath) {
        this.name = name;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.imagepath = imagepath;
    }

    public User() {
    }


    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
