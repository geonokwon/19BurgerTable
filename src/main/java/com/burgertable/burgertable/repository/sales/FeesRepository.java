package com.burgertable.burgertable.repository.sales;


import com.burgertable.burgertable.entity.FeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeesRepository extends JpaRepository<FeesEntity, String> {
    FeesEntity findByMonth(String month);
}
