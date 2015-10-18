package com.seoulapp.unilib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.seoulapp.unilib.R;

/**
 * Created by SAMSUNG on 2015-10-17.
 */
public class BookListAdapter extends BaseAdapter {
    private Context mContext;

    public BookListAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.fragment_book_list_item, parent, false);
        }
        return convertView;
    }
}
