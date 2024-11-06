package com.burgertable.burgertable.dto;


import com.burgertable.burgertable.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesLogDataDTO {

    //PK
    private Long salesId;
    //카드매출
    private String cardSales;
    //현금매출
    private String cashSales;
    //간편결제 매출
    private String socialSales;
    //총매출
    private String totalSales;
    //매출 날짜
    private Timestamp salesDate;
    //작성자
    private UserEntity user;

}
