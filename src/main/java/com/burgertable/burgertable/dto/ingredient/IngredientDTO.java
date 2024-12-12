package com.burgertable.burgertable.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    private Long id;
    private String name;
    private String unit;
    private String category;
    private Timestamp createDate;

}
