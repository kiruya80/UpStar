package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;
import com.ulling.upstar.R;
import com.ulling.upstar.base.BaseViewHolder;

/**
 * Created by KILHO on 2018. 1. 20..
 */

public class Menu extends QcBaseItem {
    private String name;
    private SubMenu subMenu;

    public Menu(int type, String name, SubMenu subMenu) {
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

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
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

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", subMenu=" + subMenu +
                '}';
    }
}
