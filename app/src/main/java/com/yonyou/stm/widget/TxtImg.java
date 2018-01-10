package com.yonyou.stm.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.widget.event.OnImgClickListener;

import org.apache.commons.lang.StringUtils;

/**
 * 属性和属性值（特指图片）显示
 * Created by lsq on 2018/1/8.
 */

public class TxtImg extends LinearLayout {

    private TextView txtTitle;
    private ImageView imgView1;
    private ImageView imgView2;
    private View buttomLine;

    private boolean bottom;

    @SuppressLint("Recycle")
    public TxtImg(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.TxtImg);
        txtTitle.setText(a.getString(R.styleable.TxtImg_ti_title));
        imgView1.setImageDrawable(a.getDrawable(R.styleable.TxtImg_ti_img1));
        imgView2.setImageDrawable(a.getDrawable(R.styleable.TxtImg_ti_img2));
        bottom = a.getBoolean(R.styleable.TxtImg_ti_bottom, true);
        if (!bottom) {
            buttomLine.setVisibility(View.GONE);
        }
        a.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.txtimg, this, true);
        txtTitle = ((TextView) this.findViewById(R.id.txtimg_title));
        imgView1 = ((ImageView) this.findViewById(R.id.txtimg_img1));
        imgView2 = ((ImageView) this.findViewById(R.id.txtimg_img2));
        buttomLine = this.findViewById(R.id.txtimg_bottom);
        bottom = true;
    }

    public void setImageBitmaps(Bitmap bitmap1,Bitmap bitmap2) {
        imgView1.setImageBitmap(bitmap1);
        imgView2.setImageBitmap(bitmap2);
    }

    public void setOnImg1ClickListener(final OnImgClickListener listener){
        imgView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImgClick(v,1);
            }
        });
    }

    public void setOnImg2ClickListener(final OnImgClickListener listener){
        imgView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImgClick(v,2);
            }
        });
    }

}
