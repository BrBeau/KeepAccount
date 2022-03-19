package com.byron.keepaccount.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class RecordPagerAdapter extends FragmentPagerAdapter {

    private String Tag = RecordPagerAdapter.class.getSimpleName();
    private List<Fragment> mFragementList;
    private String[] mTitle ={"支出", "收入"};

    public RecordPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        //获取到fragment的数组对象
        mFragementList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragementList.get(position);
    }

    @Override
    public int getCount() {
        return mFragementList.size();
    }

    //viewPager的标题
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
