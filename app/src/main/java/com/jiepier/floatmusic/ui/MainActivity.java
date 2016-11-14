package com.jiepier.floatmusic.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.adapter.MusicAdapter;
import com.jiepier.floatmusic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_play_icon)
    ImageView ivPlayIcon;
    @BindView(R.id.tv_play_title)
    TextView tvPlayTitle;
    @BindView(R.id.tv_play_artist)
    TextView tvPlayArtist;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.play_progress)
    SeekBar playProgress;
    @BindView(R.id.rl_controller)
    RelativeLayout rlController;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private boolean isPause;
    private MusicAdapter mMusicAdapter;

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initUiAndListener() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMusicAdapter);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void onPublish(int percent) {

    }

    @Override
    public void onChange(int position) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        allowBindService();
    }

    //解除绑定歌曲播放服务
    @Override
    protected void onStop() {
        super.onStop();
        allowUnBindService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @OnClick({R.id.iv_play_icon, R.id.iv_pre, R.id.iv_play, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_icon:
                break;
            case R.id.iv_pre:
                mPlayService.pre();
                break;
            case R.id.iv_play:
                if (mPlayService.isPlaying()){
                    mPlayService.pause();
                    ivPlayIcon.setImageResource(android.R.drawable.ic_media_play);
                }else {
                    mPlayService.resume();
                }
                break;
            case R.id.iv_next:
                getPlayService().next();
                break;
        }
    }
}
