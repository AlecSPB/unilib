package com.seoulapp.unilib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seoulapp.unilib.views.SlidingTabLayout;

/** google UI web site : http://www.google.com/design/spec/material-design/introduction.html#introduction-goals **/
public class MainActivity extends BaseAppCompatActivity {
    private Toolbar toolbar;
    private ViewPager mPager;
    private ViewPagerAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mPager);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private String[] mPageTitle = {"BOOK SHARING", "MY PAGE"};
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            MyFragment myFragment = MyFragment.getInstance(i);
            return myFragment;
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
    public static class MyFragment extends Fragment {
        private TextView textView;
        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_my, container, false);
            textView = (TextView)view.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if(bundle != null) {
                textView.setText("The page Number is " + bundle.getInt("position"));
            }
            return view;
        }
    }
}
