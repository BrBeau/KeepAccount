package com.byron.keepaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.byron.keepaccount.adapter.RecordPagerAdapter;
import com.byron.keepaccount.frag_record.IncomeFragment;
import com.byron.keepaccount.frag_record.OutFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    public TabLayout mTabLayout;
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //初始化控件
        mTabLayout = findViewById(R.id.record_tabLayout);
        mViewPager = findViewById(R.id.record_viewPager);
        initPagerView();
    }

    /**
     * 初始化pageView页面
     */
    public void initPagerView(){

        //因为pagerView是可以翻动的，所以需要有一个集合或数组来存放究竟有几个pager
        List<Fragment> fragmentList = new ArrayList<>();
        //把支出和收入的fragment给存放到list列表里
        OutFragment outFragment = new OutFragment();
        IncomeFragment incomeFragment = new IncomeFragment();
        fragmentList.add(outFragment);
        fragmentList.add(incomeFragment);

        //创建适配器
        RecordPagerAdapter recordPagerAdapter = new RecordPagerAdapter(getSupportFragmentManager(), fragmentList);
        //设置适配器
        mViewPager.setAdapter(recordPagerAdapter);
        //将tabLayout和ViewPager进行关联
        mTabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * ImageView的点击事件
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.record_back:
                finish();
                break;
        }

    }
}
