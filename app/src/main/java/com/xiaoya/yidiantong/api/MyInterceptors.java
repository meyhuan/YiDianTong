package com.xiaoya.yidiantong.api;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/30.
 * api 拦截器
 */
public class MyInterceptors implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        LogUtils.d(response.body().string());
        return response;
    }
}
