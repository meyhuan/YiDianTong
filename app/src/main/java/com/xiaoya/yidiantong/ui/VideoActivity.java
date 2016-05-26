package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.ViewUtils;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends StarterActivity {

    private RecyclerView recyclerView;

    private List<Video> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    private void initData(){
        try {
            String data = Video.VIDEO_DATA_1;
            if(App.getCurrentSubject() == 3){
                data = Video.VIDEO_DATA_2;
            }
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("video");
            for (int i = 0; i < jsonArray.length(); i++){
                datas.add(Video.parse(jsonArray.get(i).toString()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setupViews() {
        recyclerView = ViewUtils.getView(this, R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        initData();
        recyclerView.setAdapter(new VideoGridAdapt());

    }


    class VideoGridAdapt extends RecyclerView.Adapter<VideoViewHolder>{

        @Override
        public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_video_study, null);
            return new VideoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(VideoViewHolder holder, int position) {
            final Video video = datas.get(position);

            holder.tvTitle.setText(video.getTitle());
            holder.tvTime.setText(video.getVideoTime());
            Glide.with(mContext).load(video.getSmallPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv);
            holder.ivLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoStudyDetailActivity.class);
                    intent.putExtra("extra_video", video);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }


    class VideoViewHolder extends RecyclerView.ViewHolder{
        private FrameLayout ivLayout;
        private ImageView iv;
        private TextView tvTime;
        private TextView tvTitle;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ivLayout = (FrameLayout) itemView.findViewById(R.id.iv_layout);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
