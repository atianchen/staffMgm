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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.widget.event.OnEditClickListener;

import org.apache.commons.lang.StringUtils;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 属性名和属性值输入
 * Created by lsq on 2018/1/7.
 */
public class TxtEdit extends LinearLayout {

    private TextView txtTitle;
    private EditText editContent;
    private View buttomLine;

    private String text;
    private boolean requried;
    private boolean bottom;
    private boolean editable;
    private String hint;

    @SuppressLint("Recycle")
    public TxtEdit(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.TxtEdit);
        requried = a.getBoolean(R.styleable.TxtEdit_te_required, false);
        text = a.getString(R.styleable.TxtEdit_te_content);
        editContent.setText(text);
        hint = a.getString(R.styleable.TxtEdit_te_hint);
        if(StringUtils.isNotBlank(hint)){
            editContent.setHint(hint);
        }
        bottom = a.getBoolean(R.styleable.TxtEdit_te_bottom, true);
        editable = a.getBoolean(R.styleable.TxtEdit_te_editable, true);
        requried = a.getBoolean(R.styleable.TxtEdit_te_required, false);
        if (!bottom) {
            buttomLine.setVisibility(View.GONE);
        }
        if(!editable){
            //editContent.setKeyListener(null);
            editContent.setFocusable(false);
            editContent.setFocusableInTouchMode(false);
        }
        if (requried) {
           txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtEdit_te_title) + "<font color='red'>*</font>"));
           txtTitle.setText(Html.fromHtml(a.getString(R.styleable.TxtEdit_te_title) + "<font color='red'>*</font>"));
        } else {
           txtTitle.setText(a.getString(R.styleable.TxtEdit_te_title));
        }
        a.recycle();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.txtedit, this, true);
        txtTitle = ((TextView) this.findViewById(R.id.txtedit_title));
        editContent = ((EditText) this.findViewById(R.id.txtedit_content));
        buttomLine = this.findViewById(R.id.txtedit_bottom);
        bottom = true;
    }

    public void setText(String text) {
        this.text = text;
        editContent.setText(text);
    }

    public String getText() {
        text = editContent.getText().toString();
        return text;
    }

    public void setOnEditClickListener(final OnEditClickListener listener) {
        editContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                listener.onClick(arg0);
            }
        });
        editContent.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                listener.onFocusChange(v,hasFocus);
            }
        });
    }
}

