package com.byron.keepaccount.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth() + 1;
                int day = mDatePicker.getDayOfMonth();
                String monthStr = String.valueOf(month);
                if (month<10) monthStr = "0" + month;
                String dayStr = String.valueOf(day);
                if (day<10) dayStr = "0" + day;
                //获取输入的时分
                String hourStr = mHourEt.getText().toString().trim();
                String minuteStr = mMinEt.getText().toString().trim();
                int hour=0, minute=0;
                if (!TextUtils.isEmpty(hourStr)){
                    hour = Integer.parseInt(hourStr);
                    hour = hour%24;
                }
                if (!TextUtils.isEmpty(minuteStr)){
                    minute = Integer.parseInt(minuteStr);
                    minute = minute%60;
                }

                hourStr = String.valueOf(hour);
                minuteStr = String.valueOf(minute);
                if (hour<10) hourStr = "0" + hourStr;
                if (minute<10) minuteStr = "0" + hourStr;
                String timeFormate = year + "年" + monthStr + "月" + dayStr + "日 " + hourStr + ":" + minuteStr;
                if (mEnsureListener != null){
                    mEnsureListener.onEnsure(timeFormate, year, month, day);
                }
                cancel();
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
