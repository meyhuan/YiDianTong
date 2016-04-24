package com.xiaoya.yidiantong.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.Question;
import com.xiaoya.yidiantong.utils.AES;
import com.xiaoya.yidiantong.view.ScrollTextView;

import java.io.IOException;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class BaseQuestionFragment extends StarterFragment{

    private TextView tvQuestion;
    private ImageView ivImage;
    private RelativeLayout layoutGuess;
    private TextView tvGuess;
    private LinearLayout layoutOptiona;
    private TextView tvOptionaIcon;
    private TextView tvOptionAContent;
    private LinearLayout layoutOptionb;
    private TextView tvOptionbIcon;
    private TextView tvOptionBContent;
    private LinearLayout layoutOptionc;
    private TextView tvOptioncIcon;
    private TextView tvOptionCContent;
    private LinearLayout layoutOptiond;
    private TextView tvOptiondIcon;
    private TextView tvOptionDContent;
    private Button btnSubmit;
    private TextView tvTip;
    private LinearLayout layoutAnalysis;
    private RelativeLayout textAdMidLayout;
    private ScrollTextView questionMiddleAdView;
    private TextView tvAnalysis;
    private RelativeLayout layoutGuessExtendsOrContract;
    private RelativeLayout layoutGuessExcontract;
    private ImageView btnGuess;
    private ImageView btnGuessExtend;
    private ImageView btnGuessContract;

    public  Question mQuestion = new Question();
    
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if(args != null){
            mQuestion = (Question) args.getSerializable("question");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        setupView();
    }

    private void setupView(){
        tvQuestion.setText(AES.decrypt(mQuestion.getQuestion()));
        tvAnalysis.setText(mQuestion.getAnalysis());
        tvOptionAContent.setText(mQuestion.getOption_a());
        tvOptionBContent.setText(mQuestion.getOption_b());
        if(!TextUtils.isEmpty(mQuestion.getOption_c())){
            tvOptionCContent.setText(mQuestion.getOption_c());
        }else {
            layoutOptionc.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(mQuestion.getOption_d())){
            tvOptionDContent.setText(mQuestion.getOption_d());
        }else {
            layoutOptiond.setVisibility(View.GONE);
        }
        setCorrectOption(mQuestion.getRightOption());
        setMedia(mQuestion.getMedia_type(), mQuestion.getMedia_content());
    }

    private void setCorrectOption(String rightOption){
        int ro = 1;
        try{
            ro = Integer.parseInt(rightOption);
        }catch (Exception e){
            LogUtils.e(e.toString());
        }

        switch (ro){
            case 1:
                tvOptionaIcon.setBackgroundResource(R.drawable.zhenti_select);
                break;
            case 2:
                tvOptionbIcon.setBackgroundResource(R.drawable.zhenti_select);
                break;
            case 3:
                tvOptioncIcon.setBackgroundResource(R.drawable.zhenti_select);
                break;
            case 4:
                tvOptiondIcon.setBackgroundResource(R.drawable.zhenti_select);
                break;
        }
    }

    private void setMedia(int mediaType, String mediaContent){
        if(mediaType == 1){
            Bitmap bit = null;
            try {
                bit = BitmapFactory.decodeStream(getActivity().getAssets().open(mediaContent));
                ivImage.setImageBitmap(bit);
                ivImage.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_multiple_question;
    }

    private void assignViews(View view) {
        tvQuestion = (TextView) view.findViewById(R.id.tv_question);
        ivImage = (ImageView) view.findViewById(R.id.iv_image);
        layoutGuess = (RelativeLayout) view.findViewById(R.id.layout_guess);
        tvGuess = (TextView) view.findViewById(R.id.tv_guess);
        layoutOptiona = (LinearLayout) view.findViewById(R.id.layout_optiona);
        tvOptionaIcon = (TextView) view.findViewById(R.id.tv_optiona_icon);
        tvOptionAContent = (TextView) view.findViewById(R.id.tv_optionA_content);
        layoutOptionb = (LinearLayout) view.findViewById(R.id.layout_optionb);
        tvOptionbIcon = (TextView) view.findViewById(R.id.tv_optionb_icon);
        tvOptionBContent = (TextView) view.findViewById(R.id.tv_optionB_content);
        layoutOptionc = (LinearLayout) view.findViewById(R.id.layout_optionc);
        tvOptioncIcon = (TextView) view.findViewById(R.id.tv_optionc_icon);
        tvOptionCContent = (TextView) view.findViewById(R.id.tv_optionC_content);
        layoutOptiond = (LinearLayout) view.findViewById(R.id.layout_optiond);
        tvOptiondIcon = (TextView) view.findViewById(R.id.tv_optiond_icon);
        tvOptionDContent = (TextView) view.findViewById(R.id.tv_optionD_content);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        tvTip = (TextView) view.findViewById(R.id.tv_tip);
        layoutAnalysis = (LinearLayout) view.findViewById(R.id.layout_analysis);
        textAdMidLayout = (RelativeLayout) view.findViewById(R.id.text_ad_mid_layout);
        questionMiddleAdView = (ScrollTextView) view.findViewById(R.id.question_middle_ad_view);
        tvAnalysis = (TextView) view.findViewById(R.id.tv_analysis);
        layoutGuessExtendsOrContract = (RelativeLayout) view.findViewById(R.id.layout_guess_extends_or_contract);
        layoutGuessExcontract = (RelativeLayout) view.findViewById(R.id.layout_guess_excontract);
        btnGuess = (ImageView) view.findViewById(R.id.btn_guess);
        btnGuessExtend = (ImageView) view.findViewById(R.id.btn_guess_extend);
        btnGuessContract = (ImageView) view.findViewById(R.id.btn_guess_contract);
    }
}
