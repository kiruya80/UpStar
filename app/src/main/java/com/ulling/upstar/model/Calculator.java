package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 28..
 * 매수/매도 입력 정보
 * ㄴ 거래단가 거래수량 거래금액 수수료 정산금액
 * 코인 수익률에서 사용
 * ㄴ 수익률 / 평가손익
 */
@ToString
@Getter
@Setter
@Data /* 아래 코드 추가 */
@EqualsAndHashCode(callSuper=false)
public class Calculator extends QcBaseItem {
    // 매도 매수
    private boolean buying;
    // 평균가
    private float average;
    // 코인 수량
    private float coinAmount;
    // 총 금액
    private float totalPrice;
    // 수수료
    private float commission;
    // 수수료 제외 금액
    private float price;
    // 수익률
    private float ratio;
    // 평가손익
    private float gainAndLoss;
    private String date;
}
