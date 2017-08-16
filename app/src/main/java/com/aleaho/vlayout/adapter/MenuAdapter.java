package com.aleaho.vlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleaho.vlayout.ItemOnClickListener;
import com.aleaho.vlayout.R;
import com.aleaho.vlayout.entity.MenuEntity;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class MenuAdapter extends DelegateAdapter.Adapter<MenuAdapter.MenuViewHolder> {


    private LayoutHelper mLayoutHelper;
    private Context mContext;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private List<MenuEntity> data;

    private ItemOnClickListener itemOnClickListener;


    /**
     * 初始化显示菜单的九宫格界面，界面默认尺寸是width：MATCH_PARENT;width:300。
     *
     * @param context      上下文
     * @param layoutHelper 界面的布局格式
     * @param data         界面显示的数据集合
     */
    public MenuAdapter(Context context, LayoutHelper layoutHelper, List<MenuEntity> data) {

        this(context, layoutHelper, data,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }


    /**
     * 初始化显示菜单的九宫格界面
     *
     * @param context      上下文
     * @param layoutHelper 界面的布局格式
     * @param data         界面显示的数据集合
     * @param layoutParams 界面的布局大小
     */
    public MenuAdapter(Context context, LayoutHelper layoutHelper,
                       List<MenuEntity> data,
                       VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.data = data;
        this.mLayoutParams = layoutParams;

    }


    public void setItemOnClickListener(ItemOnClickListener l) {
        this.itemOnClickListener = l;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_work_func, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {

        MenuEntity me = data.get(position);
        holder.menuName.setText(me.name);
        holder.menuImage.setImageResource(me.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {


        ImageView menuImage;
        TextView menuName;


        public MenuViewHolder(View itemView) {
            super(itemView);

            menuImage = (ImageView) itemView.findViewById(R.id.function_image);
            menuName = (TextView) itemView.findViewById(R.id.function_name);


            if (null != itemOnClickListener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemOnClickListener.onItemClick(v, getAdapterPosition());
                    }
                });
            }

        }

        public TextView getMenuName() {
            return menuName;
        }
    }
}
