package com.bvblogic.arandroid.media;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bvblogic.arandroid.media.core.BaseMedia;
import com.bvblogic.arandroid.media.core.MediaListener;

/**
 * Created by hanz on 29.03.2018.
 */

public final class MediaPresenter extends BaseMedia {

    public MediaPresenter(Context activity) {
        super(activity);
    }

    public MediaPresenter initListener(MediaListener mediaListener) {
        setMediaListener(mediaListener);
        return this;
    }

    public MediaPresenter initProgressBar(View view) {
        setMediaListener(new MediaListener() {
            @Override
            public void onStart() {
                if (view != null)
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                if (view != null)
                    view.setVisibility(View.GONE);
            }
        });
        return this;
    }

    public BaseMedia showThumbnail(String path, ImageView imageView) {
        getThumbnail(path, imageView);
        return this;
    }
    @Override
    public BaseMedia showImage(String path, ImageView imageView) {
        playGlide(path, imageView);
        return this;
    }

    @Override
    public BaseMedia showGif(String path, ImageView imageView) {
        playGlide(path, imageView);
        return this;
    }


    @Override
    public BaseMedia showVideo(String path, VideoView videoView) {
        playVideoView(path, videoView);
        return this;
    }
}
