package com.byron.keepaccount.frag_record;

import androidx.fragment.app.Fragment;

import com.byron.keepaccount.R;
import com.byron.keepaccount.bean.TypeBean;
import com.byron.keepaccount.constant.Constant;
import com.byron.keepaccount.db.DbManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 支出的fragment界面
 */
public class OutFragment extends BaseRecordFragment {

    private String Tag = OutFragment.class.getSimpleName();

    @Override
    public void saveAccountBeanToDb() {
        mAccountBean.setKind(Constant.ZHI_CHU);
        DbManager.insertAccountBeanToDb(mAccountBean);

    }

    @Override
    public void loadDataToGv() {
        super.loadDataToGv();
        //获取数据库当中的数据源
        List<TypeBean> listType = DbManager.getTypeList(Constant.ZHI_CHU);
        mTypeBeanList.addAll(listType);
        mTypeBaseAdapter.notifyDataSetChanged();
        mTypeTv.setText("其它");
        mTypeIv.setImageResource(R.mipmap.ic_qita_fs);


    }
}
