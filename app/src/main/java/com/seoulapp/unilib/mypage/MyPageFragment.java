package com.seoulapp.unilib.mypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.seoulapp.unilib.R;
import com.seoulapp.unilib.adapter.MyBookListAdapter;

/**
 * Created by SAMSUNG on 2015-10-18.
 */
public class MyPageFragment extends Fragment {
    private ListView mListView;
    private MyBookListAdapter mListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_page,container,false);
        mListView = (ListView)rootView.findViewById(R.id.my_page_list_view);
        mListAdapter = new MyBookListAdapter(getActivity());
        View headerView = inflater.inflate(R.layout.header_my_page, null, false);
        mListView.addHeaderView(headerView);
        mListView.setAdapter(mListAdapter);
        return rootView;
    }
}
