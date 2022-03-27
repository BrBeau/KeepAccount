package com.byron.keepaccount.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.byron.keepaccount.R;
import com.byron.keepaccount.interf.TimeDialogEnsureListener;

/**
 * 记一笔选择时间的弹窗
 *
 * @author Byron
 * @date 220327
 */
public class TimeDialog extends Dialog implements View.OnClickListener{

    private TimeDialogEnsureListener mEnsureListener;

    private Button mEnsureBtn, mCancelBtn;
    private EditText mHourEt, mMinEt;
    private DatePicker mDatePicker;

    public TimeDialog(@NonNull Context context) {
        super(context);
    }

    public void setTimeDialogEnsureListener(TimeDialogEnsureListener timeDialogEnsureListener){
        mEnsureListener = timeDialogEnsureListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time);
        mEnsureBtn = findViewById(R.id.dialog_time_btn_ensure);
        mCancelBtn = findViewById(R.id.dialog_time_btn_cancel);
        mHourEt = findViewById(R.id.dialog_time_et_hour);
        mMinEt = findViewById(R.id.dialog_time_et_minute);
        mDatePicker = findViewById(R.id.dialog_time_dp);

        mEnsureBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);

        hideDpHead();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_time_btn_ensure:
                if (mEnsureListener != null){
                    //mEnsureListener.onEnsure();
                }
                break;

            case R.id.dialog_time_btn_cancel:
                cancel();
                break;
        }

    }

    //隐藏DatePicker的头布局
    private void hideDpHead(){
        ViewGroup vg = (ViewGroup) mDatePicker.getChildAt(0);
        if (vg == null) return;
        //获取头布局
        View headView = vg.getChildAt(0);
        if (headView == null) return;


    }
}
