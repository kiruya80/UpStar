package com.ulling.upstar.manager;

/**
 * Created by KILHO on 2018. 1. 28..
 *
 * 코인 계산
 * 평균가 / 갯수 / 손익 / 수익률
 */
public class CoinCalculatorManager {
    private static CoinCalculatorManager instance = null;

    public static CoinCalculatorManager getInstance() {
        if (instance == null) {
            instance = new CoinCalculatorManager();
        }
        return instance;
    }

}
