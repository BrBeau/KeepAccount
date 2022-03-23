package com.byron.keepaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.byron.keepaccount.adapter.MainListViewAdapter;
import com.byron.keepaccount.bean.AccountBean;
import com.byron.keepaccount.db.DbManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String Tag = MainActivity.class.getSimpleName();
    private ListView mListView;
    private View mHeadView;
    private ImageView mMoreBtn, mSearchIv;
    private Button mRecordBtn;
    private int year, month, day;

    //ListView的header控件
    private TextView mHeadExpanseTv, mHeadIncomeTv, mHeadBugetLeftTv, mHeadTodayTv;
    private ImageView mHeadHideMoney;

    private boolean isHideMoney = true;

    private List<AccountBean> mAccountDataList;
    private MainListViewAdapter mAdapter;

    private SharedPreferences msp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurTime();
        initView();
        addListViewHead();

        msp = getSharedPreferences("budget", Context.MODE_PRIVATE);

        //加载ListView数据
        mAccountDataList = new ArrayList<>();
        mAdapter = new MainListViewAdapter(this, mAccountDataList);
        mListView.setAdapter(mAdapter);

        //获取数据库数据加载到ListView里

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

    private void addListViewHead(){
        //listView要设置adapter依附于view才能正常显示
        mHeadView = getLayoutInflater().inflate(R.layout.m_item_lv_head, null);
        mListView.addHeaderView(mHeadView);

        mHeadExpanseTv = mHeadView.findViewById(R.id.m_item_head_expanse);
        mHeadIncomeTv = mHeadView.findViewById(R.id.m_item_head_income);
        mHeadBugetLeftTv = mHeadView.findViewById(R.id.m_item_head_budget_left);
        mHeadTodayTv = mHeadView.findViewById(R.id.m_item_head_today_tv);
        mHeadHideMoney = mHeadView.findViewById(R.id.m_head_hide_money);

        mHeadHideMoney.setOnClickListener(this);

    }

    /**
     * 获取当前时间
     */
    private void getCurTime(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
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

            case R.id.m_head_hide_money:
                //设置金额的显隐功能
                hideMoneyToggle();
                break;
        }
    }

    // 当activity获取焦点时，会调用的方法
    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
//        setTopTvShow();
    }


    /**
     * 加载sqlLite的数据到listView里
     */
    private void loadDBData(){
        List<AccountBean> accountList = DbManager.getAccountData(year, month, day);
        mAccountDataList.clear();
        if (accountList!=null){
            mAccountDataList.addAll(accountList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void hideMoneyToggle(){
        if (isHideMoney){
            PasswordTransformationMethod ptm = PasswordTransformationMethod.getInstance();
            mHeadExpanseTv.setTransformationMethod(ptm);
            mHeadIncomeTv.setTransformationMethod(ptm);
            mHeadBugetLeftTv.setTransformationMethod(ptm);
            mHeadHideMoney.setImageResource(R.drawable.m_head_hide);
            isHideMoney = false;
        }else{
            HideReturnsTransformationMethod hrtm = HideReturnsTransformationMethod.getInstance();
            mHeadExpanseTv.setTransformationMethod(hrtm);
            mHeadIncomeTv.setTransformationMethod(hrtm);
            mHeadBugetLeftTv.setTransformationMethod(hrtm);
            mHeadHideMoney.setImageResource(R.drawable.m_head_show);
            isHideMoney = true;
        }
    }

}
