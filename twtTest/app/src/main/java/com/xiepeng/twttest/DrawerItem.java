package com.xiepeng.twttest;

/**
 * Created by dell on 2015/8/31.
 */
public class DrawerItem {
    private String title;
    private int imageId;

    public DrawerItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
