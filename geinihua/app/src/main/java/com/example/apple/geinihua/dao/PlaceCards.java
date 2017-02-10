package com.example.apple.geinihua.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity mapped to table e_placecards.
 */
public class PlaceCards implements Parcelable {

    private Long s_id;
    private Long s_pid;
    private String s_add_date;
    private String s_name;
    private String s_address;
    private String s_describ;

    public PlaceCards() {
    }

    public PlaceCards(Long s_id) {
        this.s_id = s_id;
    }

    public PlaceCards(Long s_id, Long s_pid, String s_add_date, String s_name, String s_address, String s_describ) {
        this.s_id = s_id;
        this.s_pid = s_pid;
        this.s_add_date = s_add_date;
        this.s_name = s_name;
        this.s_address = s_address;
        this.s_describ = s_describ;
    }

    public Long getS_id() {
        return s_id;
    }

    public void setS_id(Long s_id) {
        this.s_id = s_id;
    }

    public Long getS_pid() {
        return s_pid;
    }

    public void setS_pid(Long s_pid) {
        this.s_pid = s_pid;
    }

    public String getS_add_date() {
        return s_add_date;
    }

    public void setS_add_date(String s_add_date) {
        this.s_add_date = s_add_date;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_describ() {
        return s_describ;
    }

    public void setS_describ(String s_describ) {
        this.s_describ = s_describ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.s_id);
        dest.writeValue(this.s_pid);
        dest.writeString(this.s_add_date);
        dest.writeString(this.s_name);
        dest.writeString(this.s_address);
        dest.writeString(this.s_describ);
    }

    protected PlaceCards(Parcel in) {
        this.s_id = (Long) in.readValue(Long.class.getClassLoader());
        this.s_pid = (Long) in.readValue(Long.class.getClassLoader());
        this.s_add_date = in.readString();
        this.s_name = in.readString();
        this.s_address = in.readString();
        this.s_describ = in.readString();
    }

    public static final Parcelable.Creator<PlaceCards> CREATOR = new Parcelable.Creator<PlaceCards>() {
        @Override
        public PlaceCards createFromParcel(Parcel source) {
            return new PlaceCards(source);
        }

        @Override
        public PlaceCards[] newArray(int size) {
            return new PlaceCards[size];
        }
    };
}
