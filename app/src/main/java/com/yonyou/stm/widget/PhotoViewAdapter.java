package com.yonyou.stm.widget;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yonyou.stm.R;
import com.yonyou.stm.action.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2018/1/10.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private PhotoViewActivity activity;
    private List<ImageView> views;

    public PhotoViewAdapter(PhotoViewActivity activity, List<Bitmap> bitmaps){
        this.activity = activity;
        this.views = new ArrayList<ImageView>();
        for(Bitmap b:bitmaps){
            ImageView imageView = new ImageView(activity);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(b);
            views.add(imageView);
        }
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ImageView)object);
    }
}
