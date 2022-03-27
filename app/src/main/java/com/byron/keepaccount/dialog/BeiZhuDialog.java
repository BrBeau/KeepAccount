package com.byron.keepaccount.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.byron.keepaccount.R;
import com.byron.keepaccount.interf.BeiZhuDialogEnsureListener;

/**
 * 点击备注自定义dialog
 * @author Byron
 * @date 220323
 */
public class BeiZhuDialog extends Dialog implements View.OnClickListener{

    private EditText mEt;
    private Button mConfirmBtn, mCancelBtn;

    public BeiZhuDialogEnsureListener mBeiZhuEnsureListener;

    public BeiZhuDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_beizhu);
        mEt = findViewById(R.id.dialog_beizhu_et);
        mConfirmBtn = findViewById(R.id.dialog_beizhu_btn_ensure);
        mCancelBtn = findViewById(R.id.dialog_beizhu_btn_cancel);

        mConfirmBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_beizhu_btn_ensure:
                if (mBeiZhuEnsureListener != null){
                    mBeiZhuEnsureListener.onEnsure();
                }
                break;

            case R.id.dialog_beizhu_btn_cancel:
                cancel();
                break;
        }

    }

    public void setEnsureListener(BeiZhuDialogEnsureListener beiZhuDialogEnsureListener){
        mBeiZhuEnsureListener = beiZhuDialogEnsureListener;
    }

    /**
     * 设置弹出的dialog的尺寸和屏幕的尺寸一致
     */
    public void setDialogSize(){
        Window window = getWindow();
        //获取窗口的属性
        WindowManager.LayoutParams wlp = window.getAttributes();
        //获取屏幕宽度
        Display display = window.getWindowManager().getDefaultDisplay();
        wlp.width = display.getWidth();
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        //todo handler的用法待加强
        handler.sendEmptyMessageDelayed(1,100);


    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
            //自动弹出软键盘
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    };

    //获取EditText的内容
    public String getBeiZhuText(){
        return mEt.getText().toString().trim();
    }
}
