package com.xiepeng.twttest.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiepeng.twttest.News.News_Html_Data;
import com.xiepeng.twttest.News.RefreshLayout;
import com.xiepeng.twttest.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2015/8/26.
 */
public class ActivitySchoolFragment extends Fragment {
//popwindow所需
    private ImageView arrow;
    private boolean isOpenPop = false;
    private PopupWindow window;
    private ListView popListView;
    private RelativeLayout title_layout;
    public static final String KEY = "key";
    ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
    Context mContext;


//解析数据所需
    private int index = 1;


    private final String url1 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/jxgcxy.html&p=";
    private final String url2 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/glyjjxb.html&p=";
    private final String url3 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/rjgcxy.html&p=";
    private final String url4 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/qsxb.html&p=";
    private final String url5 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/lxy.html&p=";
    private final String url6 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/ywkxyjsxy.html&p=";
    private final String url7 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/jyxy.html&p=";
    private final String url8 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/jsjkxyjsxy.html&p=";
    private final String url9 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/mkszyxy.html&p=";
    private final String url10 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/smkxxy.html&p=";
    private final String url11 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/hykxyjsxy.html&p=";
    private final String url12 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/jxjyxy.html&p=";
    private final String url13 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/dzxxgcxy.html&p=";
    private final String url14 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/fxy.html&p=";
    private final String url15 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/wgyyywxxy.html&p=";
    private final String url16 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/jmyqygdzgcxy.html&p=";
    private final String url17 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/dqyzdhgcxy.html&p=";
    private final String url18 = "http://120news.wenjin.in/index.php?s=/Article/lists/category/hjkxygcxy.html&p=";



    private String url = url1;

    private ListView listView;
    private Activity_School_News_Adapter adapter;
    private RefreshLayout myRefreshListView ;
    private ArrayList<ActivityCollege_News> newsList;

    View mainView;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_school, container, false);


        mContext = getActivity();
        arrow = (ImageView) mainView.findViewById(R.id.arrow);
        title_layout = (RelativeLayout) mainView.findViewById(R.id.activity_choose);
        title_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                changPopState(v);

            }
        });



        listView = (ListView) mainView.findViewById(R.id.news_school_listview_2);
        myRefreshListView = (RefreshLayout) mainView.findViewById(R.id.school_news_refreshlayout);
        myRefreshListView.setColorScheme(R.color.green);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                ActivityCollege_News news = newsList.get(position);

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



        return mainView;
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
        Type listType = new TypeToken<ArrayList<ActivityCollege_News>>() {
        }.getType();

        JSONObject object = new JSONObject(arg0);

        if(index == 1){
//            ArrayList<ActivityCollege_News> newsList2;
//            newsList2 = gson.fromJson(object.getString("rsm"),
//                    listType);

            newsList = gson.fromJson(object.getString("rsm"),
                    listType);

            adapter = new Activity_School_News_Adapter(getActivity(), newsList);
            listView.setAdapter(adapter);
        }
        else{
            ArrayList<ActivityCollege_News> newsList1 = gson.fromJson(object.getString("rsm"),
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



        Intent intent = new Intent(getActivity(),Activity_College_News_Detail.class);
        intent.putExtra("html", htmlData.getContent());
        startActivity(intent);

    }

    public void changPopState(View v) {

        isOpenPop = !isOpenPop;
        if (isOpenPop) {
            arrow.setBackgroundResource(R.drawable.icon_arrow_up);
            popAwindow(v);

        } else {
            arrow.setBackgroundResource(R.drawable.icon_arrow_down);
            if (window != null) {
                window.dismiss();

            }
        }
    }

    private void popAwindow(View parent) {
        if (window == null) {
            LayoutInflater lay = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = lay.inflate(R.layout.activity_school_popwindow, null);
            popListView = (ListView) v.findViewById(R.id.activity_school_pop_list);

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), CreateData(),
                    R.layout.activity_school_popwindow_item, new String[] { KEY },
                    new int[] { R.id.activity_school_popwindow_item_title });

            popListView.setAdapter(adapter);
            popListView.setOnItemClickListener(listClickListener);
            popListView.setDivider(new ColorDrawable(Color.GRAY));
            popListView.setDividerHeight(2);

            // window = new PopupWindow(v, 260, 300);
            int x = (int) getResources().getDimension(R.dimen.pop_x);
            int y = (int) getResources().getDimension(R.dimen.pop_y);;
            window = new PopupWindow(v, x, y);
        }
        window.setBackgroundDrawable(getResources().getDrawable(
                R.color.grey));




        window.setFocusable(true);
        window.setOutsideTouchable(false);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                isOpenPop = false;
                arrow.setBackgroundResource(R.drawable.icon_arrow_down);
            }
        });
        window.update();
