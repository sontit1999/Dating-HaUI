package com.example.datinghaui.utils;

import com.google.firebase.database.FirebaseDatabase;

public class UtilsDatabase {
    public static FirebaseDatabase database = null;
    public static FirebaseDatabase getInstant(){
       if(database == null){
           database   = FirebaseDatabase.getInstance();
       }
      return database;
    }
}
