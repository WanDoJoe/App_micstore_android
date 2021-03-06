package com.wdq.micorestore.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdq.micorestore.R;

/**
 * Created by sinosoft_wan on 2018/11/12.
 */

public class PopupDialogMedia extends Dialog {
    Context context;
    TextView text_cancel,text_camera,text_photo;
    boolean ismatchparent_height=false;
    boolean ismatchparent_width=false;
    boolean isCancel=false;

    public PopupDialogMedia(Context context){
        super(context, R.style.PopupDialog);
        this.context = context;
        setMsgDialog();
    }

    public PopupDialogMedia(Context context,View view){
        super(context, R.style.PopupDialog);
        this.context = context;
        setMsgDialog(view);
    }

    public void setMsgDialog(View v){
        Window win = this.getWindow();
        this.setCanceledOnTouchOutside(isCancel);
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        if(ismatchparent_height) {
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  //高度自适应
        }else{
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        lp.verticalMargin=1;
        win.setAttributes(lp);

        super.setContentView(v);
    }
    private void setMsgDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_identy_photo, null);
        text_cancel = (TextView) mView.findViewById(R.id.text_pop_identy_cancel);
        text_photo = (TextView) mView.findViewById(R.id.text_pop_identy_photo);
        text_camera = (TextView) mView.findViewById(R.id.text_pop_identy_camera);
        text_cancel.setOnClickListener(listener);
        text_photo.setOnClickListener(listener);
        text_camera.setOnClickListener(listener);


        Window win = this.getWindow();
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 50);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  //高度自适应
        win.setAttributes(lp);

        super.setContentView(mView);
    }
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==text_cancel){
                dismiss();
            }else if(v==text_photo){
                Toast.makeText(context,"text_photo",Toast.LENGTH_SHORT).show();
            }else if(v==text_camera){
                Toast.makeText(context, "text_camera", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public  void CanceledOnTouchOutside(boolean isCancel){
                this.isCancel=isCancel;     //点击外部关闭窗口
    }
    public void setmatchparent_height(boolean is){
                this.ismatchparent_height=is;
    }
    public void ismatchparent_width(boolean is){
        this.ismatchparent_width=is;
    }
}
