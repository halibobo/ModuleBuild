package com.qianmi.small.orderlibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qianmi.baselibrary.AppConfig;
import com.qianmi.baselibrary.BaseActivity;
import com.qianmi.baselibrary.utils.ToastUtil;
import com.qianmi.small.orderlibrary.datas.Order;
import com.qianmi.small.orderlibrary.interfaces.OperationPresenter;
import com.qianmi.small.orderlibrary.interfaces.OrderOperationView;


public class OrderActivity extends BaseActivity implements View.OnClickListener ,OrderOperationView{

    private OperationPresenter operationPresenter;
    ArrayAdapter<Order> arrayAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        operationPresenter = new OrderPresenter(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnGotoPoint).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter(OrderActivity.this, android.R.layout.simple_list_item_1, operationPresenter.getOrderList());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                operationPresenter.deleteOrder(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGotoPoint) {
            try {
                Intent intent = getStartActivityIntent(AppConfig.PointManagerModuleActivity);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                ToastUtil.showToast(getApplicationContext(), "网点管理组件尚未开放");
            }
        }else if(v.getId() == R.id.btnAdd){
            operationPresenter.addOrder();
        }
    }

    @Override
    public void showNoData() {
        findViewById(R.id.imageNoData).setVisibility(View.VISIBLE);
    }

    @Override
    public void showCountChanged() {
        arrayAdapter.notifyDataSetChanged();
        getIntent().putExtra(AppConfig.ORDER_INTENT_VALUE, "订单数剩余：" + operationPresenter.getOrderCount());
        setResult(Activity.RESULT_OK,getIntent());
    }

    @Override
    public void hasData() {
        findViewById(R.id.imageNoData).setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(OperationPresenter presenter) {

    }
}
