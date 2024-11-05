package com.burgertable.burgertable.repository;

import com.burgertable.burgertable.entity.SalesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
    Page<SalesEntity> findAllByOrderBySalesDateDesc(Pageable pageable);

}
