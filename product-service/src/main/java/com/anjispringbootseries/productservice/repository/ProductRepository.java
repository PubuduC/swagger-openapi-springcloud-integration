package com.anjispringbootseries.productservice.repository;

import com.anjispringbootseries.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ProductRepository extends JpaRepository<Product, Long> {
}
