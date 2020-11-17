package com.example.datinghaui.model;

import java.io.Serializable;

public class User implements Serializable {
    private String phoneNumber;
    private String passWord;
    private String userName;
    private String address;
    private String about;
    private String age;
    private String favorite;

    public String getLinkavatar() {
        return linkavatar;
    }

    public void setLinkavatar(String linkavatar) {
        this.linkavatar = linkavatar;
    }

    private String linkavatar;
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public User() {
    }

    public User(String phoneNumber, String passWord, String userName, String address, String about, String age, String favorite, String linkavatar, String sex) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.userName = userName;
        this.address = address;
        this.about = about;
        this.age = age;
        this.favorite = favorite;
        this.linkavatar = linkavatar;
        this.sex = sex;
    }

    public User(String phoneNumber, String passWord, String userName, String address, String about, String age) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.userName = userName;
        this.address = address;
        this.about = about;
        this.age = age;
    }

    public User(String phoneNumber, String passWord, String userName, String address, String about, String age, String favorite,String sex) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.userName = userName;
        this.address = address;
        this.about = about;
        this.age = age;
        this.favorite = favorite;
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
