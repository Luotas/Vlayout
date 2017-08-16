package com.aleaho.vlayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleaho.vlayout.Listener.BannerClickListener;
import com.aleaho.vlayout.R;
import com.aleaho.vlayout.entity.UserEntity;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/12.
 */

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {


    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private List<UserEntity> mData;

    private BannerClickListener bannerClickListener;


    /**
     * 初始化广告条布局
     *
     * @param context      上下文
     * @param layoutHelper 显示项目的布局类型
     * @param data         显示项目的数据
     */
    public BannerAdapter(Context context, LayoutHelper layoutHelper,
                         List<UserEntity> data) {
        this(context, layoutHelper, data,
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    /**
     * 初始化广告条布局
     *
     * @param context      上线文
     * @param layoutHelper 显示项目的布局类型
     * @param data         显示项目的数据
     * @param layoutParams 显示项目的布局信息
     */
    public BannerAdapter(Context context, LayoutHelper layoutHelper,
                         List<UserEntity> data,
                         @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mData = data;
        this.mLayoutParams = layoutParams;
    }


    /**
     * 注册一个回调函数，在单击视图时调用它。
     * 主要为Banner界面上的头像按钮和注销按钮注册单击事件。
     *
     * @param l 回调时被执行的方法
     */
    public void setOnBannerViewClick(BannerClickListener l) {
        this.bannerClickListener = l;
    }


    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_work_banner, parent, false));

    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {


        UserEntity ud = mData.get(position);
        holder.tvName.setText(ud.name);

        Log.i("BannerAdapter", ud.avatarUrl);

        Picasso.with(mContext).load(ud.avatarUrl).resize(80, 80).centerCrop().into(holder.civAvatar);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivBanner;
        private TextView tvLogout;
        private CircleImageView civAvatar;
        //        private ImageView civAvatar;
        private TextView tvName;

        public BannerViewHolder(View itemView) {
            super(itemView);

            ivBanner = (ImageView) itemView.findViewById(R.id.banner_image);
            tvLogout = (TextView) itemView.findViewById(R.id.login_out);
            civAvatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            tvName = (TextView) itemView.findViewById(R.id.nameView);


            if (bannerClickListener != null) {
                civAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bannerClickListener.onClick(v);

                    }
                });

                tvLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bannerClickListener.onClick(v);
                    }

                });

            }
        }
    }
}
