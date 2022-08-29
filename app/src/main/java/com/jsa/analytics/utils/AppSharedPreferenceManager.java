package com.jsa.analytics.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.jsa.analytics.model.UserDetailModel;

public class AppSharedPreferenceManager {
    public static String mySf = "mySf";
    public static String USER_DATA = "userdata";

    /*public static void setUserData(Context context, UserDetailModel result) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_DATA, new Gson().toJson(result));
        editor.apply();
//        StaticFields.astrologerData = result;
    }

    public static UserDetailModel getUserData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(USER_DATA, null), UserDetailModel.class);
    }


    public static void logout(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }*/
}
