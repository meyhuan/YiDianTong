package com.xiaoya.yidiantong.api;

import com.smartydroid.android.starter.kit.retrofit.RetrofitBuilder;
import com.xiaoya.yidiantong.api.service.AuthService;
import com.xiaoya.yidiantong.api.service.UpLoadService;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/3/30.
 *
 */
public class ApiService {

    public static final String APP_KEY = "ZWEwZjg0YjY0ZWYwOWQ5";
    public static final String PASSWORD = "password_cache";
    public static final String USERNAME = "username_cache";
    public static final String API_ENDPOINT = "http://test.api.linghit.com/api/";


    public static final String API_FORGET_PW = "MemberInfo_newsChangePW";

    public static final String OK = "1";

    public static UpLoadService createUpLoadService() {
        return retrofit().create(UpLoadService.class);
    }

    public static AuthService createAuthService() {
        return retrofit().create(AuthService.class);
    }

    private static Retrofit retrofit() {
        return RetrofitBuilder.get().retrofit();
    }
}
