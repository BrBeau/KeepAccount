package com.byron.keepaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.byron.keepaccount.R;
import com.byron.keepaccount.bean.TypeBean;

import java.util.List;

/**
 * 收入，支出fragment里的gridView适配器
 * @author Byron
 * @date 220302
 */
public class TypeBaseAdapter extends BaseAdapter {

    private List<TypeBean> mTypeBeanData;
    private Context mContext;
    public int mPosition = 0;

    public TypeBaseAdapter(Context context, List<TypeBean> typeBeanList){
        mTypeBeanData = typeBeanList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTypeBeanData.size();
    }

    @Override
    public Object getItem(int position) {
        return mTypeBeanData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.record_item_gv, parent,false);
        //查找record_item_gv布局当中的控件
        ImageView imageView = convertView.findViewById(R.id.record_item_iv);
        TextView textView = convertView.findViewById(R.id.record_item_tv);
        //获取指定位置的数据源
        TypeBean typeBean = mTypeBeanData.get(position);
        textView.setText(typeBean.getTypeName());

        //判断当前是否处于选中位置; 如果选中就增加颜色进行区分
        if (mPosition == position){
            imageView.setImageResource(typeBean.getSelectedImageId());
        } else{
            imageView.setImageResource(typeBean.getSelectImageId());
        }
        return convertView;
    }
}
