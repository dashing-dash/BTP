package com.example.android.btp.activity;

/**
 * Created by Pallav on 11/16/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.android.btp.R;

public class CustomDialog extends Dialog {

    private Activity activity;
    private String title;
    private String message;
    private String subMessage;

    CustomDialog(@NonNull Activity activity, String title, String message,String subMessage) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.message = message;
        this.subMessage=subMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        TextView textViewDialogTitle=findViewById(R.id.tv_dialog_title);
        TextView textViewDialogMessage=findViewById(R.id.tv_dialog_message);
        textViewDialogTitle.setText(title);
        textViewDialogMessage.setText(message);
    }
}
