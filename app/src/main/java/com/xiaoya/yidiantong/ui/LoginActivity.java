package com.xiaoya.yidiantong.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterNetworkActivity;
import com.smartydroid.android.starter.kit.helper.StatusBarHelper;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.api.ApiService;
import com.xiaoya.yidiantong.api.ErrorHelper;
import com.xiaoya.yidiantong.model.BaseResponse;
import com.xiaoya.yidiantong.utils.Md5Tool;

import retrofit2.Call;

public class LoginActivity extends StarterNetworkActivity<BaseResponse> implements View.OnClickListener{


    private TextInputLayout containerRegisterUserName;
    private TextInputEditText editRegiserUserName;
    private TextInputLayout containerRegisterPassword;
    private TextInputEditText editRegiserPassword;
    private Button loginConfirmRegisterBtn;
    private TextView loginPhoneRegisterBtn;
    private TextView loginForgetPasswordBtn;

    private String mUserName;
    private String mUserPW;


    String loadingString;
    String loginSuccessString;
    String loginFailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAddToolBar = false;
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void setupViews() {
        loadingString = getString(R.string.loading);
        loginSuccessString = getString(R.string.login_success);
        loginFailString = getString(R.string.login_fail);

        containerRegisterUserName = (TextInputLayout) findViewById(R.id.container_register_user_name);
        editRegiserUserName = (TextInputEditText) findViewById(R.id.edit_regiser_user_name);
        containerRegisterPassword = (TextInputLayout) findViewById(R.id.container_register_password);
        editRegiserPassword = (TextInputEditText) findViewById(R.id.edit_regiser_password);
        loginPhoneRegisterBtn = (TextView) findViewById(R.id.login_phone_register_btn);
        loginConfirmRegisterBtn = (Button) findViewById(R.id.login_confirm_register_btn);
        loginForgetPasswordBtn = (TextView) findViewById(R.id.login_forget_password_btn);


        loginForgetPasswordBtn.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );   //下划线
        loginForgetPasswordBtn.getPaint().setAntiAlias(true);//抗锯齿

        loginPhoneRegisterBtn.setOnClickListener(this);
        loginConfirmRegisterBtn.setOnClickListener(this);
        loginForgetPasswordBtn.setOnClickListener(this);

        mUserName = ACache.get(this).getAsString(ApiService.USERNAME);
        editRegiserUserName.setText(mUserName);
        addThirdLoginFragment();
    }

    private void addThirdLoginFragment(){
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.login_third_layout, new ThridLoginFragment())
//                .commit();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_phone_register_btn){
        }else if(view.getId() == R.id.login_confirm_register_btn){
            doLogin();
        }else if(view.getId() == R.id.login_forget_password_btn){
        }
    }

    private boolean validInfo(){
        mUserName = editRegiserUserName.getText().toString();
        mUserPW = editRegiserPassword.getText().toString();

        if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mUserPW)){
            return false;
        }
        return true;
    }


    private void doLogin(){
        if(validInfo()){
            Call<BaseResponse> userCall = ApiService.createAuthService().login(ApiService.APP_KEY, mUserName, Md5Tool.encryptPW(mUserPW));
            networkQueue().enqueue(userCall);
        }else {
            Utils.showToast(mContext,getResources().getString(R.string.register_input_empty_error));
        }
    }

    /**
     * 网络请求开始回调成功
     */
    @Override
    public void respondSuccess(BaseResponse data) {
        if(data.status.equals(ApiService.OK)){
            Utils.showToast(this,getString(R.string.login_success));
            //保存加密后的密码
            ACache.get(this).put(ApiService.PASSWORD,  Md5Tool.encryptPW(mUserPW));
            ACache.get(this).put(ApiService.USERNAME,  mUserName);
            Bundle bundle = new Bundle();
            finish();
        }else {
            ErrorHelper.getInstance().handleErrCode(data.status);
        }
    }




    @Override
    protected void setStatusBar() {
        StatusBarHelper.setColor(this, getResources().getColor(R.color.nav_status_bar_color));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
