package com.anjispringbootseries.productservice.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateDTO {

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    String name;

    @Min(value = 1, message = "Invalid unitsPerCarton: Should be 1 or more than 1")
    private int unitsPerCarton;

    @Min(value = 0, message = "Invalid cartonPrice: Should be 0 or more than 0")
    private int cartonPrice;

    @Min(value = 1, message = "Invalid processingSingleLaborCharge: Should be 1 or more than 1")
    private float processingSingleLaborCharge;

    @Min(value = 0, message = "Invalid discountCartonsUnitsLowerLimit: Should be more than zero")
    private int discountCartonsUnitsLowerLimit;

    @Min(value = 0, message = "Invalid cartonDiscount: Should be zero or more than zero")
    private float cartonDiscount;

}
