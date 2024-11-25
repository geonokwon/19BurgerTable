package com.burgertable.burgertable.dto.sales;

import lombok.*;

@Data
@Builder
@Getter
@Setter
public class SalesMonthPureDTO {

    private String month;
    private Long baeminMonthPure;
    private Long cardMonthPure;
    private Long coupangMonthPure;
    private Long naverMonthPure;
    private Long simpleMonthPure;
    private Long tanyoMonthPure;
    private Long yogiyoMonthPure;
    private Long totalMonthPure;

}
