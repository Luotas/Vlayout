package com.aleaho.vlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class IndexActivity extends AppCompatActivity implements BannerClickListener {


    private RecyclerView indexRecyclerView;
    private VirtualLayoutManager layoutManager;
    private List<UserData> userDatas;


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


        createUserData();

        //设置banner布局，主要用于显示用户信息以及相关状态按钮
        BannerAdapter bannerAdapter = new BannerAdapter(this, new LinearLayoutHelper(), userDatas,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));
        bannerAdapter.setOnBannerViewClick(this);
        adapters.add(bannerAdapter);

        delegateAdapter.setAdapters(adapters);


    }

    private void createUserData() {
        userDatas = new ArrayList<UserData>();


        userDatas.add(new UserData("杨杨",
                "http://up.qqjia.com/z/face01/face06/facejunyong/junyong06.jpg"));

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
}
