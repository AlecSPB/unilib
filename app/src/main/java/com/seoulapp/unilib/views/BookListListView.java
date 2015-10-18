package com.seoulapp.unilib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoulapp.unilib.R;

/**
 * Created by SAMSUNG on 2015-10-17.
 */
public class BookListListView extends LinearLayout {
    private int count = 3;
    private Context mContext;
    public BookListListView(Context context) {
        super(context);
        mContext = context;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(params);
        setBookListView();
        setMinimumHeight(70);
    }

    public BookListListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(params);
        setBookListView();
        setMinimumHeight(70);
    }

    public BookListListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(params);
        setBookListView();
        setMinimumHeight(70);
    }
    public void setBookListView() {
        for(int i=0; i<count; i++) {
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = li.inflate(R.layout.book_list_listview_item, this, false);
            TextView tvBookTitle = (TextView)view.findViewById(R.id.book_title);
            addView(view, i);
        }
    }
}
