package com.lads.rootdirecrory.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
    private Context context;

    public PrefUtil(Context context) {
        this.context = context;
    }

    public final String PREFS_NAME = "my_prefs";



    public void setInt(String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, defValue);
    }

    public void setString(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key, "null");
    }

    public void setBool(String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBool(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key, false);
    }

    public boolean getBool(String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key, defaultValue);
    }

//    public boolean getIsLinearLayout(String key) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
//        return prefs.getBoolean(key, true);
//    }
//
//    public void setIsLinearLayout(String key, boolean value) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putBoolean(key, value);
//        editor.apply();
//    }
}
