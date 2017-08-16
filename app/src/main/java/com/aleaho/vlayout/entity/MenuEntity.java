package com.aleaho.vlayout.entity;

/**
 * Created by Administrator on 2017/8/16.
 */

public class MenuEntity {

    public String name;
    public int image;

    public Class<?> toActivity;

    public MenuEntity(String name, int image, Class<?> clazz) {
        this.name = name;
        this.image = image;
        this.toActivity = clazz;
    }

    public MenuEntity(String name, int image) {
        this(name, image, null);
    }


}
