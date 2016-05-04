package com.xiaoya.yidiantong;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartydroid.android.starter.kit.StarterKit;
import com.smartydroid.android.starter.kit.account.Account;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import com.smartydroid.android.starter.kit.retrofit.RetrofitBuilder;
import com.xiaoya.yidiantong.api.ApiService;
import com.xiaoya.yidiantong.model.User;

import org.litepal.LitePalApplication;

import java.io.IOException;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */
public class App extends StarterKitApp {

    private static int currentSubject = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);

        // common config
        new StarterKit.Builder()
                .setDebug(BuildConfig.DEBUG)
                .build();

        super.onCreate();
        //enabledStrictMode();
        //LeakCanary.install(this);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // init api service
        new RetrofitBuilder.Builder()
                .debug(BuildConfig.DEBUG)
                .baseUrl(ApiService.API_ENDPOINT)
                .addInterceptors(logging)
                .build();
    }

    public static void setCurrentSubject(int currentSubject) {
        App.currentSubject = currentSubject;
    }

    public static int getCurrentSubject() {
        return currentSubject;
    }

    @Override
    public Account accountFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, User.class);
        } catch (IOException e) {
            // Nothing to do
        }
        return null;
    }

    @Override
    public String accept() {
        return null;
    }
}
