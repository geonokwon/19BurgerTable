package com.burgertable.burgertable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;


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

    private Long tangyoMonth;

    private Long totalMonth;

}
