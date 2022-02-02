package com.example.retrofitgithubuser.response.follow;

import com.google.gson.annotations.SerializedName;

public class Follower {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("nodeID")
    private String nodeID;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("html_url")
    private String html_url;

    public Follower(String login, int id, String nodeID, String avatar_url, String html_url) {
        this.login = login;
        this.id = id;
        this.nodeID = nodeID;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
