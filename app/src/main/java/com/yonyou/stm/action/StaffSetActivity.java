package com.yonyou.stm.action;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.yonyou.stm.R;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.StaffService;
import com.yonyou.stm.util.FileUtils;
import com.yonyou.stm.util.ImgUtils;
import com.yonyou.stm.widget.TxtEdit;
import com.yonyou.stm.widget.TxtEditRange;
import com.yonyou.stm.widget.TxtImg;
import com.yonyou.stm.widget.TxtStar;
import com.yonyou.stm.widget.event.OnEditClickListener;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Created by lsq on 2018/1/7.
 */

public class StaffSetActivity extends AppCompatActivity implements OnEditClickListener{

   private StaffService staffService;
    private Staff staff;

    private TxtImg img;
    private TxtEdit name;
    private TxtEdit idNumber;
    private TxtEdit gender;
    private TxtEdit ethnic;
    private TxtEdit birthday;
    private TxtEdit addr;
    private TxtEdit issueAuth;
    private TxtEditRange limitDate;
    private TxtEdit workYears;
    private TxtEdit dispatch;
    private TxtEdit salary;
    private TxtStar credit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_set);
      staffService = new StaffService();
        Intent intent = this.getIntent();
        if(intent.getExtras().containsKey(Constants.BUNDLE_KEY_STAFF)){
            //照片传值
            staff = (Staff)intent.getExtras().getSerializable(Constants.BUNDLE_KEY_STAFF);
            staff.setFrontImg(ImgUtils.getBase64(this, FileUtils.getFileUri(this,new File(staff.getFrontImg()))));
            staff.setBackImg(ImgUtils.getBase64(this, FileUtils.getFileUri(this,new File(staff.getBackImg()))));
        }else{
            //列表传值
           staff = staffService.load((Integer) intent.getExtras().get(Constants.BUNDLE_KEY_STAFFID));
        }

        init();
    }

    public void init(){
        img = ((TxtImg)findViewById(R.id.edit_img));
        name = ((TxtEdit)findViewById(R.id.edit_name));
        idNumber = ((TxtEdit)findViewById(R.id.edit_idNumber));
        gender = ((TxtEdit)findViewById(R.id.edit_gender));
        ethnic = ((TxtEdit)findViewById(R.id.edit_ethnic));
        birthday = ((TxtEdit)findViewById(R.id.edit_birthday));
        addr = ((TxtEdit)findViewById(R.id.edit_addr));
        issueAuth = ((TxtEdit)findViewById(R.id.edit_issueAuth));
        limitDate = ((TxtEditRange)findViewById(R.id.edit_limitDate));
        workYears = (TxtEdit) findViewById(R.id.edit_workYears);
        dispatch = (TxtEdit)findViewById(R.id.edit_dispatch);
        salary = (TxtEdit)findViewById(R.id.edit_salary);
        credit = (TxtStar) findViewById(R.id.edit_credit);
        setValue();
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
        birthday.setOnEditClickListener(this);
        limitDate.setOnEdit1ClickListener(this);
        limitDate.setOnEdit2ClickListener(this);
    }

    private void setValue(){
        img.setImageBitmaps(ImgUtils.base64ToBitmap(staff.getFrontImg()),ImgUtils.base64ToBitmap(staff.getBackImg()));
        name.setText(staff.getName());
        idNumber.setText(staff.getIdNumber());
        gender.setText(staff.getGender());
        ethnic.setText(staff.getEthnic());
        birthday.setText(staff.getBirthday());
        addr.setText(staff.getAddr());
        issueAuth.setText(staff.getIssueAuth());
        limitDate.setRange(staff.getSignDate(),staff.getExpiryDate());
        if(staff.getWorkYears() != null) {
            workYears.setText(staff.getWorkYears().toString());
        }
        if(StringUtils.isNotBlank(staff.getDispatch())) {
            dispatch.setText(staff.getDispatch());
        }
        if(staff.getSalary() != null) {
            salary.setText(staff.getSalary().toString());
        }
        if(staff.getCredit() != null) {
            credit.setRatingBar(staff.getCredit());
        }
    }

    private void save(){
        if(!isNumeric(workYears.getText())){
            Toast.makeText(this.getApplicationContext(), "工龄请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isNumeric(salary.getText())){
            Toast.makeText(this.getApplicationContext(), "薪资请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        staff.setName(name.getText());
        staff.setIdNumber(idNumber.getText());
        staff.setGender(gender.getText());
        staff.setEthnic(ethnic.getText());
        staff.setBirthday(birthday.getText());
        staff.setAddr(addr.getText());
        staff.setIssueAuth(issueAuth.getText());
        staff.setSignDate(limitDate.getText1());
        staff.setExpiryDate(limitDate.getText2());
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

    @Override
    public void onClick(View arg0) {
        showDatePickDlg(arg0);
    }
    @Override
    public void onFocusChange(View arg0, boolean hasFocus) {
        if (hasFocus) {
            showDatePickDlg(arg0);
        }
    }

    protected void showDatePickDlg(final View arg0) {
        String[] date = ((EditText)arg0).getText().toString().split("\\.");
        DatePickerDialog datePickerDialog = new DatePickerDialog(StaffSetActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DecimalFormat df= new DecimalFormat("00");
                ((EditText)arg0).setText(year + "." + df.format(monthOfYear+1) + "." + df.format(dayOfMonth));
            }
        }, Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
        datePickerDialog.show();
    }
}
