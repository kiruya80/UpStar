package com.ulling.upstar.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentTalkBinding;

/**
 * 메뉴
 * ㄴ 채팅방
 */
public class TalkFragment extends QcBaseLifeFragment {

    private FragmentTalkBinding viewBinding;

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


    public void setSubType(int subType) {
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
        viewBinding = (FragmentTalkBinding) getViewDataBinding();
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}
