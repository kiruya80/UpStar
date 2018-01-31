package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 28..
 * 메도 메수 정보
 */
@ToString
@Getter
@Setter
@Data /* 아래 코드 추가 */
@EqualsAndHashCode(callSuper=false)
public class CoinCalculator extends QcBaseItem {
    private Calculator calculator;
    private Coin coin;
}
