package com.blue.nicevideo.module.recommand;


import com.blue.nicevideo.module.BaseModel;
import com.blue.videosdk.module.Monitor;
import com.blue.videosdk.module.emevent.EMEvent;

import java.util.ArrayList;

/**
 * @author: Blue
 * @date: 2017/10/12.
 * @function: 搜索实体
 */
public class RecommandBodyValue extends BaseModel {

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    //视频专用
    public String thumb;
    public String resource;
    public String resourceID;
    public String adid;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;

}
