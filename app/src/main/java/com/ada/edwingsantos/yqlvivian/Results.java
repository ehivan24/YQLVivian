package com.ada.edwingsantos.yqlvivian;

import android.os.Parcel;
import android.os.Parcelable;

public class Results implements Parcelable {
    String title;
    String address;
    String city;
    String state;
    String phone;
    double latitude;
    double longitude;
    String distance;
    String businessUrl;


    public Results(String title, String address, String businessUrl, String city, String distance, double latitude, double longitude, String phone, String state) {
        this.title = title;
        this.address = address;
        this.businessUrl = businessUrl;
        this.city = city;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.state = state;
    }

    protected Results(Parcel in) {
        title = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        phone = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        distance = in.readString();
        businessUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(phone);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(distance);
        dest.writeString(businessUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}