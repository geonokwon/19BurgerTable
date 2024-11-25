package com.burgertable.burgertable.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesSaveDataDTO {
    //PK
    private Long salesId;
    //카드매출
    private Long cardSales;
    //현금매출
    private Long cashSales;
    //간편결제 매출
    private Long simpleSales;
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
    private String userName;
}
