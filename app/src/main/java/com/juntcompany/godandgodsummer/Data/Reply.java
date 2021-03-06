package com.juntcompany.godandgodsummer.Data;

import com.google.gson.annotations.SerializedName;
import com.juntcompany.godandgodsummer.DataStructure.TimeLine.WriteResult;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;

import retrofit2.Call;

/**
 * Created by EOM on 2016-07-11.
 */
public class Reply {

    @SerializedName("content")
    public String replyContent;
    @SerializedName("username")
    public String replyUsername;
    @SerializedName("email")
    public String replyUserEmail;
    @SerializedName("userPhoto")
    public String replyUserImage;
    @SerializedName("updatedAt")
    public String replyUpdateTime;
    @SerializedName("createdAt")
    public String replyCreatedtime;


    @Override
    public String toString() {
        return replyContent + replyUsername + replyUserImage + replyUserEmail + replyUpdateTime;
    }
}
