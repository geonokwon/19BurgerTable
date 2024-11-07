package com.burgertable.burgertable.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesMonthDataDTO {

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
