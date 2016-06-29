package com.qianmi.small.orderlibrary.datas;

import java.util.ArrayList;

/**
 * Created by su on 2016/6/27.
 */
public class OrderDatas {
    private ArrayList<Order> list = new ArrayList<>();

    public void addOrder(Order order) {
        if (!list.contains(order)) {
            list.add(order);
        }
    }

    public void deleteOrder(Order order) {
        if (list.contains(order)) {
            list.remove(order);
        }
    }

    public void addOrder(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    public void deleteOrder(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    public ArrayList<Order> getOrderList() {
        return list;
    }

    public int getOrderCount() {
        return list.size();
    }
}
