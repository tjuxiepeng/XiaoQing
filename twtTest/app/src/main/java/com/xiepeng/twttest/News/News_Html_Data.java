package com.xiepeng.twttest.News;

import java.io.Serializable;

/**
 * Created by dell on 2015/8/22.
 */
public class News_Html_Data implements Serializable {
    private String title;
    private String content;

    public String getContent()
    {
        return content;
    }
    public String getTitle(){
        return title;
    }
}
