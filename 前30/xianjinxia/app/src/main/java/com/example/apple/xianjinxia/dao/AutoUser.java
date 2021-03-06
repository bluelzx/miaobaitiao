package com.example.apple.xianjinxia.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table e_auto_user.
 */
public class AutoUser {

    private Long u_autoid;
    private Long u_cloud_id;
    /** Not-null value. */
    private String u_name;
    private String u_sex;
    private String u_age;
    private String u_img;
    private String u_email;
    private String u_phone;
    private String u_issetpass;
    private String u_password;
    /** Not-null value. */
    private String u_isupdate;

    public AutoUser() {
		this.u_name = "昵称";//defaultValue.modified here by zhangyl at 2015_01_06.
		this.u_isupdate = "0";//defaultValue.modified here by zhangyl at 2015_01_06. 
    }

    public AutoUser(Long u_autoid) {
		this.u_name = "昵称";//defaultValue.modified here by zhangyl at 2015_01_06.
		this.u_isupdate = "0";//defaultValue.modified here by zhangyl at 2015_01_06. 
        this.u_autoid = u_autoid;
    }

    public AutoUser(Long u_autoid, Long u_cloud_id, String u_name, String u_sex, String u_age, String u_img, String u_email, String u_phone, String u_issetpass, String u_password, String u_isupdate) {
        this.u_autoid = u_autoid;
        this.u_cloud_id = u_cloud_id;
        this.u_name = u_name;
        this.u_sex = u_sex;
        this.u_age = u_age;
        this.u_img = u_img;
        this.u_email = u_email;
        this.u_phone = u_phone;
        this.u_issetpass = u_issetpass;
        this.u_password = u_password;
        this.u_isupdate = u_isupdate;
    }

    public Long getU_autoid() {
        return u_autoid;
    }

    public void setU_autoid(Long u_autoid) {
        this.u_autoid = u_autoid;
    }

    public Long getU_cloud_id() {
        return u_cloud_id;
    }

    public void setU_cloud_id(Long u_cloud_id) {
        this.u_cloud_id = u_cloud_id;
    }

    /** Not-null value. */
    public String getU_name() {
        return u_name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public String getU_age() {
        return u_age;
    }

    public void setU_age(String u_age) {
        this.u_age = u_age;
    }

    public String getU_img() {
        return u_img;
    }

    public void setU_img(String u_img) {
        this.u_img = u_img;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_issetpass() {
        return u_issetpass;
    }

    public void setU_issetpass(String u_issetpass) {
        this.u_issetpass = u_issetpass;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    /** Not-null value. */
    public String getU_isupdate() {
        return u_isupdate;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setU_isupdate(String u_isupdate) {
        this.u_isupdate = u_isupdate;
    }

}
