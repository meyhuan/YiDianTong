package com.xiaoya.yidiantong.utils;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.xiaoya.yidiantong.model.TraficSignAndMarkModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TraficSignFileUtil {
    public static final String CATEGORY_TXT_PATH = "traficSign/desc.txt";
    public static final String ROOT = "traficSign";
    private static TraficSignFileUtil fileUtil;
    private Context mContext;

    private TraficSignFileUtil(Context paramContext) {
        this.mContext = paramContext;
    }

    /**
     * 读取一行数据，并且封装为一个对象返回
     *
     * @param lineString lineString
     * @return TraficSignAndMarkModel
     */
    private TraficSignAndMarkModel crateTraficSignAndMarkModel(String lineString) {
        TraficSignAndMarkModel localTraficSignAndMarkModel = new TraficSignAndMarkModel();
        String[] dataArr = lineString.split("，");
        for (int i = 0; i < dataArr.length; i++) {
            String[] arrayOfString = dataArr[i].split("=");
            String key = arrayOfString[0];
            String value = arrayOfString[1];
            if (key.trim().contains("name")) {
                localTraficSignAndMarkModel.setName(value);
            } else if (key.trim().equalsIgnoreCase("id")) {
                localTraficSignAndMarkModel.setId(Integer.parseInt(value.trim()));
            } else if (key.trim().equalsIgnoreCase("size")) {
                localTraficSignAndMarkModel.setSize(Integer.parseInt(value.trim()));
            } else if (key.trim().equalsIgnoreCase("picName")) {
                localTraficSignAndMarkModel.setPicName("traficSign/" + localTraficSignAndMarkModel.getId() + "/" + value.trim());
            }
        }
        return localTraficSignAndMarkModel;
    }

    private TraficSignAndMarkModel crateTraficSignAndMarkModel(String lineString, int id) {
        TraficSignAndMarkModel localTraficSignAndMarkModel = new TraficSignAndMarkModel();
        String[] dataArr = lineString.split("，[a-z]");
        for (int i = 0; i < dataArr.length; i++) {
            String[] arrayOfString = dataArr[i].split("=");
            String key = arrayOfString[0];
            String value = arrayOfString[1];
            if (key.trim().equals("ame")) {
                localTraficSignAndMarkModel.setName(value);
            } else if (key.trim().equalsIgnoreCase("ontent")) {
                localTraficSignAndMarkModel.setContent(value.trim());
            } else if (key.trim().equalsIgnoreCase("picName")) {
                localTraficSignAndMarkModel.setPicName("traficSign/" + id + "/" + value.trim());
            }
        }
        return localTraficSignAndMarkModel;
    }

    public static TraficSignFileUtil newInstance(Context context) {
        if (fileUtil == null)
            fileUtil = new TraficSignFileUtil(context);
        return fileUtil;
    }

    /**
     * 根据一个ID获取他的整个数组数据
     *
     * @param id id
     * @return id 对应的数据
     */
    public ArrayList<TraficSignAndMarkModel> getTraficSignByCategoryId(int id) {
        ArrayList<TraficSignAndMarkModel> localArrayList = new ArrayList<>();
        Object localObject = "traficSign/" + id + "/desc.txt";
        try {
            localObject = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open((String) localObject)));
            while (true) {
                String str = ((BufferedReader) localObject).readLine();
                if (str == null)
                    break;
                localArrayList.add(crateTraficSignAndMarkModel(str, id));
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            return localArrayList;
        }
        return localArrayList;
    }

    /**
     * 从一个一个文件中读取一个一行数据
     *
     * @param fileName fileName
     * @return ArrayList<TraficSignAndMarkModel>
     */
    public ArrayList<TraficSignAndMarkModel> readTraficCategory(String fileName) {
        ArrayList<TraficSignAndMarkModel> localArrayList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open(fileName)));
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null)
                    break;
                localArrayList.add(crateTraficSignAndMarkModel(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return localArrayList;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return localArrayList;
    }

    public Bitmap getBitmapFromAssets(String assetPath){
        if(TextUtils.isEmpty(assetPath)){
            return null;
        }
        try {
            return BitmapFactory.decodeStream(mContext.getAssets().open(assetPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}