package com.seoulapp.unilib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.seoulapp.unilib.views.SlidingTabLayout;


public class MainActivity extends BaseAppCompatActivity {
    private ViewPager mPager;
    private ViewPagerAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.fragment_container);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.header);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.br_blue));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{

        private String[] mPageTitle = {"BOOK SHARING", "MY PAGE"};
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPageTitle[position];
        }
    }
}
