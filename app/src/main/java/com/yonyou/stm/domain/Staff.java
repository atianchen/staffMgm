package com.yonyou.stm.domain;

import com.baidu.ocr.sdk.model.IDCardResult;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lsq on 2018/1/6.
 */

@DatabaseTable(tableName = "stm_staff")
public class Staff extends BaseEntity{

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String idImg;//身份证图片base64编码

    @DatabaseField
    private String addr;//地址

    @DatabaseField
    private String idNumber;//身份证号

    @DatabaseField
    private String birthday;//生日

    @DatabaseField
    private String name;//姓名

    @DatabaseField
    private String gender;//性别

    @DatabaseField
    private String ethnic;//民族

    @DatabaseField
    private Double workYears;//工龄

    @DatabaseField
    private String dispatch;//外派单位

    @DatabaseField
    private Double salary;//薪资

    @DatabaseField
    private Float credit;//信用度(评星 5颗)

    public Staff(){}

    public Staff(IDCardResult result,String idImg){
        this.addr = result.getAddress().getWords().toString();
        this.idNumber = result.getIdNumber().getWords().toString();
        this.birthday = result.getBirthday().getWords().toString();
        this.name = result.getName().getWords().toString();
        this.gender = result.getGender().getWords().toString();
        this.ethnic = result.getEthnic().getWords().toString();
        this.idImg = idImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public Double getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Double workYears) {
        this.workYears = workYears;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }
}
