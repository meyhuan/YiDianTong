package com.xiaoya.yidiantong.api.service;

import com.xiaoya.yidiantong.model.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2016/3/30.
 */
public interface UpLoadService {
    @Multipart
    @POST("MemberLogin_getUserPic")
    Call<BaseResponse> uploadAvatar(
            @Part("file\"; filename=\"pp.png\" ") RequestBody file,
            @Part("appkey") RequestBody appkey,
            @Part("userId") RequestBody userId,
            @Part("userPW") RequestBody userPW,
            @Part("channel") RequestBody channel);


}
