package com.burgertable.burgertable.repository;

import com.burgertable.burgertable.entity.SalesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
    Page<SalesEntity> findAllByIsDeletedFalseOrderBySalesDateDesc(Pageable pageable);

    //region 합계 저장
    //카드 합계
    @Query("SELECT SUM(s.cardSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumCardSalesByMonth(@Param("yearMonth") String yearMonth);

    //현급 합계
    @Query("SELECT SUM(s.cashSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumCashSalesByMonth(@Param("yearMonth") String yearMonth);

    //간편결제 합계
    @Query("SELECT SUM(s.simpleSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumSimpleSalesByMonth(@Param("yearMonth") String yearMonth);

    //배달의민족 합계
    @Query("SELECT SUM(s.baeminSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumBaeminSalesByMonth(@Param("yearMonth") String yearMonth);

    //쿠팡이츠 합계
    @Query("SELECT SUM(s.coupangSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumCoupangSalesByMonth(@Param("yearMonth") String yearMonth);

    //요기요 합계
    @Query("SELECT SUM(s.yogiyoSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumYogiyoSalesByMonth(@Param("yearMonth") String yearMonth);

    //네이버 합계
    @Query("SELECT SUM(s.naverSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumNaverSalesByMonth(@Param("yearMonth") String yearMonth);

    //땡겨요 합계
    @Query("SELECT SUM(s.tanyoSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumTanyoSalesByMonth(@Param("yearMonth") String yearMonth);

    //월 총 매출 합계
    @Query("SELECT SUM(s.totalSales) FROM SalesEntity s " +
            "WHERE s.isDeleted = false AND DATE_FORMAT(s.salesDate, '%Y-%m') = :yearMonth")
    Long sumTotalSalesByMonth(@Param("yearMonth") String yearMonth);
    //endregion

}
