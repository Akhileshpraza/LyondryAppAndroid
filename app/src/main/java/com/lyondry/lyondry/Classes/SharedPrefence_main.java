package com.lyondry.lyondry.Classes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefence_main {
    private Context context;
    private static SharedPrefence_main sharedPrefence_main;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    public  SharedPrefence_main(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("PREF_READ", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static SharedPrefence_main getInstance(Context context){
     if (sharedPrefence_main ==null){
         sharedPrefence_main = new SharedPrefence_main(context);
     }
     return sharedPrefence_main;
    }

    public  String set_AddressId(String addressId){
        return sharedPrefence_main.getString("id");
    }

    public String getString(String id) {
        editor.putString("id",id);
        editor.commit();
        return id;

    }

//    public String get_AddressId(String addressId){
//        editor.putString("id",addressId);
//        editor.commit();
//        return addressId;
//    }

}
