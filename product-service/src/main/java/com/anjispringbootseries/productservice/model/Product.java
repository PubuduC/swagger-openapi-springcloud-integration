package com.anjispringbootseries.productservice.model;

import com.anjispringbootseries.productservice.model.common.Audit;
import com.anjispringbootseries.productservice.model.dto.ProductDisplayDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@Entity(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product extends Audit {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    private int unitsPerCarton;
    private int cartonPrice;
    private float processingSingleLaborCharge;
    private int discountCartonsUnitsLowerLimit;
    private float cartonDiscount;

//    public Product(String name, int unitsPerCarton, int cartonPrice, float processingSingleLaborCharge, int discountCartonsUnitsLowerLimit, float cartonDiscount) {
//        this.name = name;
//        this.unitsPerCarton = unitsPerCarton;
//        this.cartonPrice = cartonPrice;
//        this.processingSingleLaborCharge = processingSingleLaborCharge;
//        this.discountCartonsUnitsLowerLimit = discountCartonsUnitsLowerLimit;
//        this.cartonDiscount = cartonDiscount;
//    }
}

