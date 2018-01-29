package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.upstar.R;
import com.ulling.upstar.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 20..
 */
@ToString
@Getter
@Setter
@Data
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
}
