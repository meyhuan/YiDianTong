package com.xiaoya.yidiantong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.smartydroid.android.starter.kit.account.AccountManager;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.smartydroid.android.starter.kit.utilities.SPUtils;
import com.smartydroid.android.starter.kit.utilities.StarterCommon;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.umeng.analytics.MobclickAgent;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.api.ApiService;
import com.xiaoya.yidiantong.ui.LoginActivity;

import java.util.Calendar;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * User  : guanhuan
 * Date  : 2016/4/6
 */
public class Helper {

    /**
     * 判断是否登录，跳转
     * @param context context
     * @return true 已登录，
     */
    public static boolean isLogin(Context context, String toastString){

        if(AccountManager.isLogin()){
            return true;
        }
        Intent intent = new Intent(context, LoginActivity.class);
        if(!TextUtils.isEmpty(toastString)){
            Utils.showToast(context,toastString);
        }
        context.startActivity(intent);
        return false;
    }


    /**
     * 显示退出对话框
     * @param context context
     */
    public static void showExitDialog(final Activity context, final boolean isExitAccount){

        boolean isCommented = (boolean) SPUtils.get(context, Constant.KEY_USER_HAS_COMMENT, false);
        final MaterialDialog materialDialog = new MaterialDialog(context);
        if(isExitAccount){
            materialDialog.setMessage(R.string.setting_is_exit_account);
        }else {
            materialDialog.setMessage(R.string.exit_tip);
        }

        if(isCommented){
            materialDialog.setNegativeButton(R.string.cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            });
            materialDialog.setPositiveButton(R.string.confirm, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                    if(isExitAccount){
                        ACache.get(context).remove(ApiService.PASSWORD);
                        ACache.get(context).remove(ApiService.USERNAME);
                        AccountManager.logout();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        context.finish();
                    }else {
                        context.finish();
                    }
                }
            });
        }else {
            materialDialog.setNegativeButton(R.string.confirm, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                    if(isExitAccount){
                        ACache.get(context).remove(ApiService.PASSWORD);
                        ACache.get(context).remove(ApiService.USERNAME);
                        AccountManager.logout();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        context.finish();

                    }else {
                        context.finish();
                    }

                }
            });
            materialDialog.setPositiveButton(R.string.comment, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                    StarterCommon.goMarket(context);
                    //保存去评论的时间
                    SPUtils.put(context, Constant.KEY_USER_COMMENT_TIME,Calendar.getInstance().getTimeInMillis());
                }
            });
        }
        materialDialog.show();
        MobclickAgent.onEvent(context, "exit_dialog");
    }

    /**
     * 通过用户是否评论来改变ExitDialog显示的内容和不同的操作
     * @param context context
     */
    public static void modifyExitAction(Context context){
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long commentTime = (long) SPUtils.get(context, Constant.KEY_USER_COMMENT_TIME, Calendar.getInstance().getTimeInMillis());
        if(currentTime - commentTime > 12000){
            SPUtils.put(context, Constant.KEY_USER_HAS_COMMENT, true);
        }else {
            //移除保存的时间，下次重新算
            SPUtils.remove(context, Constant.KEY_USER_COMMENT_TIME);
        }
    }

    public static void setCommented(Context context, boolean isCommend){
        SPUtils.put(context, Constant.KEY_USER_HAS_COMMENT, isCommend);
    }
}
