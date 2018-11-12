package com.wdq.micorestore.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wdq.micorestore.R;

/**
 * Created by sinosoft_wan on 2018/11/12.
 */

public class MsgDialog extends Dialog {
    TextView positiveButton,negativeButton;
    public MsgDialog(@NonNull Context context) {
        super(context, R.style.MsgDialog);
    }

    private void setMsgDialog() {
        View mView = null;//LayoutInflater.from(getContext()).inflate(R.layout.dialog_msg, null);
//        TextView title = (TextView) mView.findViewById(R.id.title);
//        positiveButton = (TextView) mView.findViewById(R.id.positiveButton);
//        negativeButton = (TextView) mView.findViewById(R.id.negativeButton);
//        if(positiveButton!=null) positiveButton.setOnClickListener(listener);
//        if(negativeButton!=null) negativeButton.setOnClickListener(listener);
        super.setContentView(mView);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MsgDialog.this.dismiss();
        }
    };
    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        /////////获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);;
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        /////////设置高宽
        lp.width = (int) (screenWidth * 0.75); // 宽度
        lp.height = (int) (lp.width*0.65);     // 高度
        dialogWindow.setAttributes(lp);

    }
}
