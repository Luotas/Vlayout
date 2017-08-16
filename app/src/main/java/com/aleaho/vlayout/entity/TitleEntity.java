package com.aleaho.vlayout.entity;

/**
 * Created by Administrator on 2017/8/16.
 */

public class TitleEntity {

    public String titleName;
    public int titelImage;
    public int titleColor;

    public TitleEntity(String titleName, int titelImage) {
        this(titleName, titelImage, 0);
    }

    public TitleEntity(String titleName, int titelImage, int titleColor) {
        this.titleName = titleName;
        this.titelImage = titelImage;
        this.titleColor = titleColor;
    }
}
