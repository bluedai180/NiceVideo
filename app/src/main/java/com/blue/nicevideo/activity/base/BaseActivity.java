package com.blue.nicevideo.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author: Blue
 * @date: 2017/10/9.
 * @function: 提供所有activity公共行为
 */
public class BaseActivity extends AppCompatActivity {
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }
}
