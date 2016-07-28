package com.mal.wweqqful.pokeme;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wweqqful on 7/27/16.
 */
public class UserEntity {

    // Constants
    private final String USR_Name_JSON = "userName";
    private final String USR_pic = "userPic";

    // Attribute
    private String userName;
    private String imgUrl;


    // Constructors
    public UserEntity() {
        userName = "";
        imgUrl ="";
    }

    public UserEntity(String jsonObjStr) throws JSONException {
        JSONObject dataJson = new JSONObject(jsonObjStr);
        userName = dataJson.getString(USR_Name_JSON);
        imgUrl = dataJson.getString(USR_pic);
    }


    // Public Methods

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
