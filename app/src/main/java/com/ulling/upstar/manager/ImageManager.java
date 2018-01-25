package com.ulling.upstar.manager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulling.upstar.R;

/**
 * Created by KILHO on 2018. 1. 25..
 */
public class ImageManager {
    private static ImageManager instance = null;
    private int errorImgId;
    private int holderImgId;
    private boolean diskCache;
    private boolean skipMemoryCache;
    private boolean animation;

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public void initGlideSet(boolean diskCache, boolean skipMemoryCache, boolean animation) {
        this.diskCache = diskCache;
        this.skipMemoryCache = skipMemoryCache;
        this.animation = animation;
    }

    public void setDefaultImg(int errorImgId, int holderImgId) {
        this.errorImgId = errorImgId;
        this.holderImgId = holderImgId;
    }

    public void setImage(Context qCon, String imageUrl, ImageView view) {
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(qCon).load(imageUrl);
        if (diskCache) {
            drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.RESULT);
        }
        drawableTypeRequest.skipMemoryCache(skipMemoryCache);
        drawableTypeRequest.error(errorImgId);
        drawableTypeRequest.placeholder(holderImgId);
        if (!animation) {
            drawableTypeRequest.dontAnimate();
        }
        drawableTypeRequest.into(view);
    }

    public void setImage(Context qCon, String imageUrl, int errorImgId, int holderImgId, ImageView view) {
        Glide.with(qCon)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(errorImgId)
                .placeholder(holderImgId)
                .dontAnimate()
                .into(view);
    }
}
