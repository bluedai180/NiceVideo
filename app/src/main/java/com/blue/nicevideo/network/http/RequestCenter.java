package com.blue.nicevideo.network.http;

import com.blue.nicevideo.module.course.BaseCourseModel;
import com.blue.nicevideo.module.recommand.BaseRecommandModel;
import com.blue.videosdk.okhttp.CommonOkHttpClient;
import com.blue.videosdk.okhttp.listener.DisposeDataHandle;
import com.blue.videosdk.okhttp.listener.DisposeDataListener;
import com.blue.videosdk.okhttp.request.CommonRequest;
import com.blue.videosdk.okhttp.request.RequestParams;

/**
 * @author: Blue
 * @date: 2017/10/11.
 * @function: 存放应用层所有的请求接口
 */

public class RequestCenter {
    private static void postRequest(String url, RequestParams params,
                                    DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params),
                new DisposeDataHandle(listener, clazz));
    }

    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }
}
