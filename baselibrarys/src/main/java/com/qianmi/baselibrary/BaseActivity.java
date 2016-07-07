package com.qianmi.baselibrary;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public Resources getBaseResources(){
        return getResources();
    }


    public Intent getStartActivityIntent(String activityName) {
        Intent intent = new Intent();
        intent.setClassName(this, activityName);
        return intent;
    }
}
