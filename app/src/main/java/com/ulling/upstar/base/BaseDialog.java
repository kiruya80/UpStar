package com.ulling.upstar.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ulling.upstar.R;


public abstract class BaseDialog extends DialogFragment {
    public String TAG = getClass().getSimpleName();
    protected boolean isDim = true;
    protected boolean isStatusbarTrance = true;
    private Fragment fragment;
    private AlertDialog.Builder builder;
    private ViewDataBinding rootViewBinding;

    protected abstract int needGetLayoutId();
    protected abstract void needUIBinding();
    public String title;

    public ViewDataBinding getViewDataBinding() {
        return rootViewBinding;
    }
    public BaseDialog() {
//        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppDialog);

        setCancelable(true);
    }

//        @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        isDim = false;
//        isStatusbarTrance = false;
//        super.onCreateView(inflater, container, savedInstanceState);
//
//        contentView = inflater.inflate(R.layout.dialog_calculator_coin_add, null, false);
//
//        getDialog().setContentView(contentView);
//        return contentView;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //getting proper access to LayoutInflater is the trick. getLayoutInflater is a                   //Function
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(needGetLayoutId(), null);
        builder = new AlertDialog.Builder(getActivity());
        setContentView(view);
        setTitle(title);
//        builder.setTitle(getActivity().getString(R.string.sysinfo)).setNeutralButton(
//                getActivity().getString(R.string.okay), null);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if (rootViewBinding == null)
            rootViewBinding = DataBindingUtil.inflate(inflater, needGetLayoutId(), null, false);

        needUIBinding();
        return builder.create();
    }


    public void  setTitle(String title) {
        if (builder != null)
        builder.setTitle(title);

    }

    public void setContentView(View view) {
        builder.setView(view);
    }

    public BaseDialog show(AppCompatActivity activity, boolean isDim) {
        this.isDim = isDim;

        if (activity == null)
            return null;

        try {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(TAG);
            if (prev != null) {
                ft.remove(prev);
                ((DialogFragment) prev).dismissAllowingStateLoss();
            }
            ft.addToBackStack(null);

            show(ft, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public BaseDialog show(Activity activity) {
        if (activity == null)
            return null;

        try {
            FragmentTransaction ft = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
            Fragment prev = ((AppCompatActivity) activity).getSupportFragmentManager().findFragmentByTag(TAG);
            if (prev != null) {
                ft.remove(prev);
                ((DialogFragment) prev).dismissAllowingStateLoss();
            }
            ft.addToBackStack(null);

            show(ft, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public void hide() {
        dismissAllowingStateLoss();
    }

}