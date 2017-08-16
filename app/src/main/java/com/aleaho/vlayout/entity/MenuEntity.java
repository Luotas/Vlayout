package com.aleaho.vlayout.entity;

/**
 * Created by Administrator on 2017/8/16.
 */

/**
 * 菜单数据结构
 */
public class MenuEntity {

    public String name;
    public String icon;

    public Class<?> toActivity;

    /**
     * 初始化菜单选项
     * @param name  菜单名称
     * @param icon  菜单图标
     * @param clazz 目标Activity
     */
    public MenuEntity(String name, String icon, Class<?> clazz) {
        this.name = name;
        this.icon = icon;
        this.toActivity = clazz;
    }

    /**
     * 初始化菜单选项
     * @param name  菜单名称
     * @param icon  菜单图标
     */
    public MenuEntity(String name, String icon) {
        this(name, icon, null);
    }


}
