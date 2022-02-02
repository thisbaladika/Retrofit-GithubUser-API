package com.example.retrofitgithubuser.response.userDetail;

import com.google.gson.annotations.SerializedName;

public class UserRepo {

    @SerializedName("id")
    private int id;

    @SerializedName("nodeID")
    private String nodeID;

    @SerializedName("name")
    private String name;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("htmlURL")
    private String htmlURL;

    @SerializedName("language")
    private String language;

    public UserRepo(int id, String nodeID, String name, String fullName, String htmlURL, String language) {
        this.id = id;
        this.nodeID = nodeID;
        this.name = name;
        this.fullName = fullName;
        this.htmlURL = htmlURL;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
