package com.burgertable.burgertable.dto.sales;

import com.burgertable.burgertable.entity.FeesEntity;
import com.burgertable.burgertable.entity.SalesMonthEntity;
import com.burgertable.burgertable.entity.SalesMonthPureEntity;
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
    private Long tanyoMonth;
    private Long totalMonth;
    private SalesMonthPureEntity salesMonthPure;
    private FeesEntity fees;

}
