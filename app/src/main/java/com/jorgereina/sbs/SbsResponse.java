package com.jorgereina.sbs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SbsResponse {

    @SerializedName("data")
    private List<Data> dataList;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
}
