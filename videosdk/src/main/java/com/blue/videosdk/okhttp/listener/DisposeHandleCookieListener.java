package com.blue.videosdk.okhttp.listener;

import java.util.ArrayList;


/**
 * @author: Blue
 * @date: 2017/10/9.
 * @function: 当需要专门处理Cookie时创建此回调接口
 */
public interface DisposeHandleCookieListener extends DisposeDataListener
{
	public void onCookie(ArrayList<String> cookieStrLists);
}
