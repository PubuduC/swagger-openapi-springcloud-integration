package com.anjispringbootseries.priceservice.service;

import com.anjispringbootseries.priceservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class PriceService {

    @Autowired
    RestTemplate restTemplate;
    public float calculatePrice(long id, int allUnitsNeeded) {
        Product product = restTemplate.getForEntity("http://product-service/api/v1/products/{productId}", Product.class, id).getBody();
        if (Objects.isNull(product)) {
            throw new RuntimeException("Product not found!");
        }
        int requiredCartons = allUnitsNeeded/product.getUnitsPerCarton();
        int requiredSingleUnits = allUnitsNeeded%product.getUnitsPerCarton();

        float cartonPrice;
        if (requiredCartons >= product.getDiscountCartonsUnitsLowerLimit()) {
            cartonPrice = product.getCartonPrice()*(1-product.getCartonDiscount());
        } else {
            cartonPrice = product.getCartonPrice();
        }
        return requiredCartons*cartonPrice + requiredSingleUnits*(cartonPrice/product.getUnitsPerCarton())*product.getProcessingSingleLaborCharge();

    }
}
