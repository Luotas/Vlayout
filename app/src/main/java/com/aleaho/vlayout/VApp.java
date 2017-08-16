package com.aleaho.vlayout;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.beardedhen.androidbootstrap.font.FontAwesome;

/**
 * Created by Administrator on 2017/8/16.
 */

public class VApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
//        FontAwesome.FA_BOLT;
    }
}
