package com.example.apple.xianjinxia.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity mapped to table e_allinfo.
 */
public class AllInfo implements Parcelable {

    private Long a_pid;
    private Long a_id;
    private String a_add_date;
    private Integer a_notes;
    private Integer a_cards;
    private Integer a_bills;
    private Integer a_diarys;
    private Integer a_feeling;

    public AllInfo() {
    }

    public AllInfo(Long a_id) {
        this.a_id = a_id;
    }

    public AllInfo(Long a_pid, Long a_id, String a_add_date, Integer a_notes, Integer a_cards, Integer a_bills, Integer a_diarys, Integer a_feeling) {
        this.a_pid = a_pid;
        this.a_id = a_id;
        this.a_add_date = a_add_date;
        this.a_notes = a_notes;
        this.a_cards = a_cards;
        this.a_bills = a_bills;
        this.a_diarys = a_diarys;
        this.a_feeling = a_feeling;
    }

    public Long getA_pid() {
        return a_pid;
    }

    public void setA_pid(Long a_pid) {
        this.a_pid = a_pid;
    }

    public Long getA_id() {
        return a_id;
    }

    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public String getA_add_date() {
        return a_add_date;
    }

    public void setA_add_date(String a_add_date) {
        this.a_add_date = a_add_date;
    }

    public Integer getA_notes() {
        return a_notes;
    }

    public void setA_notes(Integer a_notes) {
        this.a_notes = a_notes;
    }

    public Integer getA_cards() {
        return a_cards;
    }

    public void setA_cards(Integer a_cards) {
        this.a_cards = a_cards;
    }

    public Integer getA_bills() {
        return a_bills;
    }

    public void setA_bills(Integer a_bills) {
        this.a_bills = a_bills;
    }

    public Integer getA_diarys() {
        return a_diarys;
    }

    public void setA_diarys(Integer a_diarys) {
        this.a_diarys = a_diarys;
    }

    public Integer getA_feeling() {
        return a_feeling;
    }

    public void setA_feeling(Integer a_feeling) {
        this.a_feeling = a_feeling;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.a_pid);
        dest.writeValue(this.a_id);
        dest.writeString(this.a_add_date);
        dest.writeValue(this.a_notes);
        dest.writeValue(this.a_cards);
        dest.writeValue(this.a_bills);
        dest.writeValue(this.a_diarys);
        dest.writeValue(this.a_feeling);
    }

    protected AllInfo(Parcel in) {
        this.a_pid = (Long) in.readValue(Long.class.getClassLoader());
        this.a_id = (Long) in.readValue(Long.class.getClassLoader());
        this.a_add_date = in.readString();
        this.a_notes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.a_cards = (Integer) in.readValue(Integer.class.getClassLoader());
        this.a_bills = (Integer) in.readValue(Integer.class.getClassLoader());
        this.a_diarys = (Integer) in.readValue(Integer.class.getClassLoader());
        this.a_feeling = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<AllInfo> CREATOR = new Parcelable.Creator<AllInfo>() {
        @Override
        public AllInfo createFromParcel(Parcel source) {
            return new AllInfo(source);
        }

        @Override
        public AllInfo[] newArray(int size) {
            return new AllInfo[size];
        }
    };
}
