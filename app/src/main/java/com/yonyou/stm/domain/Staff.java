package com.yonyou.stm.domain;

import com.baidu.ocr.sdk.model.IDCardResult;
import com.yonyou.stm.util.TimeUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lsq on 2018/1/9.
 */

@Entity
public class Staff extends BaseEntity{

    @Id(autoincrement = true)
    private Long id;

    private String frontImg;//身份证正面（base64编码）

    private String backImg;//身份证背面（base64编码）
    
    private String addr;//地址

    private String idNumber;//身份证号

    private Long birthday;//生日

    private String name;//姓名

    private String gender;//性别

    private String ethnic;//民族

    private Double workYears;//工龄

    private String dispatch;//外派单位

    private Double salary;//薪资

    private Float credit;//信用度(评星 5颗)

    private String issueAuth;//签发机关

    private Long signDate;//有效期限起始

    private Long expiryDate;//有效期限截止

    private String phone;//手机号码

    private String contactType;//紧急联系人类型

    private String contactTel;//紧急联系人电话

    private Long entryDate;//入职日期

    public Staff(){}

    public Staff(IDCardResult result, String frontImg){
        this.addr = result.getAddress().getWords();
        this.idNumber = result.getIdNumber().getWords();
        this.birthday = TimeUtils.strToLong(addSpot(result.getBirthday().getWords()));
        this.name = result.getName().getWords();
        this.gender = result.getGender().getWords();
        this.ethnic = result.getEthnic().getWords();
        this.frontImg = frontImg;
    }

    @Generated(hash = 141796354)
    public Staff(Long id, String frontImg, String backImg, String addr, String idNumber,
            Long birthday, String name, String gender, String ethnic, Double workYears,
            String dispatch, Double salary, Float credit, String issueAuth, Long signDate,
            Long expiryDate, String phone, String contactType, String contactTel,
            Long entryDate) {
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
        this.phone = phone;
        this.contactType = contactType;
        this.contactTel = contactTel;
        this.entryDate = entryDate;
    }

    public void setBackInfo(IDCardResult result,String backImg){
        this.issueAuth = result.getIssueAuthority().getWords();
        this.signDate = TimeUtils.strToLong(addSpot(result.getSignDate().getWords()));
        this.expiryDate = TimeUtils.strToLong(addSpot(result.getExpiryDate().getWords()));
        this.backImg = backImg;
    }

    private String addSpot(String str){
        return str.substring(0, 4)+"."+str.substring(4, 6)+"."+str.substring(6);
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Long entryDate) {
        this.entryDate = entryDate;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getSignDate() {
        return this.signDate;
    }

    public void setSignDate(Long signDate) {
        this.signDate = signDate;
    }

    public Long getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getContactType() {
        return this.contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }


}
