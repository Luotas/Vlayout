package com.aleaho.vlayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aleaho.vlayout.Listener.BannerClickListener;
import com.aleaho.vlayout.Listener.ItemOnClickListener;
import com.aleaho.vlayout.R;
import com.aleaho.vlayout.adapter.BannerAdapter;
import com.aleaho.vlayout.adapter.MenuAdapter;
import com.aleaho.vlayout.adapter.TitleAdapter;
import com.aleaho.vlayout.entity.MenuEntity;
import com.aleaho.vlayout.entity.TitleEntity;
import com.aleaho.vlayout.entity.UserEntity;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.beardedhen.androidbootstrap.font.FontAwesome;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class IndexActivity extends AppCompatActivity implements BannerClickListener, ItemOnClickListener {


    private RecyclerView indexRecyclerView;
    private VirtualLayoutManager layoutManager;
    private List<UserEntity> userData;
    private List<TitleEntity> titleData;
    private List<MenuEntity> menuData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {

        indexRecyclerView = (RecyclerView) findViewById(R.id.index_recyclerview);
        layoutManager = new VirtualLayoutManager(this);


        indexRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        indexRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        indexRecyclerView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();


        createIndexData();

        //设置banner布局，主要用于显示用户信息以及相关状态按钮
        BannerAdapter bannerAdapter = new BannerAdapter(this, new LinearLayoutHelper(), userData,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        bannerAdapter.setOnBannerViewClick(this);
        adapters.add(bannerAdapter);


        LinearLayoutHelper titleLayoutHelper = new LinearLayoutHelper();
        titleLayoutHelper.setMargin(0, 5, 0, 5);

        adapters.add(new TitleAdapter(this, titleLayoutHelper, titleData,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80)));


        GridLayoutHelper menuLayoutHelper = new GridLayoutHelper(3);
        menuLayoutHelper.setAutoExpand(false);
        menuLayoutHelper.setWeights(new float[]{33, 33, 33});
        menuLayoutHelper.setAspectRatio(3);
        menuLayoutHelper.setItemCount(menuData.size());
        MenuAdapter menuAdapter = new MenuAdapter(this, menuLayoutHelper, menuData,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));

        menuAdapter.setItemOnClickListener(this);

        adapters.add(menuAdapter);


        delegateAdapter.setAdapters(adapters);


    }

    private void createIndexData() {
        userData = new ArrayList<UserEntity>();


        userData.add(new UserEntity("杨杨",
                "http://uploads.xuexila.com/allimg/1608/704-160Q2092417.png"));

        titleData = new ArrayList<>();
        titleData.add(new TitleEntity("我的功能", R.drawable.title_image1, R.color.title_white));


        menuData = new ArrayList<>();

        menuData.add(new MenuEntity("位置查询", FontAwesome.FA_MAP_MARKER, MainActivity.class));
        menuData.add(new MenuEntity("历史轨迹", FontAwesome.FA_ROAD, MainActivity.class));
        menuData.add(new MenuEntity("签到签退", FontAwesome.FA_SIGN_IN, VLayoutActivity.class));
        menuData.add(new MenuEntity("外出报备", FontAwesome.FA_SIGN_OUT, MainActivity.class));
        menuData.add(new MenuEntity("工作日志", FontAwesome.FA_PENCIL_SQUARE_O, VLayoutActivity.class));
        menuData.add(new MenuEntity("走访商户", FontAwesome.FA_FILE_TEXT_O, MainActivity.class));
        menuData.add(new MenuEntity("商户列别", FontAwesome.FA_LIST, VLayoutActivity.class));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out:
                Log.i("VLayout", "Logout TextView is clicked right now!");

                Toast.makeText(this, "Logout TextView is clicked right now!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.avatar:
                Intent i = new Intent(this, VLayoutActivity.class);
                startActivity(i);
                break;

        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第" + postion + "行");
        Log.i("VLayout", "点击了第" + postion + "行");

        Toast.makeText(this, menuData.get(postion - 2).name, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, menuData.get(postion - 2).toActivity);
        startActivity(i);
    }
}
