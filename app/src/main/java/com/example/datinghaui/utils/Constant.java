package com.example.datinghaui.utils;

import com.example.datinghaui.model.User;

public class Constant {
    public static final String noteUser = "User";
    public static final String noteMatching = "Matching";
    public static final String noteFan = "fan";
    public static final String noteMatched = "Matched";
    public static final String noteMessage = "Message";
    public static  User userCurent = null;
    public static String getPrimaryKeyWithUser(User user){
        String key = "";
        if(userCurent.getPhoneNumber().compareTo(user.getPhoneNumber()) > 0){
            key = user.getPhoneNumber() + "-" + userCurent.getPhoneNumber();
        }else {
            key = userCurent.getPhoneNumber()  + "-" + user.getPhoneNumber() ;
        }
        return key;
    }
}
