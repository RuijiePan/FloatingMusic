package com.jiepier.floatmusic.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.base.BaseAdapter;
import com.jiepier.floatmusic.base.BaseViewHolder;
import com.jiepier.floatmusic.bean.Music;
import com.jiepier.floatmusic.bean.MusicEntry;
import com.jiepier.floatmusic.util.ImageTools;
import com.jiepier.floatmusic.util.MusicIconLoader;
import com.jiepier.floatmusic.util.MusicUtil;

import java.util.List;

/**
 * Created by panruijiesx on 2016/11/14.
 */

public class MusicAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private int mPlayingPosition;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list_item,parent,false);
        return new BaseViewHolder(item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        Music music = MusicUtil.sMusicList.get(position);

        if (mPlayingPosition == position){
            holder.setVisibility(R.id.music_list_selected,true);
        }else {
            holder.setVisibility(R.id.music_list_selected,false);
        }

        Bitmap icon = MusicIconLoader.getInstance()
                .load(music.getImage());
        icon = icon == null? ImageTools.scaleBitmap(R.mipmap.ic_launcher):ImageTools.scaleBitmap(icon);

        holder.setImageBitmap(R.id.music_list_icon,icon)
                .setText(R.id.tv_music_list_title,music.getTitle())
                .setText(R.id.tv_play_artist,music.getArtist());
    }

    @Override
    public int getItemCount() {
        return MusicUtil.sMusicList == null ? 0 : MusicUtil.sMusicList.size();
    }

    public void setPlayingPosition(int position) {
        mPlayingPosition = position;
    }

    public int getPlayingPosition() {
        return mPlayingPosition;
    }
}
