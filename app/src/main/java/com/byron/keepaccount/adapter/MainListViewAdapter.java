package com.byron.keepaccount.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.byron.keepaccount.bean.AccountBean;

import java.util.List;

/**
 * MainActivity listView控件的适配器
 * @author Byron
 * @date 220320
 */
public class MainListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<AccountBean> mAccountDataList;

    public MainListViewAdapter(Context context, List<AccountBean> accountDataList){
        mContext = context;
        mAccountDataList = accountDataList;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
