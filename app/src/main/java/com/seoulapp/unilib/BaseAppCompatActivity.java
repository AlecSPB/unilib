package com.seoulapp.unilib;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by pkm on 2015-05-11.
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    private TextView mTvActionbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowTitleEnabled(false);
            actionbar.setDisplayShowCustomEnabled(true);

            actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionbar.setCustomView(R.layout.actionbar_default_title_layout);

            View actionbarCustomView = actionbar.getCustomView();
            Toolbar parent =(Toolbar) actionbarCustomView.getParent();
            parent.setContentInsetsAbsolute(0, 0); // left, right padding remove

           /* View btnBack = actionbarCustomView.findViewById(R.id.btn_actionbar_default_back);
            btnBack.setTag(ButtonType.BACK);
            btnBack.setOnClickListener(mOnClickListener);*/

            mTvActionbarTitle = (TextView)actionbarCustomView.findViewById(R.id.title_actionbar_txt);

        }
    }

    protected void setTitleText(String text){
        mTvActionbarTitle.setText(text);
    }

    protected TextView getTitleView(){
        return mTvActionbarTitle;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*ButtonType type = (ButtonType)v.getTag();
            switch(type){
                case BACK:
                    finish();
                    break;
            }*/
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        VolleySingleton.getInstance(this).cancelAllRequestQueue(this);
    }

    protected View getTitleCustomActionBar() {
        ActionBar actionbar = getSupportActionBar();
        return actionbar.getCustomView();
    }

    protected void addView(View v, RelativeLayout.LayoutParams params) {
        ViewGroup titleFrame = (ViewGroup)getTitleCustomActionBar();
        titleFrame.addView(v, params);
    }

    protected void showView(View frame){
        frame.setVisibility(View.VISIBLE);
    }
    protected void hideView(View frame){
        frame.setVisibility(View.GONE);
    }
}
