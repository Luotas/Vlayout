package com.aleaho.vlayout;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.aleaho.vlayout.entity.FunctionEntity;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainVLayoutActivity extends AppCompatActivity implements ItemOnClickListener, BannerClickListener {

    private RecyclerView workRecyclerView = null;

    private VirtualLayoutManager layoutManager = null;
    private List<LayoutHelper> helperList = null;

    List<FunctionEntity> oneFuncs;
    List<FunctionEntity> twoFuncs;
    WorkAdapter workAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vlayout);
        workRecyclerView = (RecyclerView) findViewById(R.id.work_recyclerview);

        initData();

        initView();

        initRvContent();
    }

    private void initRvContent() {

        //        SeparatorDecoration itemDivider = new SeparatorDecoration.Builder(this)
        //                .width(1)
        //                .colorFromResources(R.color.back_color)
        //                .build();
        //        //添加ItemDecoration，item之间的间隔
        ////        int leftRight = dip2px(2);
        ////        int topBottom = dip2px(2);
        //
        //        workRecyclerView.addItemDecoration(itemDivider);


        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };


    }


    public int dip2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    private void initData() {
        oneFuncs = new ArrayList<FunctionEntity>();
        twoFuncs = new ArrayList<FunctionEntity>();


        for (int i = 0; i < 8; i++) {
            oneFuncs.add(new FunctionEntity("私人服务", R.drawable.home));
            twoFuncs.add(new FunctionEntity("公司服务", R.drawable.person));
        }
    }

    private void initView() {

        layoutManager = new VirtualLayoutManager(this);
        workRecyclerView.setLayoutManager(layoutManager);

        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        workRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);


        helperList = new LinkedList<>();

        //1.
        //头部图片一项，使用SingleLayoutHelper
        SingleLayoutHelper bannerLayoutHelper = new SingleLayoutHelper();
        bannerLayoutHelper.setItemCount(1);
        helperList.add(bannerLayoutHelper);

        //2
        //个人功能大标题
        LinearLayoutHelper personTitleHelper = new LinearLayoutHelper();
        personTitleHelper.setItemCount(1);
        helperList.add(personTitleHelper);

        //功能模块，主要为九宫格
        GridLayoutHelper personGridHelper = new GridLayoutHelper(3);
        personGridHelper.setAutoExpand(false);
        personGridHelper.setWeights(new float[]{33, 33, 33});
        personGridHelper.setAspectRatio(3);
        personGridHelper.setItemCount(oneFuncs.size());
        helperList.add(personGridHelper);

        //公司功能大标题
        LinearLayoutHelper companyTitleHelper = new LinearLayoutHelper();
        companyTitleHelper.setItemCount(1);
        helperList.add(companyTitleHelper);


        //公司功能模块
        GridLayoutHelper companyGridHelper = new GridLayoutHelper(3);
        companyGridHelper.setWeights(new float[]{33, 33, 33});
        companyGridHelper.setAutoExpand(false);
        companyGridHelper.setItemCount(twoFuncs.size());
        companyGridHelper.setAspectRatio(3);
        helperList.add(companyGridHelper);

        layoutManager.setLayoutHelpers(helperList);
        workAdapter = new WorkAdapter(layoutManager, oneFuncs, twoFuncs, this, this);

        workRecyclerView.setAdapter(workAdapter);


    }


    // 点击事件的回调函数
    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第" + postion + "行");
        Log.i("VLayout", "点击了第" + postion + "行");

        //Toast.makeText(this, (String) listItem.get(postion).get("ItemTitle"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out:
                Log.i("VLayout", "Logout TextView is clicked right now!");

                Toast.makeText(this, "Logout TextView is clicked right now!", Toast.LENGTH_SHORT).show();
        }
    }
}
