package com.aleaho.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * Created by Administrator on 2017/8/12.
 */

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {


    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;

    private LogoutClickListener logoutClickListener;


    /**
     * 初始化
     *
     * @param context
     * @param layoutHelper
     * @param count
     */
    public BannerAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    /**
     * @param context
     * @param layoutHelper
     * @param count
     * @param layoutParams
     */
    public BannerAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
    }


    public void setOnLogoutClick(LogoutClickListener l) {
        this.logoutClickListener = l;
    }


    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_work_banner, parent, false));

    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public BannerViewHolder(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.banner_image);
            tv = (TextView) itemView.findViewById(R.id.login_out);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (logoutClickListener != null) {
                        logoutClickListener.onClick(v);
                    }
                }
            });


        }
    }
}
