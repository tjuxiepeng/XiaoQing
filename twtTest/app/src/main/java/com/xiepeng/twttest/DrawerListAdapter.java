package com.xiepeng.twttest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiepeng.twttest.Activity.ActivityCollege_News;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/31.
 */
public class DrawerListAdapter extends BaseAdapter {
    private Context c;
    private ArrayList<DrawerItem> list;

    public DrawerListAdapter(Context context, ArrayList<DrawerItem> newsList) {
        this.c = context;
        this.list = newsList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(c, R.layout.main_drawer_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.main_list_item_image);
            holder.imageView = (ImageView) convertView.findViewById(R.id.main_list_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DrawerItem news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.imageView.setImageResource(news.getImageId());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public ImageView imageView;
    }
}
