package com.jiaojing.xautocompletetextview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jiaojing on 2017/12/25.
 */

public class XAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private String TAG = getClass().getSimpleName();

    private static final String FILE_NAME = "serach_history";
    private static final String KEY = "history";

    private static final int MAX_ONCE_MATCHED_ITEM = 5; // 提示框最多要显示的记录行数

    private Context mContext;
    private XAutoCompleteAdapter xAutoCompleteAdapter;
    private ArrayAdapter mSearchAdapter;


    public XAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public XAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initHistory();
    }

    private void initHistory() {
        String history = spGet();
        String[] historyArray = history.split(",");
        Log.e(TAG, "historyArray.length==="+ historyArray.length);
        ArrayList<String> historyList = new ArrayList<>();
        historyList.addAll( Arrays.asList(historyArray));

        xAutoCompleteAdapter = new XAutoCompleteAdapter(mContext, historyList);
//        mSearchAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_dropdown_item_1line, historyArray);

        final int simpleItemHeight = xAutoCompleteAdapter.getSimpleItemHeight();
        xAutoCompleteAdapter.setOnFilterResultsListener(new XAutoCompleteAdapter.OnFilterResultsListener() {
            @Override
            public void onFilterResultsListener(int count) {
                if(count >  MAX_ONCE_MATCHED_ITEM) {//超过了设置的最大item条数
                    setDropDownHeight(simpleItemHeight * MAX_ONCE_MATCHED_ITEM);
                }else {
                    setDropDownHeight(simpleItemHeight * count);
                }
            }
        });

        this.setAdapter(xAutoCompleteAdapter);
    }


    /**
     * SharedPreferences存值
     */
    public void spPut(String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, value).commit();
    }

    /**
     * SharedPreferences取值
     *
     * @return
     */
    public String spGet() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY, "");
    }

}
