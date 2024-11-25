package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class SalesMonthPureEntity {
    @Id
    private String month; //PK: 월별 순수익 (YYYY-MM 형식)

    private Long cardMonthPure;
    private Long simpleMonthPure;
    private Long baeminMonthPure;
    private Long coupangMonthPure;
    private Long yogiyoMonthPure;
    private Long naverMonthPure;
    private Long tanyoMonthPure;
    private Long totalMonthPure;


}
