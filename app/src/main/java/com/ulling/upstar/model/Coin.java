package com.ulling.upstar.model;

import com.ulling.lib.core.entities.QcBaseItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by KILHO on 2018. 1. 28..
 * 코인 정보
 * 아이디 / 이름 / 단위 / 이미지
 */
@ToString
@Getter
@Setter
@Data /* 아래 코드 추가 */
@EqualsAndHashCode(callSuper=false)
public class Coin extends QcBaseItem {
    private int coinId;
    private String coinName;
    private String unit;
    private String coinImg;
}
