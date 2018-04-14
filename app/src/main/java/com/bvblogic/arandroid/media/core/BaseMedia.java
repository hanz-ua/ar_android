package com.bvblogic.arandroid.media.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bvblogic.arandroid.R;
import com.danikula.videocache.Cache;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.FileCache;
import com.danikula.videocache.HttpProxyCache;
import com.danikula.videocache.HttpUrlSource;
import com.danikula.videocache.ProxyCacheException;

import java.io.File;

/**
 * Created by hanz on 29.03.2018.
 */

public abstract class BaseMedia {

    protected Context context;
    private RequestManager glide;
    private MediaListener mediaListener;

    public BaseMedia(Context context) {
        this.context = context;
        initGlide();
    }

    public RequestManager getGlide() {
        return glide;
    }

    public void setMediaListener(MediaListener mediaListener) {
        this.mediaListener = mediaListener;
    }

    private void initGlide() {
        glide = Glide.with(context);
    }

    protected void playGlide(String path, ImageView imageView) {
        if (mediaListener != null)
            mediaListener.onStart();
        glide.load(path)
                .apply(requestOptions)
                .transition(GenericTransitionOptions.with(R.anim.anim))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (mediaListener != null)
                            mediaListener.onFinish();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (mediaListener != null)
                            mediaListener.onFinish();
                        return false;
                    }
                })
                .into(imageView);
    }

    private RequestOptions requestOptions = new RequestOptions();

    public BaseMedia setRequestOptions(RequestOptions requestOptions) {
        this.requestOptions = requestOptions;
        return this;
    }

    protected void getThumbnail(String path, ImageView imageView) {
        if (mediaListener != null)
            mediaListener.onStart();
        glide.asBitmap().load(path)
                .apply(requestOptions)
                .transition(GenericTransitionOptions.with(R.anim.anim))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if (mediaListener != null)
                            mediaListener.onFinish();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (mediaListener != null)
                            mediaListener.onFinish();
                        return false;
                    }

                })
                .into(imageView);
    }

    private MediaPlayer mediaPlayer;
    private HttpProxyCache proxyCache;

    private void initCache(String path) throws ProxyCacheException {
        Cache cache = new FileCache(new File(context.getExternalCacheDir(), Uri.parse(path).getPath()));
        HttpUrlSource source = new HttpUrlSource(path);
        proxyCache = new HttpProxyCache(source, cache);
    }

    protected void playVideoView(String path, VideoView videoView) {
        if (mediaListener != null)
            mediaListener.onStart();

        try {
            initCache(path);
        } catch (ProxyCacheException e) {
            e.printStackTrace();
        }

        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoPath(proxyCache != null ? proxyCache.getUrl() : path);
        videoView.setOnPreparedListener(mp -> {
            mediaPlayer = mp;
            mute(mute);
            mp.setLooping(true);
            if (mediaListener != null)
                mediaListener.onFinish();
            videoView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim));
        });
        videoView.setOnErrorListener((mp, what, extra) -> {
            if (mediaListener != null)
                mediaListener.onFinish();
            return false;
        });
        videoView.start();
    }

    public abstract BaseMedia showImage(String path, ImageView imageView);

    public abstract BaseMedia showGif(String path, ImageView imageView);

    public abstract BaseMedia showVideo(String path, VideoView videoView);

    public abstract BaseMedia showThumbnail(String path, ImageView imageView);

    private boolean mute = true;

    public BaseMedia mute(boolean b) {
        mute = b;
        if (b) {
            setVolume(0);
        } else {
            setVolume(100);
        }

        return this;

    }

    private void setVolume(int amount) {
        final int max = 100;
        final double numerator = max - amount > 0 ? Math.log(max - amount) : 0;
        final float volume = (float) (1 - (numerator / Math.log(max)));

        if (mediaPlayer != null)
            mediaPlayer.setVolume(volume, volume);
    }

}
