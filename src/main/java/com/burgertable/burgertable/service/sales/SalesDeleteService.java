package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.repository.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesDeleteService {
    private final SalesRepository salesRepository;
    private final SalesSaveService salesSaveService;

    public boolean salesDateDelete(Long pageNum) {
        SalesEntity salesEntity = salesRepository.findById(pageNum).orElse(null);
        if (salesEntity != null) {
            salesEntity.setDeleted(true);
            salesRepository.save(salesEntity);
            salesSaveService.updateMonthlySummary(salesEntity.getSalesDate());
            return true;
        }
        return false;
    }
}
