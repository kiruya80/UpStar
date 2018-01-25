package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.upstar.R;
import com.ulling.upstar.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KILHO on 2018. 1. 20..
 */
public class Menu extends QcBaseItem {
    private int fragType;
    private int subIndex;

    private String name;
    private String imgUrl;
    private int imgResourceId;

    public Menu(int type, int fragType, String name, String imgUrl) {
        super();
        this.type = type;
        this.fragType = fragType;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Menu(int type, int fragType, String name, int imgResourceId) {
        super();
        this.type = type;
        this.fragType = fragType;
        this.name = name;
        this.imgResourceId = imgResourceId;
    }

    public Menu(int type, int fragType, int subIndex, String name, int imgResourceId) {
        super();
        this.type = type;
        this.fragType = fragType;
        this.subIndex = subIndex;
        this.name = name;
        this.imgResourceId = imgResourceId;
    }

    public int getFragType() {
        return fragType;
    }

    public void setFragType(int fragType) {
        this.fragType = fragType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public int getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(int subIndex) {
        this.subIndex = subIndex;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "fragType=" + fragType +
                ", subIndex=" + subIndex +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgResourceId=" + imgResourceId +
                '}';
    }
}
