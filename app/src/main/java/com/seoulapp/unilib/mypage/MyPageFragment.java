package com.seoulapp.unilib.mypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.seoulapp.unilib.R;

/**
 * Created by SAMSUNG on 2015-10-18.
 */
public class MyPageFragment extends Fragment {
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_page,container,false);
        mListView = (ListView)rootView.findViewById(R.id.my_page_list_view);
        View headerView = inflater.inflate(R.layout.header_my_page, null, false);
        mListView.addHeaderView(headerView);
        return rootView;
    }
}
