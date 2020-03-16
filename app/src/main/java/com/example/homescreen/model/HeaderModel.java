package com.example.homescreen.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeaderModel extends BaseObservable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Items> rows = null;

    private MutableLiveData<List<Items>> itemList = new MutableLiveData<>();

    public MutableLiveData<List<Items>> getItemList() {
        return itemList;
    }

    public void setItemList(MutableLiveData<List<Items>> itemList) {
        this.itemList.setValue(rows);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Items> getRows() {
        return rows;
    }

    public void setRows(List<Items> rows) {
        this.rows = rows;
    }
}
