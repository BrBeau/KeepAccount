package com.byron.keepaccount.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.byron.keepaccount.R;
import com.byron.keepaccount.interf.KeyboardEnsureListener;

/**
 * 自定义软键盘
 * @author Byron
 * @date 211207
 */
public class KeyboardUtil {


    private String TAG = KeyboardUtil.class.getSimpleName();
    //装载自定义键盘的容器
    private KeyboardView mKeyboardView;
    private EditText mEditText;
    //自定义的键盘
    private final Keyboard mKeyboard;

    private KeyboardEnsureListener mEnsureListener;

    public KeyboardUtil(KeyboardView mKeyboardView, EditText mEditText) {
        this.mKeyboardView = mKeyboardView;
        this.mEditText = mEditText;
        //取消弹出系统键盘，用我们自己定义的
        mEditText.setInputType(InputType.TYPE_NULL);
        mKeyboard = new Keyboard(this.mEditText.getContext(), R.xml.keyboard);

        //将键盘装载到keyboard view里
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setEnabled(true);
        //不允许为预览
        mKeyboardView.setPreviewEnabled(false);
        //设置键盘的监听事件
        mKeyboardView.setOnKeyboardActionListener(keyboardActionListener);
    }

    KeyboardView.OnKeyboardActionListener keyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Log.d(TAG, "键盘primaryCode： " + primaryCode);
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            switch (primaryCode){
                //点击了删除按键,删除的是前一个
                case Keyboard.KEYCODE_DELETE:
                    if (editable != null && mEditText.length()>0){
                        if (start>0){
                            editable.delete(start-1, start);
                        }
                    }
                    break;
                //点击了清零
                case Keyboard.KEYCODE_CANCEL:
                    editable.clear();
                    break;
                //点击了完成
                case Keyboard.KEYCODE_DONE:
                    /**
                     * 因为点击了完成后，直接跳回到了主页面，所以只能够设置一个接口来回传
                     */
                    mEnsureListener.onEnsure(); //通过接口回调
                    break;


                 //剩下的这些就是点击了正常的输入，就直接插入editable中
                default:
                    editable.insert(start, Character.toString((char)primaryCode));
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void setEnsureListener(KeyboardEnsureListener keyboardEnsureListener){
        mEnsureListener = keyboardEnsureListener;
    }

    /**
     * 展示键盘
     */
    public void showKeyboard(){
        int vis = mKeyboardView.getVisibility();
        if (vis == View.INVISIBLE || vis == View.GONE){
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard(){
        int vis = mKeyboardView.getVisibility();
        if (vis == View.VISIBLE || vis == View.INVISIBLE){
            mKeyboardView.setVisibility(View.GONE);
        }
    }


}
