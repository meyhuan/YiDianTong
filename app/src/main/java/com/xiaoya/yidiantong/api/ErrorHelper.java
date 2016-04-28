package com.xiaoya.yidiantong.api;

import android.content.Context;
import android.text.TextUtils;

import com.smartydroid.android.starter.kit.app.StarterKitApp;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.xiaoya.yidiantong.R;


/**
 * User  : guanhuan
 * Date  : 2016/4/5
 * 处理服务器返回的错误码
 */
public class ErrorHelper {

    private ErrorHelper(){}

    private static ErrorHelper instance ;

    public static  ErrorHelper getInstance(){
        if(instance == null){
            return new ErrorHelper();
        }
        return instance;
    }

    public void handleErrCode(String code){
        if(TextUtils.isEmpty(code)){
            return;
        }
        int c;
        try{
            c = Integer.parseInt(code);
        }catch (Exception e){
            c = -1;
        }
        Context context =  StarterKitApp.appContext();
        if(c == -25){
            Utils.showToast(context, getString(R.string.pw_error));
        }else if(c == -24){
            Utils.showToast(context, getString(R.string.user_not_exist));
        }else if(c == -11){
            Utils.showToast(context, getString(R.string.user_existed));
        }else if(c == -8){
            Utils.showToast(context, getString(R.string.pw_is_not_empty));
        }else if(c == -7){
            Utils.showToast(context,R.string.user_name_not_empty);
        }else{
            Utils.showToast(context, R.string.unknow_error);
        }
    }

    private String getString(int stringID){
       return StarterKitApp.appContext().getResources().getString(stringID);
    }


}
