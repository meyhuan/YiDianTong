package com.xiaoya.yidiantong;

import android.app.Application;

import org.litepal.LitePalApplication;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */
public class App extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
