package com.example.homescreen.shared_preferences;

import android.content.Context;

public class PrefConfig {

    String preference_file_key = "kana_pos_pref";

//    public static final String APP_LOGIN_STATUS = "app_login_status";
//    public static final String USER_LOGGED_STATUS = "logged user";

//    SharedPreferences sharedPref;

    public PrefConfig(Context context) {
//        sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE);
    }

//    public void insertString(String key, String value) {
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(key, value);
//        editor.commit();
//    }
//
//    public void insertInteger(String key, Integer value) {
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(key, value);
//        editor.commit();
//    }

    public void insertBoolian(String key, boolean value) {
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(key, value);
//        editor.commit();
    }

//    public boolean isAppLogged() {
//        return sharedPref.getBoolean(APP_LOGIN_STATUS, false);
//    }
//    public boolean isUserLogged() {
//        return sharedPref.getBoolean(USER_LOGGED_STATUS, false);
//    }
}
