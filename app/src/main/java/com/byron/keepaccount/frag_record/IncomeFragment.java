package com.byron.keepaccount.frag_record;


import android.content.ContentValues;

import androidx.fragment.app.Fragment;

import com.byron.keepaccount.R;
import com.byron.keepaccount.bean.TypeBean;
import com.byron.keepaccount.constant.Constant;
import com.byron.keepaccount.db.DbManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends BaseRecordFragment{


    @Override
    public void saveAccountBeanToDb() {
        mAccountBean.setKind(Constant.SHOU_RU);
        DbManager.insertAccountBeanToDb(mAccountBean);
    }

    @Override
    public void loadDataToGv() {
        super.loadDataToGv();
        List<TypeBean> listType = DbManager.getTypeList(Constant.SHOU_RU);
        mTypeBeanList.addAll(listType);
        mTypeBaseAdapter.notifyDataSetChanged();
        mTypeTv.setText("其它");
        mTypeIv.setImageResource(R.mipmap.in_qt_fs);

    }
}
