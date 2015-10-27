package com.seoulapp.unilib.mypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.seoulapp.unilib.R;
import com.seoulapp.unilib.adapter.MyBookListAdapter;

/**
 * Created by SAMSUNG on 2015-10-18.
 */
public class MyPageFragment extends Fragment {
    private RadioGroup mTabGroup;
    private View mMyBookList;
    private View mMyBookHistory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_page, container, false);
        mTabGroup = (RadioGroup) rootView.findViewById(R.id.tab_group);
        mTabGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mMyBookList = rootView.findViewById(R.id.my_book_list_frame);
        mMyBookHistory = rootView.findViewById(R.id.my_book_history_frame);

        return rootView;
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int id = radioGroup.getCheckedRadioButtonId();
            switch(id) {
                case R.id.my_book_list_tab :
                    mMyBookList.setVisibility(View.VISIBLE);
                    mMyBookHistory.setVisibility(View.GONE);
                    break;
                case R.id.my_book_history_tab:
                    mMyBookList.setVisibility(View.GONE);
                    mMyBookHistory.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}