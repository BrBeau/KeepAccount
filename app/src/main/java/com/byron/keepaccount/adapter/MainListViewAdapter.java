package com.byron.keepaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.byron.keepaccount.R;
import com.byron.keepaccount.bean.AccountBean;

import java.util.Calendar;
import java.util.List;

/**
 * MainActivity listView控件的适配器
 * @author Byron
 * @date 220320
 */
public class MainListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<AccountBean> mAccountDataList;
    private int mYear, mMonth, mDay;
    private LayoutInflater mLayoutInflater;

    public MainListViewAdapter(Context context, List<AccountBean> accountDataList){
        mContext = context;
        mAccountDataList = accountDataList;
        mLayoutInflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

    }
    @Override
    public int getCount() {
        return mAccountDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAccountDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.m_item_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AccountBean accountBean = mAccountDataList.get(position);
        viewHolder.mTypeIv.setImageResource(accountBean.getImageId());
        viewHolder.mTypeTv.setText(accountBean.getTypeName());
        viewHolder.mBeizhuTv.setText(accountBean.getBeiZhu());
        viewHolder.mTimeTv.setText(accountBean.getTime());
        viewHolder.mMoneyTv.setText("￥ " + accountBean.getMoney());


        return convertView;
    }
}

class ViewHolder{
    ImageView mTypeIv;
    TextView mTypeTv, mBeizhuTv, mMoneyTv, mTimeTv;
    public ViewHolder(View v){
        mTypeIv = v.findViewById(R.id.m_item_iv);
        mTypeTv = v.findViewById(R.id.m_item_title);
        mBeizhuTv = v.findViewById(R.id.m_item_beizhu);
        mMoneyTv = v.findViewById(R.id.m_item_money);
        mTimeTv = v.findViewById(R.id.m_item_time);
    }
}
