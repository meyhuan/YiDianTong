package com.xiaoya.yidiantong.model;

/**
 * Created by Administrator on 2016/3/30.
 */
public class UserInfo {

    public String status;
    public User content;

    @Override
    public String toString() {
        return "UserInfo{" +
                "status='" + status + '\'' +
                ", content=" + content.toString() +
                '}';
    }
}
