package com.qianmi.baselibrary.adapter;

/**
 * Created by su on 2015/11/12.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * 通用 ViewPagerAdapter
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private static final int TYPE_SECTION_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private int viewTypeCount = 1;
    private SectionCallBack mCallBack;
    private int mSectionLayoutId;

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    /**
     * 初始化通用Adapter
     *
     * @param context      上下文
     * @param mDatas       需要显示的数据集合
     * @param itemLayoutId 子布局文件
     */
    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public void refresh(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    public void setViewTypeCount(int viewTypeCount) {
        this.viewTypeCount = viewTypeCount;
        if (this.viewTypeCount > 1 && mCallBack != null) {
            mSectionLayoutId = mCallBack.addSectionLayout();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (null == mDatas || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }

        if (mCallBack != null && mCallBack.isSection(position)) {
            return TYPE_SECTION_ITEM;
        }
        return TYPE_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case TYPE_ITEM:
                //从ViewHolder中获取控件view，若为空则创建
                final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
                convert(viewHolder, getItem(position), position);

                return viewHolder.getConvertView();

            case TYPE_SECTION_ITEM:
                View view = mInflater.inflate(mSectionLayoutId, null);
                mCallBack.setView(view,position);
                return view;
        }
        return null;
    }

    /**
     * 抽取出getView中间改变的部分
     *
     * @param helper holder缓存对象
     * @param item   Bean对象
     */
    public abstract void convert(ViewHolder helper, T item, int position);

    /**
     * 获得ViewHolder中的view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }

    public void addCallBack(SectionCallBack callBack) {
        this.mCallBack = callBack;
    }

    /**
     *section回调接口
     */
    public interface SectionCallBack {

        boolean isSection(int position);

        int addSectionLayout();

        void setView(View view, int position);
    }
}
