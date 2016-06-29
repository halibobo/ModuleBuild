package com.qianmi.small.orderlibrary;


import com.qianmi.small.orderlibrary.datas.Order;
import com.qianmi.small.orderlibrary.datas.OrderDatas;
import com.qianmi.small.orderlibrary.interfaces.OperationPresenter;
import com.qianmi.small.orderlibrary.interfaces.OrderOperationView;

import java.util.ArrayList;

/**
 * Created by su on 2016/6/27.
 */
public class OrderPresenter implements OperationPresenter {

    private OrderDatas orderDatas = new OrderDatas();
    private OrderOperationView orderOperationView;

    public OrderPresenter(OrderOperationView orderOperationView) {
        this.orderOperationView = orderOperationView;
    }

    @Override
    public void addOrder() {
        Order order = new Order();
        order.setName("order");
        order.setPrice(1515);
        orderDatas.addOrder(order);
        orderOperationView.showCountChanged();
        if (orderDatas.getOrderCount() > 0) {
            orderOperationView.hasData();
        }
    }

    @Override
    public void deleteOrder(int index) {
        orderDatas.deleteOrder(index);
        orderOperationView.showCountChanged();
        if (orderDatas.getOrderCount() <= 0) {
            orderOperationView.showNoData();
        }
    }

    @Override
    public int getOrderCount() {
        return orderDatas.getOrderCount();
    }

    @Override
    public ArrayList<Order> getOrderList() {
        return orderDatas.getOrderList();
    }

    @Override
    public void start() {

    }
}
