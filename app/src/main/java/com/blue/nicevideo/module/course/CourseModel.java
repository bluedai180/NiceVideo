package com.blue.nicevideo.module.course;

import com.blue.nicevideo.module.BaseModel;

import java.util.ArrayList;

/**
 * @author: Blue
 * @date: 2017/10/11.
 * @function:
 */

public class CourseModel extends BaseModel {
    public CourseHeaderValue head;
    public CourseFooterValue footer;
    public ArrayList<CourseCommentValue> body;
}
