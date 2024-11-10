package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Builder;
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

    //소셜 총 매출
    private Long socialSales;

    //총매출
    private Long totalSales;

    //삭제여부
    private boolean isDeleted = false;

    //작성자 식별
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private UserEntity user;

}
