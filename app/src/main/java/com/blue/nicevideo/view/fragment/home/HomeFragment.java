package com.blue.nicevideo.view.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blue.nicevideo.R;
import com.blue.nicevideo.adapter.CourseAdapter;
import com.blue.nicevideo.constant.Constant;
import com.blue.nicevideo.home.HomeHeaderLayout;
import com.blue.nicevideo.module.recommand.BaseRecommandModel;
import com.blue.nicevideo.network.http.RequestCenter;
import com.blue.nicevideo.view.fragment.BaseFragment;
import com.blue.videosdk.zxing.app.CaptureActivity;
import com.blue.videosdk.okhttp.listener.DisposeDataListener;

/**
 * @author: Blue
 * @date: 2017/10/9.
 * @function:
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View mContentView;
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    private BaseRecommandModel mRecommandData;
    private CourseAdapter mAdapter;

    private static final int REQUEST_QRCODE = 0x01;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    /**
     * 发送首页列表数据请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e("bluedai", "onSuccess " + responseObj.toString());
                /**
                 * 获取数据更新UI
                 */
                mRecommandData = (BaseRecommandModel) responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e("bluedai", "onFailure " + reasonObj.toString());
            }
        });
    }

    private void showSuccessView() {
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {

            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            // 为列表添加表头
            mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            Log.d("bluedai", "showSuccessView: error");
            showErrorView();
        }
    }

    private void showErrorView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_view:
                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                    doOpenCamera();
                } else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
                break;
        }
    }

    @Override
    public void doOpenCamera() {
        Intent intent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");
                    Log.d("bluedai", "onActivityResult: " + code);
                }
                break;
        }
    }
}
