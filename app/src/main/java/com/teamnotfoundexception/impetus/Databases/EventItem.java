package com.teamnotfoundexception.impetus.Databases;

import java.io.Serializable;

/**
 * Created by sagar on 3/21/18.
 */

public class EventItem implements Serializable {

    public int mId;
    public String mName;
    public String mType;
    public int  mPrice;
    public String mDescription;
    public String mImagePath;
    public String mStartTime;
    public String mEndTime;
    
    


    public EventItem() {

    }

    public EventItem(EventItem eventItem) {

        this.mId = eventItem.getId();
        this.mName = eventItem.getName();
        this.mType = eventItem.getType();
        this.mPrice = eventItem.getPrice();
        this.mDescription = eventItem.getDescription();
        this.mImagePath = eventItem.getImagePath() ;
        this.mStartTime = eventItem.getStartTime();
        this.mEndTime = eventItem.getEndTime();
    }

    public EventItem(int dishId, String dishName, String dishType, int price, String description, String imagePath, String startTime, String endTime) {

        this.mId = dishId;
        this.mName = dishName;
        this.mType = dishType;
        this.mPrice = price;
        this.mDescription = description;
        this.mImagePath = imagePath ;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String StartTime) {
        this.mStartTime = mStartTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }


    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public void setPrice(int price) {
        this.mPrice = price;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
