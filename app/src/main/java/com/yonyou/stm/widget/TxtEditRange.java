package com.yonyou.stm.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.widget.event.OnEditClickListener;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 属性和属性值输入（区间）
 * Created by lsq on 2018/1/9.
 */

public class TxtEditRange extends LinearLayout {

    private TextView txtTitle;
    private EditText content1;
    private EditText content2;
    private View buttomLine;

    private String text;
    private boolean requried;
    private boolean bottom;
    private boolean editable;
    private String hint;

    @SuppressLint("Recycle")
    public TxtEditRange(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.TxtEditRange);
        requried = a.getBoolean(R.styleable.TxtEditRange_ter_required, false);
        bottom = a.getBoolean(R.styleable.TxtEditRange_ter_bottom, true);
        if (!bottom) {
            buttomLine.setVisibility(View.GONE);
        }
        if (requried) {
            txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtEditRange_ter_title) + "<font color='red'>*</font>"));
            txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtEditRange_ter_title) + "<font color='red'>*</font>"));
        } else {
            txtTitle.setText(a.getString(R.styleable.TxtEditRange_ter_title));
        }
        content1.setText(a.getString(R.styleable.TxtEditRange_ter_content1));
        content2.setText(a.getString(R.styleable.TxtEditRange_ter_content2));
        a.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.txteditrange, this, true);
        txtTitle = ((TextView) this.findViewById(R.id.ter_title));
        content1 = ((EditText) this.findViewById(R.id.ter_content1));
        content2 = ((EditText) this.findViewById(R.id.ter_content2));
        buttomLine = this.findViewById(R.id.ter_bottom);
        bottom = true;
    }

    /**
     * 设置日期区间
     * @param startDate 开始时间 2018.01.01
     * @param endDate 结束时间 2018.02.01
     */
    public void setRange(String startDate,String endDate){
        content1.setText(startDate);
        content2.setText(endDate);
    }

    public void setOnEdit1ClickListener(final OnEditClickListener listener) {
      content1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                listener.onClick(arg0);
            }
        });
        content1.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                listener.onFocusChange(v,hasFocus);
            }
        });
    }

    public void setOnEdit2ClickListener(final OnEditClickListener listener) {
       content2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                listener.onClick(arg0);
            }
        });
        content2.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                listener.onFocusChange(v,hasFocus);
            }
        });
    }

    public String getText1(){
        return content1.getText().toString();
    }

    public String getText2(){
        return content2.getText().toString();
    }

}
