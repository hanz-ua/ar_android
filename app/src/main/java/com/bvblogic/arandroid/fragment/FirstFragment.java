package com.bvblogic.arandroid.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.bumptech.glide.request.RequestOptions;
import com.bvblogic.arandroid.R;
import com.bvblogic.arandroid.fragment.core.BaseFragment;
import com.bvblogic.arandroid.media.MediaPresenter;
import com.bvblogic.arandroid.media.core.BaseMedia;
import com.bvblogic.arandroid.navigator.core.ResourceManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hanz on 24.03.2018.
 */

@EFragment(R.layout.fragment_first)
public class FirstFragment extends BaseFragment {

    @Override
    public void backPressed() {
        super.backPressed();
    }

    @ViewById(R.id.imageView)
    ImageView imageView;

    @ViewById(R.id.videoView)
    VideoView videoView;

    @ViewById(R.id.progress_bar)
    ProgressBar progressBar;


    @AfterViews
    public void initToolbar() {
        navigatorManager.
                getMainManager(this).
                initToolbar(ResourceManager.ToolbarId.SIMPLE,
                        R.string.app_name);
    }

    @Click(R.id.image)
    public void clickImage() {
        new MediaPresenter(getBaseActivity()).
                initProgressBar(progressBar).
                setRequestOptions(
                        new RequestOptions().
                                override(50, 50)).
                showThumbnail(
                        "https://dtaymlksrk6ng.cloudfront.net/c3b5b237-dd2b-455a-b7cf-2dbdb38a64ff",
                        imageView
                );
    }

    @Click(R.id.gif)
    public void clickGit() {
        new MediaPresenter(getBaseActivity()).initProgressBar(progressBar).setRequestOptions(new RequestOptions().override(50, 50)).showThumbnail(
                "https://dtaymlksrk6ng.cloudfront.net/ecards/Happy+Bday+(2).gif",
                imageView
        );

    }

    @Click(R.id.video)
    public void clickVideo() {
        BaseMedia baseMedia = new MediaPresenter(getBaseActivity()).initProgressBar(progressBar).showVideo(
                "https://s3-us-west-2.amazonaws.com/assets-erp-whapps-qa/ecards/Happy+BdayVid.mp4",
                videoView);

//        play("https://dtaymlksrk6ng.cloudfront.net/d20a9bcd-036b-41c5-ad4e-c29d17610e23");

        //  baseMedia.mute(false);
    }

}
