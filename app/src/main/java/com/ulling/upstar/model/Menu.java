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
    private String name;
    private int imgUrl;

    public Menu(int type, int fragType, String name, int imgUrl) {
        super();
        this.type = type;
        this.fragType = fragType;
        this.name = name;
        this.imgUrl = imgUrl;
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

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "fragType=" + fragType +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
