package com.xiepeng.twttest;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.xiepeng.twttest.Activity.ActivityFragment;
import com.xiepeng.twttest.Discuss.DiscussFragment;
import com.xiepeng.twttest.Donate.DonateFragment;
import com.xiepeng.twttest.News.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //导航菜单的listView
    private ListView mDrawerList;
    private ArrayList<String> menuLists;
    private ArrayAdapter<String> adapter;

    private List<DrawerItem> list = new ArrayList<DrawerItem>();

    private NewsFragment contentFragment0;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        DrawerItem item1 = new DrawerItem("咨询",R.mipmap.News);
//        DrawerItem item2 = new DrawerItem("活动",R.mipmap.Forum);
//        DrawerItem item3 = new DrawerItem("论坛",R.mipmap.Comment);
//        DrawerItem item4 = new DrawerItem("捐赠",R.mipmap.Donation);


        menuLists = new ArrayList<String>();
//        menuLists.add(item1);
//        menuLists.add(item2);
//        menuLists.add(item3);
//        menuLists.add(item4);
        menuLists.add("资讯");
        menuLists.add("活动");
        menuLists.add("论坛");
        menuLists.add("捐赠");
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,menuLists);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("");

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        contentFragment0 = new NewsFragment();
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, contentFragment0)
                .commit();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //需要将ActionDrawerToggle与DrawerLayout的状态同步
        //将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Button的Icon
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //将ActionBar上的图标与Drawer结合起来,以下这个if判断实现点击左上角图标，打开抽屉
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        switch (position){
            case 0:
                NewsFragment contentFragment_0 = new NewsFragment();
//                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment_0)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 1:
                ActivityFragment contentFragment1 = new ActivityFragment();
//                FragmentManager fm1 = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment1)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 2:
                DiscussFragment contentFragment2 = new DiscussFragment();
//                FragmentManager fm2 = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment2)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 3:
                DonateFragment contentFragment3 = new DonateFragment();
//                FragmentManager fm3 = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment3)
                        .commit();
                mDrawerLayout.closeDrawer(mDrawerList);
                break;

        }

    }
}
