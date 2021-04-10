package com.example.fast2meet;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferanceConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public   SharedPreferanceConfig(Context context)
    {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_shared_preference),Context.MODE_PRIVATE);
        //sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.sp_login_shared_preference),Context.MODE_PRIVATE);
    }


    public  void login_status(boolean status)
    {
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_shared_preference),status);
        editor.commit();
    }

    public  void sp_login_status(boolean status)
    {
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putBoolean("SP",status);
        editor.commit();
    }

    public  boolean read_login_status()
    {
        boolean status  = false;
        status  = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_shared_preference),false);
        return  status;
    }

    public  boolean sp_read_login_status()
    {
        boolean status  = false;
        status  = sharedPreferences.getBoolean("SP",false);
        return  status;
    }
}
