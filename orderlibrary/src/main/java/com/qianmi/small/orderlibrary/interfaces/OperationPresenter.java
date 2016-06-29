package com.qianmi.small.orderlibrary.interfaces;

import com.qianmi.baselibrary.mvp.BasePresenter;
import com.qianmi.small.orderlibrary.datas.Order;

import java.util.ArrayList;

/**
 * Created by su on 2016/6/25.
 */
public interface OperationPresenter extends BasePresenter {

    void addOrder();

    void deleteOrder(int index);

    int getOrderCount();

    ArrayList<Order> getOrderList();
}
