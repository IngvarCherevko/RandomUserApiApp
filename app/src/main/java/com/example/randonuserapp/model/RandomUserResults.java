
package com.example.randonuserapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomUserResults {

    @SerializedName("info")
    private Info mInfo;
    @SerializedName("results")
    private List<Result> mResults;

    public Info getInfo() {
        return mInfo;
    }

    public void setInfo(Info info) {
        mInfo = info;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

}
