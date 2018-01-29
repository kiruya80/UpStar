package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import lombok.Data;
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
@Data
public class CoinCalculator extends QcBaseItem {
    private Coin coin;
    private Calculator calculator;
}
