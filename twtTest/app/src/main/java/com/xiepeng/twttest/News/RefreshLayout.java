package com.xiepeng.twttest.News;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.xiepeng.twttest.R;

/**
 * ?????SwipeRefreshLayout,??????????????????????????????.
 * 
 * 
 */
public class RefreshLayout extends SwipeRefreshLayout implements OnScrollListener {

    /**
     * ???????????????????????
     */

    private int mTouchSlop;
    /**
     * listview???
     */
    private ListView mListView;

    /**
     * ??????????, ?????????????????????
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView???????footer
     */
    private View mListViewFooter;

    /**
     * ???????y????
     */
    private int mYDown;
    /**
     * ??????y????, ??mYDown?????????????????§Ø???????????????
     */
    private int mLastY;
    /**
     * ?????????? ( ??????????? )
     */
    private boolean isLoading = false;

    /**
     * @param context
     */
    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.news_refresh_foot, null,
                false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // ?????ListView????
        if (mListView == null) {
            getListView();
        }
    }

    /**
     * ???ListView????
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                // ???¨´???????????ListView, ??¨´??????????????????????
                mListView.setOnScrollListener(this);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // ????
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // ???
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // ???
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * ????????????, ?????????????, listview?????????, ???????????.
     * 
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * ?§Ø??????????
     */
    private boolean isBottom() {

        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    /**
     * ?????????????
     * 
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * ???????????,??????????????.??????onLoad????
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            // ??????
            setLoading(true);
            //
            mOnLoadListener.onLoad();
        }
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
        // ????????????????????????
        if (canLoad()) {
            loadData();
        }
    }

    /**
     * ?????????????
     * 
     * @author mrsimple
     */
    public static interface OnLoadListener {
        public void onLoad();
    }
}
