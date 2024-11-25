package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class SalesMonthEntity {
    @Id
    private String month;

    private Long cardMonth;

    private Long cashMonth;

    private Long simpleMonth;

    private Long baeminMonth;

    private Long coupangMonth;

    private Long yogiyoMonth;

    private Long naverMonth;

    private Long tanyoMonth;

    private Long totalMonth;

    //순수익 테이블 참조
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "month") // FK
    private SalesMonthPureEntity salesMonthPure;

    //수수료 테이블 참조
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "month") // FK
    private FeesEntity fees;

}
