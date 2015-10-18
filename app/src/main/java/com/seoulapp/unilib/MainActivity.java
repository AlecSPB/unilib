package com.seoulapp.unilib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seoulapp.unilib.booklist.BookListFragment;
import com.seoulapp.unilib.mypage.MyPageFragment;
import com.seoulapp.unilib.views.SlidingTabLayout;

/** google UI web site : http://www.google.com/design/spec/material-design/introduction.html#introduction-goals **/
public class MainActivity extends BaseAppCompatActivity {
    private ViewPager mPager;
    private ViewPagerAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleText("능동");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tabs);
        int[] resId = {R.drawable.tab_book_list_selector, R.drawable.tab_my_page_selector};
        mSlidingTabLayout.setTitleResId(resId);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mPager);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new BookListFragment();
                    break;
                case 1:
                    fragment = new MyPageFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}