//        window.showAtLocation(parent, Gravity.CENTER_HORIZONTAL | Gravity.TOP,
//                0, (int) getResources().getDimension(R.dimen.pop_layout_y));

//        window.showAtLocation(parent, 0 ,
//                (int) getResources().getDimension(R.dimen.pop_layout_y),
//                (int) getResources().getDimension(R.dimen.pop_layout_x));

          window.showAsDropDown(title_layout,2, 5);

    }

    AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Map<String, Object> map = (Map<String, Object>) parent
                    .getItemAtPosition(position);

            TextView textView = (TextView)mainView.findViewById(R.id.activity_school_choice);

            if((String)map.get(KEY)=="机械学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url1;
                getData(1);
            }
            else if((String)map.get(KEY)=="经管学部"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url2;
                getData(1);
            }
            else if((String)map.get(KEY)=="软件学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url3;
                getData(1);
            }
            else if((String)map.get(KEY)=="求是学部"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url4;
                getData(1);
            }
            else if((String)map.get(KEY)=="理学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url5;
                getData(1);
            }
            else if((String)map.get(KEY)=="药物科学与技术学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url6;
                getData(1);
            }
            else if((String)map.get(KEY)=="教育学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url7;
                getData(1);
            }
            else if((String)map.get(KEY)=="计算机科学与技术学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url8;
                getData(1);
            }
            else if((String)map.get(KEY)=="马克思主义学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url9;
                getData(1);
            }
            else if((String)map.get(KEY)=="生命科学学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url10;
                getData(1);
            }
            else if((String)map.get(KEY)=="海洋科学与技术学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url11;
                getData(1);
            }
            else if((String)map.get(KEY)=="继续教育学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url12;
                getData(1);
            }
            else if((String)map.get(KEY)=="电子信息工程学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url13;
                getData(1);
            }
            else if((String)map.get(KEY)=="法学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url14;
                getData(1);
            }
            else if((String)map.get(KEY)=="外国语言与文学学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url15;
                getData(1);
            }
            else if((String)map.get(KEY)=="精密仪器与光电子工程学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url16;
                getData(1);
            }
            else if((String)map.get(KEY)=="电气与自动化工程学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url17;
                getData(1);
            }
            else if((String)map.get(KEY)=="环境科学与工程学院"){
                index = 1;
                textView.setText((String)map.get(KEY));
                url = url18;
                getData(1);
            }

            if (window != null) {
                window.dismiss();

            }

        }
    };

    public ArrayList<Map<String, Object>> CreateData() {

        Map<String, Object> map;

        map = new HashMap<String, Object>();
        map.put(KEY, "机械学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "经管学部");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "软件学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "求是学部");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "理学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "药物科学与技术学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "教育学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "计算机科学与技术学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "马克思主义学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "生命科学学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "海洋科学与技术学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "继续教育学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "电子信息工程学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "法学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "外国语言与文学学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "精密仪器与光电子工程学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "电气与自动化工程学院");
        items.add(map);
        map = new HashMap<String, Object>();
        map.put(KEY, "环境科学与工程学院");
        items.add(map);

        return items;

    }
}
