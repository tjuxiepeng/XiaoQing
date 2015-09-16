package com.xiepeng.twttest.Activity;

/**
 * Created by dell on 2015/8/30.
 */
public class ActivityCollege_News {
    private String title;
    private String index;
    private String start_time;
    private String at_place;


    public ActivityCollege_News(String index, String title, String start_time, String at_place) {
        this.index = index;
        this.title = title;
        this.start_time = start_time;
        this.at_place = at_place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getAt_place() {
        return at_place;
    }

    public void setAt_place(String at_place) {
        this.at_place = at_place;
    }
}
