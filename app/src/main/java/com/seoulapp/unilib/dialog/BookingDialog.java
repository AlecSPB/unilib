package com.seoulapp.unilib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.seoulapp.unilib.R;
import com.seoulapp.unilib.interfaces.HttpServerSuccess;
import com.seoulapp.unilib.network.ProcessHttpTask;
import com.seoulapp.unilib.views.NumberPicker;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by SAMSUNG on 2015-10-28.
 */
public class BookingDialog extends Dialog {
    private Context mContext;
    private TextView mFromDate;
    private TextView mToDate;
    private int mYear;
    private int mFromMonth;
    private int mFromDay;
    private int mToMonth;
    private int mToDay;

    public BookingDialog(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public BookingDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
        init();
    }

    private void init() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_date_picker);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mFromMonth = calendar.get(Calendar.MONTH) + 1;
        mFromDay = calendar.get(Calendar.DAY_OF_MONTH);
        mToMonth = calendar.get(Calendar.MONTH) + 1;
        mToDay = calendar.get(Calendar.DAY_OF_MONTH);

        ImageView closeBtn = (ImageView) findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mFromDate = (TextView) findViewById(R.id.from_date);
        mToDate = (TextView) findViewById(R.id.to_date);
        NumberPicker fromMonth = (NumberPicker) findViewById(R.id.from_month);
        fromMonth.setOnValueChangedListener(mOnValueChangeListener);
        NumberPicker fromDay = (NumberPicker) findViewById(R.id.from_day);
        fromDay.setOnValueChangedListener(mOnValueChangeListener);
        NumberPicker toMonth = (NumberPicker) findViewById(R.id.to_month);
        toMonth.setOnValueChangedListener(mOnValueChangeListener);
        NumberPicker toDay = (NumberPicker) findViewById(R.id.to_day);
        toDay.setOnValueChangedListener(mOnValueChangeListener);
        setNumberPickerValue(fromMonth, 12);
        setNumberPickerValue(toMonth, 12);
        setNumberPickerValue(fromDay, 31);
        setNumberPickerValue(toDay, 31);
        fromMonth.setValue(mFromMonth);
        fromDay.setValue(mFromDay);
        toMonth.setValue(mToMonth);
        toDay.setValue(mToDay);
//        getBookDate();
        final ImageView ivBookType = (ImageView)findViewById(R.id.book_type_img);
        final TextView tvBookType = (TextView)findViewById(R.id.book_type_txt);
        final TextView tvBookTitle = (TextView)findViewById(R.id.book_title);
        final TextView tvBookWriter = (TextView)findViewById(R.id.book_writer);
        final TextView tvBookIntro = (TextView)findViewById(R.id.book_intro);
        String url = "http://133.130.106.19:5000/books/5631195cdc064a93128883a0";
        final String[] jsonName = {"_id","name", "author", "intro", "owner", "genre", "status", "regYmdt"};
        ProcessHttpTask task =  new ProcessHttpTask();
        task.setHttpServerSuccess(new HttpServerSuccess() {
            @Override
            public void onSuccess(Map map) {
                if(map != null) {
                    String bookType = (String) map.get(jsonName[5]);
                    switch(bookType) {
                        // 수필
                        case "essay":
                            ivBookType.setImageResource(R.drawable.n_icon1);
                            tvBookType.setText(R.string.book_type_1);
                            break;
                        // 자기 개발
                        case "self":
                            ivBookType.setImageResource(R.drawable.n_icon2);
                            tvBookType.setText(R.string.book_type_2);
                            break;
                        // 로맨스
                        case "romance":
                            ivBookType.setImageResource(R.drawable.n_icon3);
                            tvBookType.setText(R.string.book_type_3);
                            break;
                        // 여행
                        case "travel":
                            ivBookType.setImageResource(R.drawable.n_icon4);
                            tvBookType.setText(R.string.book_type_4);
                            break;
                        // 교육
                        case "edu":
                            ivBookType.setImageResource(R.drawable.n_icon5);
                            tvBookType.setText(R.string.book_type_5);
                            break;
                        // 뷰티
                        case "beauty":
                            ivBookType.setImageResource(R.drawable.n_icon6);
                            tvBookType.setText(R.string.book_type_6);
                            break;
                        // 스릴러
                        case "thrill":
                            ivBookType.setImageResource(R.drawable.n_icon7);
                            tvBookType.setText(R.string.book_type_7);
                            break;
                        default:
                            ivBookType.setImageResource(R.drawable.n_icon1);
                            tvBookType.setText(bookType);
                            break;
                    }
                }
                tvBookTitle.setText(map.get(jsonName[1]) == null ? "" : (String)map.get(jsonName[1]));
                tvBookWriter.setText(map.get(jsonName[2]) == null ? "" : (String)map.get(jsonName[2]));
                tvBookIntro.setText(map.get(jsonName[3]) == null ? "" : (String)map.get(jsonName[3]));
            }
        });
        task.execute(url, jsonName);
        updateDateValue();
    }

    private void setNumberPickerValue(NumberPicker month, int maxValue) {
        month.setMaxValue(maxValue);
        month.setMinValue(1);
        month.setFocusable(true);
        month.setFocusableInTouchMode(true);
    }

    private NumberPicker.OnValueChangeListener mOnValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            int id = picker.getId();
            switch (id) {
                case R.id.from_month:
                    mFromMonth = newVal;
                    break;
                case R.id.from_day:
                    mFromDay = newVal;
                    break;
                case R.id.to_month:
                    mToMonth = newVal;
                    break;
                case R.id.to_day:
                    mToDay = newVal;
                    break;
            }
            updateDateValue();
        }
    };

    private void updateDateValue() {
        mFromDate.setText(mYear + "." + mFromMonth + "." + mFromDay);
        mToDate.setText(mYear + "." + mToMonth + "." + mToDay);
    }
}