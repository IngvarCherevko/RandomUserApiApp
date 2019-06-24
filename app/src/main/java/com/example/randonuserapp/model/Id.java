
package com.example.randonuserapp.model;

import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("name")
    private String mName;
    @SerializedName("value")
    private String mValue;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
