package com.ulling.upstar.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.listener.OnSingleClickListener;
import com.ulling.lib.core.util.QcLog;
import com.ulling.upstar.R;
import com.ulling.upstar.base.BaseDialog;
import com.ulling.upstar.databinding.DialogCalculatorCoinAddBinding;
import com.ulling.upstar.databinding.FragmentCoinCalculatorBinding;

/**
 * Created by KILHO on 2018. 1. 27..
 */
@SuppressLint("ValidFragment")
public class BuyingDialog extends BaseDialog {

    private View contentView;
    private boolean cancelable;
    private DialogListener listener;
    private DialogCalculatorCoinAddBinding viewBinding;

    public interface DialogListener {
        void onClickOk();

        void onDismiss();
    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.dialog_calculator_coin_add;
    }

    public BuyingDialog(boolean cancelable, DialogListener listener) {
        this.cancelable = cancelable;
        setCancelable(cancelable);
        this.listener = listener;
    }

    public static BaseDialog show(Activity activity, boolean cancelable, DialogListener listener) {
        BuyingDialog dialog = new BuyingDialog(cancelable, listener);
        return dialog.show(activity);
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (DialogCalculatorCoinAddBinding) getViewDataBinding();
        viewBinding.btnConfirm.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                QcLog.e("onSingleClick");
                dismiss();
            }
        });

    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        isDim = false;
//        isStatusbarTrance = false;
//        super.onCreateView(inflater, container, savedInstanceState);
//
//        contentView = inflater.inflate(R.layout.dialog_calculator_coin_add, null, false);
////        contentView.findViewById(R.id.btn_ok).setOnClickListener(btnListener);
//
//        getDialog().setContentView(contentView);
//        return contentView;
//    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        //getting proper access to LayoutInflater is the trick. getLayoutInflater is a                   //Function
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
//        View view = inflater.inflate(R.layout.dialog_calculator_coin_add, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(view);
//        return builder.create();
//    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
        if (listener != null)
            listener.onDismiss();
    }
}
