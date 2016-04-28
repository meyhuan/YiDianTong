package com.xiaoya.yidiantong.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/3/31.
 */
public class Md5Tool {

    /**
     *
     * @param s:要进行加密的字符串
     * @return  字符串的md5值
     */
    public static String getMd5(String s) {
        char hexChar[] = {'0', '1', '2', '3', '4', '5', '6',
                '7', '8' , '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        //md5加密算法的加密对象为字符数组，这里是为了得到加密的对象
        byte[] b = s.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            byte[] b2 = md.digest();// 进行加密并返回字符数组
            char str[] = new char[b2.length << 1];
            int len = 0;
            //将字符数组转换成十六进制串，形成最终的密文
            for (int i = 0; i < b2.length; i++) {
                byte val = b2[i];
                str[len++] = hexChar[(val >>> 4) & 0xf];
                str[len++] = hexChar[val & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 以下计算这么多，其实就是为了获取 "@#23$%asd"这个字符串,这样处理，不以明文显示，有一定的防破解
     *
     * @param text 要附加的值
     * */
    public static String getCipherCode(String text) {
        final int code_order[] = { 0x23, 0x24, 0x25, 0x32, 0x33, 0x40, 0x61, 0x64, 0x73 };
        final int order[] = { 1, 4, 5, 2, 3, 0, 6, 8, 7 };
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < order.length; i++) {
            for (int j = 0; j < order.length; j++) {
                if (i == order[j]) {
                    builder.append((char) code_order[j]);
                    break;
                }
            }
        }
        return builder.toString();
    }

    /**
     * 加密密码
     * @param pw pw
     * @return String
     */
    public static String encryptPW(String pw) {
        String encrypted = Md5Tool.getMd5(Md5Tool.getCipherCode(pw));
        if(TextUtils.isEmpty(pw) || TextUtils.isEmpty(encrypted)){
            return null;
        }
        return encrypted.toLowerCase();
    }
}
