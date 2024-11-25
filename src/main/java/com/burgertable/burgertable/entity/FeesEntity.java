package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
public class FeesEntity {
    @Id
    private String month;

    //카드 수수료
    private Long cardFee;

    //간편결제 수수료
    private Long simpleFee;

    //배달의민족 수수료
    private Long baeminFee;

    //쿠팡이츠 수수료
    private Long coupangFee;

    //요기요 수수료
    private Long yogiyoFee;

    //네이버 수수료
    private Long naverFee;

    //땡겨요 수수료
    private Long tanyoFee;

    //총 수수료
    private Long totalFee;

    @CreationTimestamp//생성시 현재 시간으로 설정
    private Timestamp createDate;

    @UpdateTimestamp //엔터티가 수정(UPDATE) 될 때마다 현재 시간으로 갱신
    private Timestamp updateDate;

}
