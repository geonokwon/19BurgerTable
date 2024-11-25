package com.burgertable.burgertable.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesDTO {

    private String month;
    private Long cardFee;
    private Long simpleFee;
    private Long baeminFee;
    private Long coupangFee;
    private Long yogiyoFee;
    private Long naverFee;
    private Long tanyoFee;
    private Long totalFee;


}
