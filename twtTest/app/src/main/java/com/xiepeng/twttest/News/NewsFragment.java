package com.xiepeng.twttest.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiepeng.twttest.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/8/21.
 */
public class NewsFragment extends Fragment {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private ImageView mTabline;
    private int mScreen1_3;

    private int mCurrentPageIndex  = 0;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment, container, false);

        initTabLine();
        initView();

        LinearLayout l1 = (LinearLayout)view.findViewById(R.id.l1);
        LinearLayout l2 = (LinearLayout)view.findViewById(R.id.l2);

        l1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mViewPager.setCurrentItem(0);

            }

        });

        l2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mViewPager.setCurrentItem(1);

            }

        });

        return view;
    }

    private void initTabLine()
    {
        mTabline = (ImageView) view.findViewById(R.id.id_iv_tabline);
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 2;
        LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    private void initView()
    {
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewpager);

        mDatas = new ArrayList<Fragment>();

        SchoolNews_Fragment tab01 = new SchoolNews_Fragment();
        Topic_Fragment tab02 = new Topic_Fragment();

        mDatas.add(tab01);
        mDatas.add(tab02);

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx)
            {

                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset
                            * mScreen1_3);
                }
                mTabline.setLayoutParams(lp);

            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // TODO Auto-generated method stub

            }
        });

    }
}
