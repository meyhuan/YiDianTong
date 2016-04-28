package com.xiaoya.yidiantong.model;

import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;

import com.smartydroid.android.starter.kit.account.Account;
import com.smartydroid.android.starter.kit.model.entity.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by YuGang Yang on February 20, 2016.
 * Copyright 20015-2016 qiji.tech. All rights reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class User extends Entity implements Account {

    //男
    public static final int MAN = 0;
    //女
    public static final int WAMEN = 1;
    //未知
    public static final int UNKONW = 2;
    public static final String DEFAULT_NAME = "LJ";
    //用户id
    public String id;
    public String userId;
    public String email;
    public String userPW;
    public String verifyemail;
    public String birthday;
    public String work;
    public String love;
    public String isshowQQ;
    public String isshowPhone;
    public String isshowBirthday;
    public String isWeibo;
    public String isqq;
    public String constellation;
    public String country;
    public String province;
    public String city;
    public String score;
    public String qq;
    //用户手机号
    public String mobilephone;
    //用户昵称
    public String name;
    //用户头像
    public String imgUrl;
    //用户性别
    public int sex;

    public User() {

    }

    public Uri uri() {
        if (TextUtils.isEmpty(imgUrl)) return null;

        if (imgUrl.startsWith("http://")) {
            return Uri.parse(imgUrl);
        }

        return null;
    }

    @Override public String token() {
        return id;
    }

    @Override public Object key() {
        return id;
    }
    @Override public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.email);
        dest.writeString(this.verifyemail);
        dest.writeString(this.userPW);
        dest.writeString(this.birthday);
        dest.writeString(this.work);
        dest.writeString(this.love);
        dest.writeString(this.isshowQQ);
        dest.writeString(this.isshowPhone);
        dest.writeString(this.isshowBirthday);
        dest.writeString(this.isWeibo);
        dest.writeString(this.isqq);
        dest.writeString(this.constellation);
        dest.writeString(this.country);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.score);
        dest.writeString(this.qq);
        dest.writeString(this.mobilephone);
        dest.writeString(this.name);
        dest.writeString(this.imgUrl);
        dest.writeInt(this.sex);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.email = in.readString();
        this.verifyemail = in.readString();
        this.birthday = in.readString();
        this.userPW = in.readString();
        this.work = in.readString();
        this.love = in.readString();
        this.isshowQQ = in.readString();
        this.isshowPhone = in.readString();
        this.isshowBirthday = in.readString();
        this.isWeibo = in.readString();
        this.isqq = in.readString();
        this.constellation = in.readString();
        this.country = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.score = in.readString();
        this.qq = in.readString();
        this.mobilephone = in.readString();
        this.name = in.readString();
        this.imgUrl = in.readString();
        this.sex = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", userPW='" + userPW + '\'' +
                ", verifyemail='" + verifyemail + '\'' +
                ", birthday='" + birthday + '\'' +
                ", work='" + work + '\'' +
                ", love='" + love + '\'' +
                ", isshowQQ='" + isshowQQ + '\'' +
                ", isshowPhone='" + isshowPhone + '\'' +
                ", isshowBirthday='" + isshowBirthday + '\'' +
                ", isWeibo='" + isWeibo + '\'' +
                ", isqq='" + isqq + '\'' +
                ", constellation='" + constellation + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", score='" + score + '\'' +
                ", qq='" + qq + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", sex=" + sex +
                '}';
    }
}
