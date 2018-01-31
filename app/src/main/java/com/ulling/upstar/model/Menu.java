package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 20..
 */
@ToString
@Getter
@Setter
@Data /* 아래 코드 추가 */
@EqualsAndHashCode(callSuper=false)
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
