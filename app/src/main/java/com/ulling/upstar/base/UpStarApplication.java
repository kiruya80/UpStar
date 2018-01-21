package com.ulling.upstar.base;

import android.content.Context;

import com.ulling.lib.core.base.QcBaseApplication;
import com.ulling.lib.core.util.QcPreferences;
import com.ulling.lib.core.util.QcToast;
import com.ulling.upstar.R;

/**
 * Created by KILHO on 2018. 1. 14..
 */

public class UpStarApplication extends QcBaseApplication {
    private static UpStarApplication SINGLE_U;
    public static Context qCon;
//    public static QcPreferences appQcPreferences;

    @Override
    public void onCreate() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
        }
        super.onCreate();
        init();
    }

    /**
     * @MethodName : init
     * @Date : 2015. 3. 15.
     * @author : KILHO
     * @Method ㄴ 초기화
     * @변경이력
     */
    private synchronized void init() {
        SINGLE_U = this;
        qCon = getApplicationContext();
        APP_NAME = qCon.getResources().getString(R.string.app_name);
//        MyVolley.init(this);
        QcPreferences.getInstance().getAPP_NAME();
        QcToast.getInstance().show("Start App : " + QcPreferences.getInstance().getAPP_NAME(), false);
    }

    public static synchronized UpStarApplication getInstance() {
        return SINGLE_U;
    }
//    public static synchronized QcPreferences getQcPrefer() {
//        if (appQcPreferences == null) {
//            appQcPreferences = QcPreferences.getInstance(qCon, APP_NAME);
//        }
//        return appQcPreferences;
//    }
}
