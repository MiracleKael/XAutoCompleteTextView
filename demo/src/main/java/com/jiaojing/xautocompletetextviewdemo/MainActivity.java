package com.jiaojing.xautocompletetextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.jiaojing.xautocompletetextview.XAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private AppCompatAutoCompleteTextView autocompletetextview;
    private XAutoCompleteTextView xautocompletetextview;
    private String KEY = "history";
    private ArrayAdapter mSearchAdapter;
    private String[] mSearchHistoryArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);


        autocompletetextview = (AppCompatAutoCompleteTextView) findViewById(R.id.autocompletetextview);
        xautocompletetextview = (XAutoCompleteTextView) findViewById(R.id.xautocompletetextview);
//        AppCompatButton button1 = (AppCompatButton) findViewById(R.id.button1);

        initHistoryData();//初始化历史记录


        mSearchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mSearchHistoryArray);

        autocompletetextview.setAdapter(mSearchAdapter);

    }

    private void initHistoryData() {
        mSearchHistoryArray = SPUtils.get(this, KEY, "").split(",");
    }

    public void onButton(View view){
        String s = autocompletetextview.getText().toString().trim();
        SPUtils.put(this, KEY, s);
        mSearchAdapter.add(s);
    }
    public void onButton2(View view){
        String s = xautocompletetextview.getText().toString().trim();
        SPUtils.put(this, KEY, s);
        mSearchAdapter.add(s);
    }
    public void onButton3(View view){

    }
}
