package com.qianmi.small.orderlibrary.interfaces;

import com.qianmi.baselibrary.mvp.BaseView;

/**
 * Created by su on 2016/6/25.
 */
public interface OrderOperationView extends BaseView<OperationPresenter> {

    void showNoData();

    void showCountChanged();

    void hasData();

}
