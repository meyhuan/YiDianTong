package com.xiaoya.yidiantong.model;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Author: meyu
 * Date:   16/5/26
 * Email:  627655140@qq.com
 */
public class Video implements Serializable{

//    {
//        "id": "6039",
//            "title": "倒车入库",
//            "picUrl":"http://dev.cheyooh.com/cheyooh_driving//image/0/02dcrk.jpg",
//            "smallPicUrl":"http://dev.cheyooh.com/cheyooh_driving//image/0/02dcrk_m.jpg",
//            "videoUrl": "http://cyb-video.b0.upaiyun.com/daocheruku0.mp4",
//            "videoTime": "05:05",
//            "size":"13M"
//    }
//
    private int id;
    private String title;
    private String picUrl;
    private String smallPicUrl;
    private String videoUrl;
    private String videoTime;
    private String size;

    public static Video parse(String json){
        Video video = new Video();
        if(!TextUtils.isEmpty(json)){
            try {
                JSONObject jsonObject = new JSONObject(json);
                video.setId(jsonObject.getInt("id"));
                video.setTitle(jsonObject.getString("title"));
                video.setPicUrl(jsonObject.getString("picUrl"));
                video.setSmallPicUrl(jsonObject.getString("smallPicUrl"));
                video.setVideoUrl(jsonObject.getString("videoUrl"));
                video.setVideoTime(jsonObject.getString("videoTime"));
                video.setSize(jsonObject.getString("size"));
                return video;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSmallPicUrl() {
        return smallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", smallPicUrl='" + smallPicUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoTime='" + videoTime + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public static final String VIDEO_DATA_2= "{\n" +
            "  \"video\": [\n" +
            "    {\n" +
            "      \"id\": \"6113\",\n" +
            "      \"title\": \"上车准备\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03sczb_m.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03sczb.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/shangchezhunbei0.mp4\",\n" +
            "      \"videoTime\": \"05:05\",\n" +
            "      \"size\":\"4.1M\"\n" +
            "    },{\n" +
            "      \"id\": \"6115\",\n" +
            "      \"title\": \"安全起步\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03aqqb.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03aqqb_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/anquanqibu0.mp4\",\n" +
            "      \"videoTime\": \"04:03\",\n" +
            "      \"size\":\"10.5M\"\n" +
            "    },{\n" +
            "      \"id\": \"6117\",\n" +
            "      \"title\": \"直线行驶\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03zxxs.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03zxxs_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/zhixianxingshi0.mp4\",\n" +
            "      \"videoTime\": \"04:46\",\n" +
            "      \"size\":\"10.5M\"\n" +
            "    },{\n" +
            "      \"id\": \"6119\",\n" +
            "      \"title\": \"加减挡位\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03jjdw.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03jjdw_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/jiajiandang0.mp4\",\n" +
            "      \"videoTime\": \"02:17\",\n" +
            "      \"size\":\"4.4M\"\n" +
            "    },{\n" +
            "      \"id\": \"6121\",\n" +
            "      \"title\": \"路口直行\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03lkzx.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/03lkzx_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/lukouzhixing0.mp4\",\n" +
            "      \"videoTime\": \"05:17\",\n" +
            "      \"size\":\"13.0M\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";
    public static final String VIDEO_DATA_1= "{\n" +
            "  \"video\": [\n" +
            "    {\n" +
            "      \"id\": \"6039\",\n" +
            "      \"title\": \"倒车入库\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02dcrk.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02dcrk_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/daocheruku0.mp4\",\n" +
            "      \"videoTime\": \"05:05\",\n" +
            "      \"size\":\"13M\"\n" +
            "    },{\n" +
            "      \"id\": \"6041\",\n" +
            "      \"title\": \"侧方停车\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02cftc.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02cftc_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/cefangtingche0.mp4\",\n" +
            "      \"videoTime\": \"04:55\",\n" +
            "      \"size\":\"13.2M\"\n" +
            "    },{\n" +
            "      \"id\": \"6043\",\n" +
            "      \"title\": \"坡道定点停车和起步\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02pdddtchqb.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02pdddtchqb_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/podaodingdiantingcheheqibu0.mp4\",\n" +
            "      \"videoTime\": \"04:46\",\n" +
            "      \"size\":\"12.7M\"\n" +
            "    },{\n" +
            "      \"id\": \"6045\",\n" +
            "      \"title\": \"曲线行驶\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02qxxs.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02qxxs_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/qixianjiashi0.mp4\",\n" +
            "      \"videoTime\": \"02:52\",\n" +
            "      \"size\":\"7.7M\"\n" +
            "    },{\n" +
            "      \"id\": \"6047\",\n" +
            "      \"title\": \"直角转弯\",\n" +
            "      \"picUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02zjzw.jpg\",\n" +
            "      \"smallPicUrl\":\"http://dev.cheyooh.com/cheyooh_driving//image/0/02zjzw_m.jpg\",\n" +
            "      \"videoUrl\": \"http://cyb-video.b0.upaiyun.com/zhijiaozhuanwan0.mp4\",\n" +
            "      \"videoTime\": \"03:25\",\n" +
            "      \"size\":\"9.2M\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";
}
