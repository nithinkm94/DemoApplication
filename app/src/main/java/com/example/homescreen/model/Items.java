package com.example.homescreen.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.homescreen.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

@Entity
public class Items extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id = 0;
    @SerializedName("title")
    @Expose
    private String title = "test";
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public Items(Integer id, String title, String description, String imageHref) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public Items(Items dataModel) {
        this.id = dataModel.getId();
        this.title = dataModel.getTitle();
        this.description = dataModel.getDescription();
        this.imageHref = dataModel.getImageHref();
    }


    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }



}
