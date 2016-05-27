package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smartydroid.android.starter.kit.account.AccountManager;
import com.smartydroid.android.starter.kit.app.StarterNetworkActivity;
import com.smartydroid.android.starter.kit.helper.GlideRoundTransform;
import com.smartydroid.android.starter.kit.helper.StatusBarHelper;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.smartydroid.android.starter.kit.utilities.ViewUtils;
import com.umeng.analytics.MobclickAgent;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.api.ApiService;
import com.xiaoya.yidiantong.api.ErrorHelper;
import com.xiaoya.yidiantong.model.BaseResponse;
import com.xiaoya.yidiantong.model.User;
import com.xiaoya.yidiantong.model.UserInfo;
import com.xiaoya.yidiantong.utils.Helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends StarterNetworkActivity<UserInfo> implements View.OnClickListener{


    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final String IMAGE_UNSPECIFIED = "image/*";

    public static final String ACACHE_AVARAT_KEY= "acache_avarat_key";

    private Uri mPhotoUri;
    private User mUser;

    private RelativeLayout settingAvatarLayout;
    private ImageView settingAvatarImg;
    private RelativeLayout settingAccountLayout;
    private TextView settingAccountTv;
    private RelativeLayout settingNicknameLayout;
    private TextView settingNicknameTv;
    private RelativeLayout settingGanderLayout;
    private TextView settingGenderTv;
    private RelativeLayout settingExitAccountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void setupViews() {
        assignViews();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //用于处理显示的退出对话框
        Helper.modifyExitAction(this);
    }

    private void initView(){
        mUser = AccountManager.getCurrentAccount();
        if (mUser != null) {
            String nick = mUser.name;
            if(!TextUtils.isEmpty(nick)){
                settingNicknameTv.setText(nick);
                settingNicknameTv.setTextColor(getResources().getColor(R.color.textColorSecondary));
            }
            Glide.with(this)
                    .load(mUser.imgUrl)
                    .placeholder(R.drawable.ic_default_avatar)
                    .error(R.drawable.ic_default_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new GlideRoundTransform(mContext))
                    .into(settingAvatarImg);
            settingAccountTv.setText(mUser.userId);
            settingNicknameTv.setText(TextUtils.isEmpty(mUser.name) ? User.DEFAULT_NAME+ mUser.id : mUser.name);
            settingGenderTv.setText(setGender(mUser.sex));
        }else {
            mUser = new User();
        }
    }

    /**
     * 通过user的字段 选择性别
     * @param  sex sex
     * @return 性别
     */
    private String setGender(int sex){
        switch (sex){
            case User.MAN:
                return getResources().getString(R.string.setting_man);
            case User.WAMEN:
                return getResources().getString(R.string.setting_women);
            default:
                return getResources().getString(R.string.setting_gender_unknow);
        }
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setTitle(R.string.setting);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.setting_avatar_layout){
            showAvatarSelectDialog();
            MobclickAgent.onEvent(mContext, "setting_avatar");
        }else if(view.getId() == R.id.setting_account_layout){
            MobclickAgent.onEvent(mContext, "setting_account");
            Utils.showToast(mContext,getString(R.string.setting_toast_user_id_not_modify));
        }else if(view.getId() == R.id.setting_gander_layout){
            MobclickAgent.onEvent(mContext, "setting_sex");
            showGenderSelectDialog();
        }else if(view.getId() == R.id.setting_nickname_layout){
            MobclickAgent.onEvent(mContext, "setting_nickname");
            showNicknameInputDialog();
        }else if(view.getId() == R.id.setting_exit_account_layout){
            MobclickAgent.onEvent(mContext, "setting_exit");
            Helper.showExitDialog(this, true);
        }
    }

    /**
     * 显示修改昵称对话框
     */
    private void showNicknameInputDialog(){
        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_nickname_input_view, null);
        final EditText editText = ViewUtils.getView(view, R.id.setting_input_nickname_edt);
        materialDialog.setView(view);
        materialDialog.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(mContext, "setting_name_cancel");
                materialDialog.dismiss();
            }
        });
        materialDialog.setPositiveButton(R.string.confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(mContext, "setting_name_confirm");
                String nickname = editText.getText().toString();
                if(!TextUtils.isEmpty(nickname)){
                    materialDialog.dismiss();
                    upLoadUserInfo(nickname, mUser.sex+"");
                }else {
                    Utils.showToast(mContext, getResources().getString(R.string.setting_toast_nick_name_err));
                }
            }
        });
        materialDialog.show();
    }

    /**
     * 显示头像选择对话框
     */
    private void showAvatarSelectDialog() {
        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_avatar_select_view, null);
        ViewUtils.getView(view, R.id.setting_photo_form_take_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoUri = Uri.fromFile(getOutputMediaFile());
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
                startActivityForResult(intent, PHOTOHRAPH);
                materialDialog.dismiss();
                MobclickAgent.onEvent(mContext, "setting_take_photo");
            }
        });
        ViewUtils.getView(view, R.id.setting_photo_form_album_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTOZOOM);
                materialDialog.dismiss();
                MobclickAgent.onEvent(mContext, "setting_album");
            }
        });
        materialDialog.setView(view);
        materialDialog.show();

    }

    /**
     * 显示性别选择对话框
     */
    private void showGenderSelectDialog() {
        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setCanceledOnTouchOutside(true);
        materialDialog.setButtonLayoutVisiable(View.GONE);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_gender_picker_view, null);
        RadioButton radioButton0 = ViewUtils.getView(view, R.id.setting_gender_selcet_man_radioBtn);
        RadioButton radioButton1 = ViewUtils.getView(view, R.id.setting_gender_selcet_women_radioBtn);
        if(mUser.sex == User.WAMEN){
            radioButton1.setChecked(true);
            radioButton0.setChecked(false);
        }else {
            radioButton0.setChecked(true);
            radioButton1.setChecked(false);
        }
        ViewUtils.getView(view, R.id.setting_gender_selcet_man_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                upLoadUserInfo(mUser.name, User.MAN+"");
                MobclickAgent.onEvent(mContext, "setting_sex_man");
            }
        });
        ViewUtils.getView(view, R.id.setting_gender_selcet_women_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                upLoadUserInfo(mUser.name, User.WAMEN+"");
                MobclickAgent.onEvent(mContext, "setting_sex_women");
            }
        });
        materialDialog.setView(view);
        materialDialog.show();
    }

    /**
     * 上传用户信息
     */
    private void upLoadUserInfo(String nickname, String sex){
        Call<BaseResponse> call = ApiService.createAuthService().updateUserInfo(ApiService.APP_KEY, mUser.userId, mUser.userPW, sex, nickname);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().status.equals(ApiService.OK)){
                    getUserInfo();
                }else {
                    ErrorHelper.getInstance().handleErrCode(response.body().status);
                    dismissUnBackProgressLoading();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Utils.showToast(mContext,R.string.net_error);
                dismissUnBackProgressLoading();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 拍照
        if (requestCode == PHOTOHRAPH) {
            // 设置文件保存路径这里放在跟目录下
            startPhotoZoom(mPhotoUri);
            return;
        }
        if (data == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == PHOTOZOOM) { // 读取相册缩放图片
            startPhotoZoom(data.getData());
        } else if (requestCode == PHOTORESOULT) { // 处理结果
            Bundle extras = data.getExtras();
            if (extras == null) {
                return;
            }
            Bitmap userHeadPortrait = extras.getParcelable("data");
            File file = ACache.get(this).put(ACACHE_AVARAT_KEY,userHeadPortrait);
            upLoadHead(file);
        }
    }

    private void upLoadHead(File file) {
        if(file == null){
             return;
        }
        RequestBody avatar = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody appkey = RequestBody.create(MediaType.parse("text/plain"), ApiService.APP_KEY);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), mUser.userId);
        RequestBody userPw = RequestBody.create(MediaType.parse("text/plain"),  mUser.userPW);
        RequestBody channel = RequestBody.create(MediaType.parse("text/plain"), "umeng");
        Call<BaseResponse> call = ApiService.createUpLoadService().uploadAvatar(avatar, appkey, userId, userPw,channel);
        showUnBackProgressLoading(R.string.loading);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body().status.equals(ApiService.OK)){
                    getUserInfo();
                }else {
                    ErrorHelper.getInstance().handleErrCode(response.body().status);
                    dismissUnBackProgressLoading();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Utils.showToast(mContext,R.string.net_error);
                dismissUnBackProgressLoading();
            }
        });
    }

    /**
     * 重新获取修改后的用户信息
     */
    private void getUserInfo(){
        String userName = ACache.get(this).getAsString(ApiService.USERNAME);
        String userPW = ACache.get(this).getAsString(ApiService.PASSWORD);
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPW)){
            Call<UserInfo> userInfoCall = ApiService.createAuthService().getUserInfo(ApiService.APP_KEY, userName);
            networkQueue().enqueue(userInfoCall);
        }
    }

    @Override
    public void respondSuccess(UserInfo data) {
        super.respondSuccess(data);
        if(data.status.equals(ApiService.OK)){
            User user = data.content;
            user.userPW = ACache.get(this).getAsString(ApiService.PASSWORD);
            mUser = user;
            AccountManager.store(mUser);
            initView();
            AccountManager.notifyDataChanged();
            Utils.showToast(mContext, R.string.user_reset_success);
        }else {
            Utils.showToast(mContext, R.string.user_reset_fail);
        }
        dismissUnBackProgressLoading();
    }

    /**
     * 裁剪头像
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 96);
        intent.putExtra("outputY", 96);
        intent.putExtra("return-data", true);

        try {
            startActivityForResult(intent, PHOTORESOULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片缓存的路径
     *
     * @return File
     */
    private File getOutputMediaFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
                .format(new Date());
        File mediaFile = new File(ACache.getExternalCacheDir(this).getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }


    @Override
    public void startRequest() {
        super.startRequest();
        showUnBackProgressLoading(R.string.loading);
    }

    @Override
    public void endRequest() {
        super.endRequest();
        dismissUnBackProgressLoading();
    }

    @Override
    protected void setStatusBar() {
        StatusBarHelper.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
    }


    private void assignViews() {
        settingAvatarLayout = (RelativeLayout) findViewById(R.id.setting_avatar_layout);
        settingAvatarImg = (ImageView) findViewById(R.id.setting_avatar_img);
        settingAccountLayout = (RelativeLayout) findViewById(R.id.setting_account_layout);
        settingAccountTv = (TextView) findViewById(R.id.setting_account_tv);
        settingNicknameLayout = (RelativeLayout) findViewById(R.id.setting_nickname_layout);
        settingNicknameTv = (TextView) findViewById(R.id.setting_nickname_tv);
        settingGanderLayout = (RelativeLayout) findViewById(R.id.setting_gander_layout);
        settingGenderTv = (TextView) findViewById(R.id.setting_gender_tv);
        settingExitAccountLayout = (RelativeLayout) findViewById(R.id.setting_exit_account_layout);

        settingAvatarLayout.setOnClickListener(this);
        settingAccountLayout.setOnClickListener(this);
        settingNicknameLayout.setOnClickListener(this);
        settingGanderLayout.setOnClickListener(this);
        settingExitAccountLayout.setOnClickListener(this);
    }
}
