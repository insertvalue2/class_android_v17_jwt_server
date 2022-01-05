package com.nomadlab.myjwt_server.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResLogin {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ResData resData;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResData getData() {
        return resData;
    }

    public void setData(ResData resData) {
        this.resData = resData;
    }

}
