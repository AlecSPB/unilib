package com.seoulapp.unilib.booklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.seoulapp.unilib.R;
import com.seoulapp.unilib.adapter.BookListAdapter;

/**
 * Created by SAMSUNG on 2015-10-17.
 */
public class BookListFragment extends Fragment {
    private BookListAdapter mListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.book_list_view);
        mListAdapter = new BookListAdapter(getActivity());
        listView.setAdapter(mListAdapter);
        return rootView;
    }
}
