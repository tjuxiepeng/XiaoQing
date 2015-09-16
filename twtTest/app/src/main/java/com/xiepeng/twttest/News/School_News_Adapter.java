package com.xiepeng.twttest.News;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiepeng.twttest.R;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/21.
 */
public class School_News_Adapter extends BaseAdapter {
    private Context c;
    private ArrayList<School_News> list;

    public School_News_Adapter(Context context, ArrayList<School_News> books) {
        this.c = context;
        this.list = books;
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
            convertView = View.inflate(c, R.layout.school_news_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.school_news_title);
            holder.amount = (TextView) convertView.findViewById(R.id.people_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        School_News news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.amount.setText(news.getView());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public TextView amount;
    }

}
