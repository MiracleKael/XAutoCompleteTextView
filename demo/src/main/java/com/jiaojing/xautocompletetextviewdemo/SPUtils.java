package com.jiaojing.xautocompletetextviewdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiaojing on 2017/12/25.
 */

public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "serach_history";

    public static void put(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String temp = get(context, key, "");
        value = temp + value + ",";

        editor.putString(key, value);
        editor.commit();
    }

    public static String get(Context context, String key, String defaultObject){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, defaultObject);
        return string;
    }
}
