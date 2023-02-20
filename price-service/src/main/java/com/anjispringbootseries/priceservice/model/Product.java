package com.anjispringbootseries.priceservice.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String name;
    private int unitsPerCarton;
    private int cartonPrice;
    private float processingSingleLaborCharge;
    private int discountCartonsUnitsLowerLimit;
    private float cartonDiscount;
}
