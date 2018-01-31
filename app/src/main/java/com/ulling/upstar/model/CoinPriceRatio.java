package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 28..
 * 코인 메인 수익률 정보
 * ㄴ 코인 , 수익률 평가손익 보유수량 매수평균가
 */
@ToString
@Getter
@Setter
@Data /* 아래 코드 추가 */
@EqualsAndHashCode(callSuper=false)
public class CoinPriceRatio extends QcBaseItem {
    private Coin coin;
    private Calculator averageCalculator;
    private ArrayList<Calculator> calculator;
}
