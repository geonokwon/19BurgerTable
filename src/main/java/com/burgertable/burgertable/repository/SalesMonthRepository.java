package com.burgertable.burgertable.repository;

import com.burgertable.burgertable.entity.SalesMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesMonthRepository extends JpaRepository<SalesMonthEntity, String> {
    SalesMonthEntity findByMonth(String month);

}
