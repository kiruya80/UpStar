package com.ulling.upstar.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;

public class TalkFragment extends QcBaseLifeFragment {

    public TalkFragment() {
    }

    public static TalkFragment getInstance() {
        TalkFragment fragment = new TalkFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void needInitToOnCreate() {

    }

    @Override
    protected void needResetData() {

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.fragment_talk;
    }

    @Override
    protected void needUIBinding() {

    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}
