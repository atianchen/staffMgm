package com.yonyou.stm.action;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yonyou.stm.R;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.StaffService;
import com.yonyou.stm.util.DeviceUtils;
import com.yonyou.stm.util.FileUtils;
import com.yonyou.stm.util.ImgUtils;
import com.yonyou.stm.widget.PhotoViewAdapter;
import com.yonyou.stm.widget.ZoomOutPageTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsq on 2018/1/10.
 */

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RelativeLayout viewPagerContainer;

    private Staff staff;
    private StaffService staffService;

    private List<Bitmap> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoview);

        staffService = new StaffService();
        imgList = new ArrayList<Bitmap>();

        Intent intent = this.getIntent();
        if(intent.getExtras().containsKey(Constants.BUNDLE_KEY_STAFF)){
            //照片传值
            staff = (Staff)intent.getExtras().getSerializable(Constants.BUNDLE_KEY_STAFF);
            imgList.add(BitmapFactory.decodeFile(staff.getFrontImg()));
            imgList.add(BitmapFactory.decodeFile(staff.getBackImg()));
        }else{
            //列表传值
            staff = staffService.load((Long) intent.getExtras().get(Constants.BUNDLE_KEY_STAFFID));
            imgList.add(ImgUtils.base64ToBitmap(staff.getFrontImg()));
            imgList.add(ImgUtils.base64ToBitmap(staff.getBackImg()));
        }

        initViewPager();
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerContainer= (RelativeLayout) findViewById(R.id.viewPagerContainer);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                DeviceUtils.getWindowWidth(this),
                DeviceUtils.getWindowHeight(this) * 2 / 5);
        //clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。 默认情况下，clipChild被设置为true。 也就是不允许进行扩展绘制。
        viewPager.setClipChildren(false);
        viewPagerContainer.setClipChildren(false);
        viewPager.setLayoutParams(params);
        viewPager.setAdapter(new PhotoViewAdapter(this,imgList));
        //设置切换效果
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置预加载数量
        viewPager.setOffscreenPageLimit(2);
        //设置每页之间的左右间隔
        viewPager.setPageMargin(10);
        //将容器的触摸事件反馈给ViewPager
        viewPagerContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }
}
