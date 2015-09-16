package com.xiepeng.twttest.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiepeng.twttest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dell on 2015/8/21.
 */
public class Topic_Fragment extends Fragment {
    private int index = 1;
    private final String url = "http://120news.wenjin.in/index.php?s=/Article/lists/category/feature.html&p=";
    private ListView listView;
    private School_News_Adapter adapter;
    private RefreshLayout myRefreshListView ;
    private ArrayList<School_News> newsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.news_topic, container, false);

        listView = (ListView) view.findViewById(R.id.news_topic_listview);
        myRefreshListView = (RefreshLayout) view.findViewById(R.id.topic_refreshlayout);
        myRefreshListView.setColorScheme(R.color.green);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                School_News news = newsList.get(position);

                String index = news.getIndex();

                String htmlUrl = "http://120news.wenjin.in/index.php?s=/Article/detail/id/" + index + ".html";
                getHtmlData(htmlUrl);

            }

        });

        getData(index);

        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(), "更新中", Toast.LENGTH_SHORT)
                        .show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        if (adapter != null){
                            adapter.notifyDataSetChanged();
                        }

                        myRefreshListView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        myRefreshListView.setOnLoadListener(new RefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        index+=1;
                        getData(index);

                        myRefreshListView.setLoading(false);

                    }
                }, 0);

            }
        });


        return view;
    }

    private void getData(int index) {
        StringRequest request = new StringRequest(url+String.valueOf(index), new Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {

                try {
                    dealData(arg0);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub

            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
    }

    private void dealData(String arg0) throws JSONException {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<School_News>>() {
        }.getType();

        JSONObject object = new JSONObject(arg0);

        if(index == 1){


            newsList = gson.fromJson(object.getString("rsm"),
                    listType);

            adapter = new School_News_Adapter(getActivity(), newsList);
            listView.setAdapter(adapter);
        }
        else{
//            Type listType1 = new TypeToken<String>() {
//            }.getType();
//            String moreTag = (String)gson.fromJson(object.getString("errno"),listType1);
//            if(moreTag == "0"){
//                Toast.makeText(getActivity(), "没有更多内容", Toast.LENGTH_SHORT)
//                        .show();
//            }
//
            ArrayList<School_News> newsList1 = gson.fromJson(object.getString("rsm"),
                    listType);

            newsList.addAll(newsList1);

            adapter.notifyDataSetChanged();

            myRefreshListView.setRefreshing(false);

        }
    }

    private void getHtmlData(String url) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {

                try {
                    dealHtmlData(arg0);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub

            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
    }

    private void dealHtmlData(String arg0) throws JSONException {

        Gson gson = new Gson();
        Type listType = new TypeToken<News_Html_Data>() {
        }.getType();


        JSONObject object = new JSONObject(arg0);

        News_Html_Data htmlData = gson.fromJson(object.getString("rsm"),
                listType);

        System.out.println(htmlData.getContent());

        Intent intent = new Intent(getActivity(),Detail_News.class);
        intent.putExtra("html", htmlData.getContent());
        startActivity(intent);

    }
}
