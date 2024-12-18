package com.burgertable.burgertable.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class InventoryEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    private BigDecimal totalQuantity;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private BigDecimal lowStock;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean lowStockStatus = true;

    //재고 부족 여부 업데이트
    public void updateLowStockStatus() {
        if (totalQuantity != null && lowStock != null) {
            this.lowStockStatus = totalQuantity.compareTo(lowStock) <= 0;
        }
    }


}
