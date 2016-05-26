package com.xiaoya.yidiantong.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.multithreaddownload.CallBack;
import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.DownloadManager;
import com.aspsine.multithreaddownload.DownloadRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.Video;
import com.xiaoya.yidiantong.view.StretchVideoView;

import java.io.File;

/**
 * Author: meyu
 * Date:   16/5/26
 * Email:  627655140@qq.com
 */
public class VideoStudyDetailActivity extends StarterActivity {


    private RelativeLayout rlVideoLayout;
    private StretchVideoView vv;
    private ImageView ivDefault;
    private ImageView ivFullscreen;
    private TextView tvTitle;
    private LinearLayout llDownload;
    private ImageView ivPlayPause;
    private TextView tvDownloadTip;
    private ProgressBar pbWaiting;
    private FrameLayout flTime;
    private ProgressBar pbProgress;
    private TextView tvSize;
    private TextView tvTime;
    private ScrollView svLayout;
    private TextView tvRequest;
    private TextView tvRequestContent;
    private TextView tvCriterion;
    private TextView tvCriterionContent;
    private ImageView tvStrategy;
    private TextView tvStrategyContent;

    private Video currentVideo;
    private boolean isDownloaded = false;
    private boolean stretch_flag = false;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_study_detail);
        currentVideo = (Video) getIntent().getSerializableExtra("extra_video");
        isDownloaded = isDownloaded(currentVideo);
        initVideo();
        if (currentVideo != null && !TextUtils.isEmpty(currentVideo.getPicUrl())) {
            Glide.with(this).load(currentVideo.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivDefault);
        }
    }

    private void initVideo(){
        if (isDownloaded) {
            ivPlayPause.setImageResource(R.drawable.ic_play);
            tvDownloadTip.setVisibility(View.GONE);
            vv.setVideoURI(Uri.parse(getVideoPath(currentVideo)));
            tvTime.setText(currentVideo.getVideoTime());
            tvSize.setText(currentVideo.getSize());
            pbProgress.setVisibility(View.VISIBLE);
            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pbProgress.setMax(mp.getDuration());
                    countDownTimer = new CountDownTimer(mp.getDuration(), 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (vv.getCurrentPosition() >= vv.getDuration()) {
                                stop();
                            }
                            pbProgress.setProgress(vv.getCurrentPosition());
                        }

                        @Override
                        public void onFinish() {
                            stop();
                        }
                    };
                }
            });

        }
    }

    private boolean isDownloaded(Video video) {
        if (video == null) {
            return false;
        }
        File file = new File(getVideoPath(video));
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private String getVideoPath(Video video) {
        if (video != null) {
            return new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES) + File.separator + video.getTitle()).toString();
        }
        return null;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        System.out.println("screenHeight  " + screenHeight);
        System.out.println("screenWidth  " + screenWidth);
        if (!stretch_flag) {
            //切换成竖屏
            showToolBar();
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(screenHeight, Utils.dip2px(this, 160));
            rlVideoLayout.setLayoutParams(params1);
            Toast.makeText(getApplicationContext(), "竖屏", Toast.LENGTH_LONG).show();
        } else {
            //切换成横屏
            hintToolBar();
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
            rlVideoLayout.setLayoutParams(params1);
            Toast.makeText(getApplicationContext(), "横屏", Toast.LENGTH_LONG).show();
        }
    }


    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_video_layout:
                    if (isDownloaded) {
                        if (vv.isPlaying()) {
                            stop();
                        } else {
                            play();
                        }
                    } else {
                        initDownloader(currentVideo);
                    }
                    break;
                case R.id.iv_fullscreen:
                    if (stretch_flag) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        stretch_flag = false;
                    } else {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        stretch_flag = true;
                    }

                    break;
            }
        }
    }

    private void play() {
        vv.start();
        ivPlayPause.setVisibility(View.INVISIBLE);
        if(countDownTimer != null ) countDownTimer.start();
    }

    private void stop() {
        vv.pause();
        ivPlayPause.setVisibility(View.VISIBLE);

        if(countDownTimer != null ) countDownTimer.cancel();
    }


    private void initDownloader(Video video) {
        if (video == null) {
            return;
        }
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);

        // first: build a DownloadRequest:
        final DownloadRequest request = new DownloadRequest.Builder()
                .setTitle(video.getTitle())
                .setUri(video.getVideoUrl())
                .setFolder(getExternalFilesDir(Environment.DIRECTORY_MOVIES))
                .build();

        // download:
        // the tag here, you can simply use download uri as your tag;
        DownloadManager.getInstance().download(request, video.getId() + "", new CallBack() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onConnecting() {

            }

            @Override
            public void onConnected(long total, boolean isRangeSupport) {

            }

            @Override
            public void onProgress(long finished, long total, int progress) {
                pbProgress.setProgress(progress);
            }

            @Override
            public void onCompleted() {
                isDownloaded = true;
                initVideo();
                stop();
            }

            @Override
            public void onDownloadPaused() {

            }

            @Override
            public void onDownloadCanceled() {

            }

            @Override
            public void onFailed(DownloadException e) {

            }
        });
    }


    @Override
    protected void setupViews() {
        assignViews();
    }


    private void assignViews() {
        rlVideoLayout = (RelativeLayout) findViewById(R.id.rl_video_layout);
        vv = (StretchVideoView) findViewById(R.id.vv);
        ivDefault = (ImageView) findViewById(R.id.iv_default);
        ivFullscreen = (ImageView) findViewById(R.id.iv_fullscreen);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        llDownload = (LinearLayout) findViewById(R.id.ll_download);
        ivPlayPause = (ImageView) findViewById(R.id.iv_play_pause);
        tvDownloadTip = (TextView) findViewById(R.id.tv_download_tip);
        pbWaiting = (ProgressBar) findViewById(R.id.pb_waiting);
        flTime = (FrameLayout) findViewById(R.id.fl_time);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        tvSize = (TextView) findViewById(R.id.tv_size);
        tvTime = (TextView) findViewById(R.id.tv_time);
        svLayout = (ScrollView) findViewById(R.id.sv_layout);
        tvRequest = (TextView) findViewById(R.id.tv_request);
        tvRequestContent = (TextView) findViewById(R.id.tv_request_content);
        tvCriterion = (TextView) findViewById(R.id.tv_criterion);
        tvCriterionContent = (TextView) findViewById(R.id.tv_criterion_content);
        tvStrategy = (ImageView) findViewById(R.id.tv_strategy);
        tvStrategyContent = (TextView) findViewById(R.id.tv_strategy_content);

        MyOnClickListener myOnClickListener = new MyOnClickListener();
        rlVideoLayout.setOnClickListener(myOnClickListener);
        ivFullscreen.setOnClickListener(myOnClickListener);

    }


}
