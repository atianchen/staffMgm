package com.yonyou.stm.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yonyou.stm.R;

/**
 * Created by lsq on 2018/1/7.
 */

public class TxtStar extends LinearLayout {

    private TextView txtTitle;
    private RatingBar ratingBar;
    private View buttomLine;

    private boolean requried;
    private boolean bottom;
    private boolean editable;

    @SuppressLint("Recycle")
    public TxtStar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.TxtStar);
        requried = a.getBoolean(R.styleable.TxtStar_ts_required, false);
        bottom = a.getBoolean(R.styleable.TxtStar_ts_bottom, true);
        editable = a.getBoolean(R.styleable.TxtStar_ts_bottom, true);
        requried = a.getBoolean(R.styleable.TxtStar_ts_required, false);
        if (!bottom) {
            buttomLine.setVisibility(View.GONE);
        }
        if(!editable){
        }
        if (requried) {
            txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtStar_ts_title) + "<font color='red'>*</font>"));
            txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtStar_ts_title) + "<font color='red'>*</font>"));
        } else {
            txtTitle.setText(a.getString(R.styleable.TxtStar_ts_title));
        }
        a.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.txtstar, this, true);
        txtTitle = ((TextView) this.findViewById(R.id.txtstar_title));
        ratingBar = ((RatingBar) this.findViewById(R.id.txtstar_star));
        buttomLine = this.findViewById(R.id.txtstar_bottom);
        bottom = true;
    }

    public void setRatingBar(Float star){
        ratingBar.setRating(star);
    }

    public Float getRating(){
        return ratingBar.getRating();
    }
}
