package com.xiepeng.twttest;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.xiepeng.twttest.Activity.ActivityFragment;
import com.xiepeng.twttest.Discuss.DiscussFragment;
import com.xiepeng.twttest.Donate.DonateFragment;
import com.xiepeng.twttest.News.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private NewsFragment contentFragment0;
    private FragmentManager fm;

    private LinearLayout l1;
    private LinearLayout l2;
    private LinearLayout l3;
    private LinearLayout l4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        //用ToolBar代替ActionBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("天津大学120周年校庆");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.blank, R.string.blank);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        contentFragment0 = new NewsFragment();
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, contentFragment0)
                .commit();

        l1 = (LinearLayout)findViewById(R.id.nav1);
        l2 = (LinearLayout)findViewById(R.id.nav2);
        l3 = (LinearLayout)findViewById(R.id.nav3);
        l4 = (LinearLayout)findViewById(R.id.nav4);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsFragment contentFragment_0 = new NewsFragment();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment_0)
                        .commit();
                mDrawerLayout.closeDrawers();
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFragment contentFragment1 = new ActivityFragment();

                fm.beginTransaction().replace(R.id.content_frame, contentFragment1)
                        .commit();
                mDrawerLayout.closeDrawers();
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscussFragment contentFragment2 = new DiscussFragment();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment2)
                        .commit();
                mDrawerLayout.closeDrawers();
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateFragment contentFragment3 = new DonateFragment();
                fm.beginTransaction().replace(R.id.content_frame, contentFragment3)
                        .commit();
                mDrawerLayout.closeDrawers();
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //需要将ActionDrawerToggle与DrawerLayout的状态同步
        //将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Button的Icon
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen( Gravity.LEFT )) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //将ActionBar上的图标与Drawer结合起来,以下这个if判断实现点击左上角图标，打开抽屉
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
