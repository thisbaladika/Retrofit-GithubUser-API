package com.example.retrofitgithubuser.response.userSearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSearch {
    @SerializedName("items")
    private List<UserSearchData> userDataEntityList;

    public List<UserSearchData> getUserDataEntityList(){
        return userDataEntityList;
    }

    public void setUserDataEntityList(List<UserSearchData> userDataEntityList) {
        this.userDataEntityList = userDataEntityList;
    }
}
