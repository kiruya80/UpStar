package com.ulling.lib.core.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by KILHO on 2018. 1. 14..
 */

public class QcTextUtils {

    public static class NumberTextWatcher implements TextWatcher {

        private   OnTextWatcherListener onTextWatcherListener;
        private DecimalFormat df;
        private DecimalFormat dfnd;
        private boolean hasFractionalPart;

        private EditText et;

        public interface OnTextWatcherListener {
            void onAfterTextChanged(String numberStr);
        }

        public NumberTextWatcher(EditText et) {
            df = new DecimalFormat("#,###.########");
            df.setDecimalSeparatorAlwaysShown(true);
            dfnd = new DecimalFormat("#,###");
            this.et = et;
            hasFractionalPart = false;
        }


        public NumberTextWatcher(EditText et, OnTextWatcherListener onTextWatcherListener) {
            df = new DecimalFormat("#,###.########");
            df.setDecimalSeparatorAlwaysShown(true);
            dfnd = new DecimalFormat("#,###");
            this.et = et;
            hasFractionalPart = false;
            this.onTextWatcherListener = onTextWatcherListener;
        }

        @Override
        public void afterTextChanged(Editable s) {
            et.removeTextChangedListener(this);

            try {
                int inilen, endlen;
                inilen = et.getText().length();

                String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
                Number number = df.parse(v);
                int cp = et.getSelectionStart();
                if (hasFractionalPart) {
                    et.setText(df.format(number));
                } else {
                    et.setText(dfnd.format(number));
                }
                endlen = et.getText().length();
                int sel = (cp + (endlen - inilen));
                if (sel > 0 && sel <= et.getText().length()) {
                    et.setSelection(sel);
                } else {
                    // place cursor at the end?
                    et.setSelection(et.getText().length() - 1);
                }
            } catch (NumberFormatException nfe) {
                // do nothing?
            } catch (ParseException e) {
                // do nothing?
            }

            if (onTextWatcherListener != null) {
                onTextWatcherListener.onAfterTextChanged(et.getText().toString().replaceAll(",", ""));
            }
            et.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()))) {
                hasFractionalPart = true;
            } else {
                hasFractionalPart = false;
            }
        }

    }

}
