package com.burgertable.burgertable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesSaveData {
    //유저테이블 PK
    private Long salesId;
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
    //매출 날짜
    private Timestamp salesDate;
    //작성자
    private Long userNum;
}
