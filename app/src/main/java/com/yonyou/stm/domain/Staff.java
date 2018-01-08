package com.yonyou.stm.domain;

import com.baidu.ocr.sdk.model.IDCardResult;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yonyou.stm.service.LogService;

/**
 * Created by lsq on 2018/1/6.
 */

@DatabaseTable(tableName = "stm_staff")
public class Staff extends BaseEntity{

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String frontImg;//身份证正面（base64编码）

    @DatabaseField
    private String backImg;//身份证背面（base64编码）

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

    @DatabaseField
    private String issueAuth;//签发机关

    @DatabaseField
    private String signDate;//有效期限起始

    @DatabaseField
    private String expiryDate;//有效期限截止

    public Staff(){}

    public Staff(IDCardResult result,String frontImg){
        this.addr = result.getAddress().getWords();
        this.idNumber = result.getIdNumber().getWords();
        this.birthday = result.getBirthday().getWords();
        this.name = result.getName().getWords();
        this.gender = result.getGender().getWords();
        this.ethnic = result.getEthnic().getWords();
        this.frontImg = frontImg;
    }

    public void setBackInfo(IDCardResult result,String backImg){
        this.issueAuth = result.getIssueAuthority().getWords();
        this.signDate = result.getSignDate().getWords();
        this.expiryDate = result.getExpiryDate().getWords();
        this.backImg = backImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
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

    public String getIssueAuth() {
        return issueAuth;
    }

    public void setIssueAuth(String issueAuth) {
        this.issueAuth = issueAuth;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }
}
