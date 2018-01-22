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
    private String name;
    private List<SubMenu> subMenu;

    public Menu(int type, String name, List<SubMenu>  subMenu) {
        super();
        this.type = type;
        this.name = name;
        this.subMenu = subMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<SubMenu> subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", subMenu=" + subMenu +
                '}';
    }

    public static class SubMenu extends QcBaseItem {
        private String name;

        public SubMenu(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "SubMenu{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
