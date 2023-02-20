package com.anjispringbootseries.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDisplayDTO {

    private long id;

    private String name;

    private int unitsPerCarton;

    private int cartonPrice;

    private float processingSingleLaborCharge;

    private int discountCartonsUnitsLowerLimit;

    private float cartonDiscount;
}
