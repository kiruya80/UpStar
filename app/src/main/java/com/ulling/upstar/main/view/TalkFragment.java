package com.ulling.upstar.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ulling.lib.core.base.QcBaseLifeFragment;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.FragmentTalkBinding;

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
//        setToolbar(viewBinding.toolbar);
//        actionBar.setTitle(getResources().getString(R.string.menu_talk));
////        actionBar.setDisplayShowCustomEnabled(false); //커스터마이징 하기 위해 필요
//        actionBar.setDisplayShowTitleEnabled(true);
////        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
//
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }
}
