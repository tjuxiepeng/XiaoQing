package com.xiepeng.twttest.Activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiepeng.twttest.R;
import java.util.ArrayList;

/**
 * Created by dell on 2015/8/30.
 */
public class Activity_School_News_Adapter extends BaseAdapter{
    private Context c;
    private ArrayList<ActivityCollege_News> list;

    public Activity_School_News_Adapter(Context context, ArrayList<ActivityCollege_News> lists) {
        this.c = context;
        this.list = lists;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(c, R.layout.activity_school_news_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.activity_school_news_title);
            holder.time = (TextView) convertView.findViewById(R.id.activity_school_news_time);
            holder.place = (TextView) convertView.findViewById(R.id.activity_school_news_place);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ActivityCollege_News news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.time.setText(news.getStart_time());
        holder.place.setText(news.getAt_place());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public TextView time;
        public TextView place;
    }
}
