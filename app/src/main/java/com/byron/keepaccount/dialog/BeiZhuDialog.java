package com.byron.keepaccount.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.byron.keepaccount.R;

/**
 * 点击备注自定义dialog
 * @author Byron
 * @date 220323
 */
public class BeiZhuDialog extends Dialog {

    @SuppressLint("ResourceType")
    public BeiZhuDialog(@NonNull Context context, int themeResId) {
        super(context, R.layout.dialog_beizhu);
    }



}
