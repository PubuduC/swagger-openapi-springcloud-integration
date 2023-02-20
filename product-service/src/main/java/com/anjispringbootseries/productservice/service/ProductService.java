package com.anjispringbootseries.productservice.service;

import com.anjispringbootseries.productservice.exception.ProductNotFoundException;
import com.anjispringbootseries.productservice.model.Product;
import com.anjispringbootseries.productservice.model.dto.ProductCreateDTO;
import com.anjispringbootseries.productservice.model.dto.ProductDisplayDTO;
import com.anjispringbootseries.productservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    private ProductRepository productRepository;
    public List<ProductDisplayDTO> getAllProducts(){
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProductDisplayDTO getProductById(long id) {
        return productRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new ProductNotFoundException("Product not found by id : " + id));
    }

    public ProductDisplayDTO createProduct(ProductCreateDTO productCreateDTO) {
//        Product product = new Product(productCreateDTO.getName(), productCreateDTO.getCartonPrice(), productCreateDTO.getUnitsPerCarton(), productCreateDTO.getCartonDiscount(), productCreateDTO.getDiscountCartonsUnitsLowerLimit(), productCreateDTO.getProcessingSingleLaborCharge());

        Product product = Product.builder()
                .name(productCreateDTO.getName())
                .cartonPrice(productCreateDTO.getCartonPrice())
                .unitsPerCarton(productCreateDTO.getUnitsPerCarton())
                .cartonDiscount(productCreateDTO.getCartonDiscount())
                .discountCartonsUnitsLowerLimit(productCreateDTO.getDiscountCartonsUnitsLowerLimit())
                .processingSingleLaborCharge(productCreateDTO.getProcessingSingleLaborCharge())
                .build();
        return this.convertToDto(productRepository.save(product));
    }

    public void  deleteProduct(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Couldn't delete product. Product not found by id : " + id);
        }
    }

    public ProductDisplayDTO updateProduct(long id, ProductCreateDTO productCreateDTO) {
        Optional<Product> product = productRepository.findById(id);
        Product updatedProduct;
        if(product.isPresent()) {
            updatedProduct = product.get();
            updatedProduct.setName(productCreateDTO.getName());
            updatedProduct.setCartonPrice(productCreateDTO.getCartonPrice());
            updatedProduct.setUnitsPerCarton(productCreateDTO.getUnitsPerCarton());
            updatedProduct.setCartonDiscount(productCreateDTO.getCartonDiscount());
            updatedProduct.setDiscountCartonsUnitsLowerLimit(productCreateDTO.getDiscountCartonsUnitsLowerLimit());
            updatedProduct.setProcessingSingleLaborCharge(productCreateDTO.getProcessingSingleLaborCharge());
        } else {
            updatedProduct = Product.builder()
                    .name(productCreateDTO.getName())
                    .cartonPrice(productCreateDTO.getCartonPrice())
                    .unitsPerCarton(productCreateDTO.getUnitsPerCarton())
                    .cartonDiscount(productCreateDTO.getCartonDiscount())
                    .discountCartonsUnitsLowerLimit(productCreateDTO.getDiscountCartonsUnitsLowerLimit())
                    .processingSingleLaborCharge(productCreateDTO.getProcessingSingleLaborCharge())
                    .build();
        }

        return this.convertToDto(productRepository.save(updatedProduct));
    }

    private ProductDisplayDTO convertToDto(Product product) {
        final ProductDisplayDTO productDisplayDTO = modelMapper().map(product, ProductDisplayDTO.class);
        return productDisplayDTO;
    }
}

