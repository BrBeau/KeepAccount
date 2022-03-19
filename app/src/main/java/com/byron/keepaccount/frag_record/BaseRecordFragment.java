package com.byron.keepaccount.frag_record;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.byron.keepaccount.R;
import com.byron.keepaccount.adapter.TypeBaseAdapter;
import com.byron.keepaccount.bean.AccountBean;
import com.byron.keepaccount.bean.TypeBean;
import com.byron.keepaccount.interf.KeyboardEnsureListener;
import com.byron.keepaccount.utils.KeyboardUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class BaseRecordFragment extends Fragment implements View.OnClickListener{

    public String TAG = BaseRecordFragment.class.getSimpleName();
    //将需要插入到记账本钟的数据保存成一个对象的形式
    public AccountBean mAccountBean;
    public List<TypeBean> mTypeBeanList;
    public TypeBaseAdapter mTypeBaseAdapter;

    //public ImageView mMoreView;
    public ImageView mTypeIv;  //gv里子item的图片
    public TextView mBeiZhuTv, mTimeTv, mTypeTv;
    public GridView mGridView;
    public KeyboardView mKeyboardView;
    public EditText mMoneyEt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountBean = new AccountBean();
        mAccountBean.setTypeName("其它");
        mAccountBean.setImageId(R.mipmap.ic_qita_fs);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_out, container, false);
        initView(v);
        createKeyboard();
        setCurTime();
        loadDataToGv();
        setGvListener();
        return v;
    }

    /**
     * 初始化布局文件
     * @param view
     */
    public void initView(View view){
        mTypeIv = view.findViewById(R.id.out_more_iv);
        mBeiZhuTv = view.findViewById(R.id.out_record_remark);
        mTimeTv = view.findViewById(R.id.out_record_time);
        mGridView = view.findViewById(R.id.out_grid_view);
        mKeyboardView = view.findViewById(R.id.out_keyboard_view);
        mMoneyEt = view.findViewById(R.id.out_record_money);
        mTypeTv = view.findViewById(R.id.out_more_tip);
        mBeiZhuTv.setOnClickListener(this);
        mTimeTv.setOnClickListener(this);

    }

    /**
     * 创建自定义软键盘
     */
    public void createKeyboard(){
        KeyboardUtil keyboardUtil = new KeyboardUtil(mKeyboardView, mMoneyEt);
        keyboardUtil.showKeyboard();
        keyboardUtil.setEnsureListener(new KeyboardEnsureListener() {
            @Override
            public void onEnsure() {
                //获取editText输入的金额
                Log.d(TAG, "键盘点击完成");
                String moneyStr = mMoneyEt.getText().toString();
                if (TextUtils.isEmpty(moneyStr) || moneyStr.equals("0")){
                    getActivity().finish();
                    return;
                }
                float money = Float.parseFloat(moneyStr);
                mAccountBean.setMoney(money);
                saveAccountBeanToDb();
                getActivity().finish();

            }
        });

    }

    /**
     * 让子类去实现
     * caz 会有支出和收入两个fragment
     */
    public abstract void saveAccountBeanToDb();

    /**
     * 设置当前时间显示到timeView上
     */
    public void setCurTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String time = simpleDateFormat.format(date);
        //设置timeTv标签
        mTimeTv.setText(time);
        mAccountBean.setTime(time);

        //获取当前时间的年月日
        Calendar calendar = Calendar.getInstance();
        mAccountBean.setYear(calendar.get(Calendar.YEAR));
        mAccountBean.setMonth(calendar.get(Calendar.MONTH) +1);
        mAccountBean.setDay(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 加载数据到mGridView里
     */
    public void loadDataToGv(){
        mTypeBeanList = new ArrayList<>();
        mTypeBaseAdapter = new TypeBaseAdapter(getContext(), mTypeBeanList);
        mGridView.setAdapter(mTypeBaseAdapter);

    }

    /**
     * 设置gv每个item的点击事件
     * 点击gv的子item数据变化
     */
    public void setGvListener(){
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTypeBaseAdapter.mPosition = position;
                //提示绘制已经发生了变化
                mTypeBaseAdapter.notifyDataSetChanged();
                TypeBean typeBean = mTypeBeanList.get(position);
                String typeName = typeBean.getTypeName();
                mTypeTv.setText(typeName);
                mAccountBean.setTypeName(typeName);
                int selectedIv = typeBean.getSelectedImageId();
                mTypeIv.setImageResource(selectedIv);
                mAccountBean.setImageId(selectedIv);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.out_record_time:
                Log.d(TAG, "onClick 时间");
                break;

            case R.id.out_record_remark:
                Log.d(TAG, "onClick 备注");
                break;

        }

    }
}
