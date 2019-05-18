package com.example.p1101_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class Dialog2 extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String TAG = "myLogs";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Title!").setPositiveButton(R.string.yes, (DialogInterface.OnClickListener) this)
                .setNegativeButton(R.string.no, (DialogInterface.OnClickListener) this)
                .setNeutralButton(R.string.maybe, (DialogInterface.OnClickListener) this)
                .setMessage(R.string.message_text);
        return adb.create();
        //return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes;
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
        }
        if (i > 0)
            Log.i(TAG, "Dialog 2 : " + getResources().getString(i));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.i(TAG, "Dialog 2 : onDismiss ");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.i(TAG, "Dialog 2 : onCancel ");
    }
}
