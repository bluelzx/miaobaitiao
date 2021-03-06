package com.example.apple.xianjinxia.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table e_allcardinfo.
 */
public class AllCardInfo {

    private Long ac_pid;
    private Long ac_id;
    private String ac_add_date;
    private Integer ac_type;
    private String ac_card_name;

    public AllCardInfo() {
    }

    public AllCardInfo(Long ac_id) {
        this.ac_id = ac_id;
    }

    public AllCardInfo(Long ac_pid, Long ac_id, String ac_add_date, Integer ac_type, String ac_card_name) {
        this.ac_pid = ac_pid;
        this.ac_id = ac_id;
        this.ac_add_date = ac_add_date;
        this.ac_type = ac_type;
        this.ac_card_name = ac_card_name;
    }

    public Long getAc_pid() {
        return ac_pid;
    }

    public void setAc_pid(Long ac_pid) {
        this.ac_pid = ac_pid;
    }

    public Long getAc_id() {
        return ac_id;
    }

    public void setAc_id(Long ac_id) {
        this.ac_id = ac_id;
    }

    public String getAc_add_date() {
        return ac_add_date;
    }

    public void setAc_add_date(String ac_add_date) {
        this.ac_add_date = ac_add_date;
    }

    public Integer getAc_type() {
        return ac_type;
    }

    public void setAc_type(Integer ac_type) {
        this.ac_type = ac_type;
    }

    public String getAc_card_name() {
        return ac_card_name;
    }

    public void setAc_card_name(String ac_card_name) {
        this.ac_card_name = ac_card_name;
    }

}
