package com.aleaho.vlayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleaho.vlayout.R;
import com.aleaho.vlayout.entity.TitleEntity;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class TitleAdapter extends DelegateAdapter.Adapter<TitleAdapter.TitleViewHolder> {

    private LayoutHelper mLayoutHelper;

    private Context mContext;
    private List<TitleEntity> datas;


    private VirtualLayoutManager.LayoutParams mLayoutParams;

    public TitleAdapter(Context context, LayoutHelper layoutHelper,
                        List<TitleEntity> datas) {

        this(context, layoutHelper, datas,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 15));
    }


    public TitleAdapter(Context context, LayoutHelper layoutHelper,
                        List<TitleEntity> datas,
                        @NonNull VirtualLayoutManager.LayoutParams layoutParams) {

        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.datas = datas;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.title_view, parent, false));
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {


        TitleEntity te = datas.get(0);
        holder.itemView.setLayoutParams(mLayoutParams);
        holder.titleIamgeView.setImageResource(te.titelImage);
        holder.titleTextView.setText(te.titleName);

        if (te.titleColor != 0) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(te.titleColor));
        }
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        /**
         * 标题栏视图，主要用于设置颜色的。
         */
        View colorView;
        /**
         * 标题栏左边图片
         */
        ImageView titleIamgeView;
        /**
         * 标题栏内容
         */
        TextView titleTextView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleIamgeView = (ImageView) itemView.findViewById(R.id.title_image);
            titleTextView = (TextView) itemView.findViewById(R.id.title_name);

        }
    }
}
