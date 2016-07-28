package com.mal.wweqqful.pokeme;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wweqqful on 7/27/16.
 */
public class Post {

    // Constants
    private final String POST_TXT_JSON = "postText";
    private final String POST_Time_JSON = "postTime";
    private final String POST_PIC_URL_JSON = "postImage";
    private final String POST_Likes_JSON = "likes";
    private final String POST_COMMENTS_JSON = "comments";
    private final String POST_SHARE_JSON = "shares";

    // Attributes
    private UserEntity userPost;
    private String postTxt;
    private String postImgUrl;
    private String postTimeTxt;
    private String postLikesTxt;
    private String postCommentsTxt;
    private String postShareTxt;

    // Constructors

    public Post() {
        userPost = new UserEntity();
        postTxt = "";
        postImgUrl = "";
        postTimeTxt = "";
        postLikesTxt = "";
        postCommentsTxt = "";
        postShareTxt = "";
    }

    public Post(String jsonObjStr) throws JSONException {
        JSONObject dataJson = new JSONObject(jsonObjStr);
        userPost = new UserEntity(jsonObjStr);
        postTxt = dataJson.getString(POST_TXT_JSON);
        postImgUrl = dataJson.getString(POST_PIC_URL_JSON);
        postTimeTxt = dataJson.getString(POST_Time_JSON);
        postLikesTxt = dataJson.getString(POST_Likes_JSON);
        postCommentsTxt = dataJson.getString(POST_COMMENTS_JSON);
        postShareTxt = dataJson.getString(POST_SHARE_JSON);
    }

    // Public Methods

    public UserEntity getUserPost() {
        return userPost;
    }

    public void setUserPost(UserEntity userPost) {
        this.userPost = userPost;
    }

    public String getPostTxt() {
        return postTxt;
    }

    public void setPostTxt(String postTxt) {
        this.postTxt = postTxt;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getPostTimeTxt() {
        return postTimeTxt;
    }

    public void setPostTimeTxt(String postTimeTxt) {
        this.postTimeTxt = postTimeTxt;
    }

    public String getPostLikesTxt() {
        return postLikesTxt;
    }

    public void setPostLikesTxt(String postLikesTxt) {
        this.postLikesTxt = postLikesTxt;
    }

    public String getPostCommentsTxt() {
        return postCommentsTxt;
    }

    public void setPostCommentsTxt(String postCommentsTxt) {
        this.postCommentsTxt = postCommentsTxt;
    }

    public String getPostShareTxt() {
        return postShareTxt;
    }

    public void setPostShareTxt(String postShareTxt) {
        this.postShareTxt = postShareTxt;
    }

    @Override
    public String toString() {
        return "Post{" +
                ", userPost=" + userPost +
                ", postTxt='" + postTxt + '\'' +
                ", postImgUrl='" + postImgUrl + '\'' +
                ", postTimeTxt='" + postTimeTxt + '\'' +
                ", postLikesTxt='" + postLikesTxt + '\'' +
                ", postCommentsTxt='" + postCommentsTxt + '\'' +
                ", postShareTxt='" + postShareTxt + '\'' +
                '}';
    }
}



