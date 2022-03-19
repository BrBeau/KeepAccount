package com.byron.keepaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String Tag = MainActivity.class.getSimpleName();
    private View mListView;
    private ImageView mMoreBtn, mSearchIv;
    private Button mRecordBtn;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurTime();
        initView();
    }


    private void initView(){
        mListView = findViewById(R.id.m_lv);
        mMoreBtn = findViewById(R.id.m_more_imgbtn);
        mRecordBtn = findViewById(R.id.m_record_btn);
        mSearchIv = findViewById(R.id.m_search_img);

        mMoreBtn.setOnClickListener(this);
        mRecordBtn.setOnClickListener(this);
        mSearchIv.setOnClickListener(this);

    }

    /**
     * 获取当前时间
     */
    private void getCurTime(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.m_more_imgbtn:
                Log.d(Tag, "更多图标被点击");
                break;

            case R.id.m_record_btn:
                Log.d(Tag, "记一笔按钮被点击");
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
                break;

            case R.id.m_search_img:
                Log.d(Tag, "搜索图标被点击");
                break;
        }
    }

}
