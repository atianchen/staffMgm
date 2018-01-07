package com.yonyou.stm.action;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yonyou.stm.R;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.StaffService;
import com.yonyou.stm.widget.TxtEdit;
import com.yonyou.stm.widget.TxtStar;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by lsq on 2018/1/7.
 */

public class StaffSetActivity extends AppCompatActivity {

    private StaffService staffService;
    private Staff staff;

    private TxtEdit workYears;
    private TxtEdit dispatch;
    private TxtEdit salary;
    private TxtStar credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_set);
        Intent intent = this.getIntent();
        staff = (Staff)intent.getExtras().getSerializable(Constants.BUNDLE_KEY_STAFF);
        init();
    }

    public void init(){
        ((TxtEdit)findViewById(R.id.edit_name)).setText(staff.getName());
        ((TxtEdit)findViewById(R.id.edit_idNumber)).setText(staff.getIdNumber());
        ((TxtEdit)findViewById(R.id.edit_gender)).setText(staff.getGender());
        ((TxtEdit)findViewById(R.id.edit_ethnic)).setText(staff.getEthnic());
        ((TxtEdit)findViewById(R.id.edit_birthday)).setText(staff.getBirthday());
        ((TxtEdit)findViewById(R.id.edit_addr)).setText(staff.getAddr());
        workYears = (TxtEdit) findViewById(R.id.edit_workYears);
        if(staff.getWorkYears() != null) {
            workYears.setText(staff.getWorkYears().toString());
        }
        dispatch = (TxtEdit)findViewById(R.id.edit_dispatch);
        if(StringUtils.isNotBlank(staff.getDispatch())) {
            dispatch.setText(staff.getDispatch());
        }
        salary = (TxtEdit)findViewById(R.id.edit_salary);
        if(staff.getSalary() != null) {
            salary.setText(staff.getSalary().toString());
        }
        credit = (TxtStar) findViewById(R.id.edit_credit);
        if(staff.getCredit() != null) {
            credit.setRatingBar(staff.getCredit());
        }
        ((Button)findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        ((Button)findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaffSetActivity.this.finish();
            }
        });
    }

    private void save(){
        if(staffService ==null){
            staffService = new StaffService(StaffSetActivity.this);
        }
        if(!isNumeric(workYears.getText())){
            Toast.makeText(this.getApplicationContext(), "工龄请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isNumeric(salary.getText())){
            Toast.makeText(this.getApplicationContext(), "薪资请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        staff.setWorkYears(Double.parseDouble(workYears.getText()));
        staff.setDispatch(dispatch.getText());
        staff.setSalary(Double.parseDouble(salary.getText()));
        staff.setCredit(credit.getRating());
        staffService.save(staff);
        Toast.makeText(this.getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]*)?$");
        return pattern.matcher(str).matches();
    }

}
