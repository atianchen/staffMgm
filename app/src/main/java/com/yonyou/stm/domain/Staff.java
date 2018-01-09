package com.yonyou.stm.domain;

import com.baidu.ocr.sdk.model.IDCardResult;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lsq on 2018/1/9.
 */

@Entity
public class Staff extends BaseEntity{

    @Id(autoincrement = true)
    private long id;

    private String frontImg;//身份证正面（base64编码）

    private String backImg;//身份证背面（base64编码）
    
    private String addr;//地址

    private String idNumber;//身份证号

    private String birthday;//生日

    private String name;//姓名

    private String gender;//性别

    private String ethnic;//民族

    private Double workYears;//工龄

    private String dispatch;//外派单位

    private Double salary;//薪资

    private Float credit;//信用度(评星 5颗)

    private String issueAuth;//签发机关

    private String signDate;//有效期限起始

    private String expiryDate;//有效期限截止

    public Staff(){}

    public Staff(IDCardResult result, String frontImg){
        this.addr = result.getAddress().getWords();
        this.idNumber = result.getIdNumber().getWords();
        this.birthday = addSpot(result.getBirthday().getWords());
        this.name = result.getName().getWords();
        this.gender = result.getGender().getWords();
        this.ethnic = result.getEthnic().getWords();
        this.frontImg = frontImg;
    }

    @Generated(hash = 1257400501)
    public Staff(long id, String frontImg, String backImg, String addr,
            String idNumber, String birthday, String name, String gender,
            String ethnic, Double workYears, String dispatch, Double salary,
            Float credit, String issueAuth, String signDate, String expiryDate) {
        this.id = id;
        this.frontImg = frontImg;
        this.backImg = backImg;
        this.addr = addr;
        this.idNumber = idNumber;
        this.birthday = birthday;
        this.name = name;
        this.gender = gender;
        this.ethnic = ethnic;
        this.workYears = workYears;
        this.dispatch = dispatch;
        this.salary = salary;
        this.credit = credit;
        this.issueAuth = issueAuth;
        this.signDate = signDate;
        this.expiryDate = expiryDate;
    }

    public void setBackInfo(IDCardResult result,String backImg){
        this.issueAuth = result.getIssueAuthority().getWords();
        this.signDate = addSpot(result.getSignDate().getWords());
        this.expiryDate = addSpot(result.getExpiryDate().getWords());
        this.backImg = backImg;
    }

    private String addSpot(String str){
        return str.substring(0, 4)+"."+str.substring(4, 6)+"."+str.substring(6);
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getIssueAuth() {
        return this.issueAuth;
    }

    public void setIssueAuth(String issueAuth) {
        this.issueAuth = issueAuth;
    }

    public Float getCredit() {
        return this.credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDispatch() {
        return this.dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public Double getWorkYears() {
        return this.workYears;
    }

    public void setWorkYears(Double workYears) {
        this.workYears = workYears;
    }

    public String getEthnic() {
        return this.ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBackImg() {
        return this.backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getFrontImg() {
        return this.frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
