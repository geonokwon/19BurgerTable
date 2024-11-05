package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //유저테이블 PK
    private Long salesId;

    //매출 날짜
    private Timestamp salesDate;

    //수정일
    @UpdateTimestamp //엔터티가 수정(UPDATE) 될 때마다 현재 시간으로 갱신
    private Timestamp updateDate;

    //카드매출
    private Long card_sales;

    //현금매출
    private Long cash_sales;

    //배달의민족 매출
    private Long baeminSales;

    //쿠팡이츠 매출
    private Long coupangSales;

    //요기요 매출
    private Long yogiyoSales;

    //네이버 매출
    private Long naverSales;

    //땡겨요 매출
    private Long tanyoSales;

    //총매출
    private Long totalSales;




}
