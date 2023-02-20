package com.anjispringbootseries.productservice.controller;

import com.anjispringbootseries.productservice.model.Product;
import com.anjispringbootseries.productservice.model.dto.ProductCreateDTO;
import com.anjispringbootseries.productservice.model.dto.ProductDisplayDTO;
import com.anjispringbootseries.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products", description = "Returns all the products in db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 No Products found",
                                    summary = "No Products found",
                                    value = "[]"),
                            @ExampleObject(
                                    name = "200 Products retrieved successfully",
                                    summary = "Products retrieved successfully",
                                    value = "[\n" +
                                            "  {\n" +
                                            "    \"id\": 2,\n" +
                                            "    \"name\": \"Product 1\",\n" +
                                            "    \"unitsPerCarton\": 5,\n" +
                                            "    \"cartonPrice\": 825,\n" +
                                            "    \"processingSingleLaborCharge\": 1.3,\n" +
                                            "    \"discountCartonsUnitsLowerLimit\": 3,\n" +
                                            "    \"cartonDiscount\": 0.1\n" +
                                            "  },\n" +
                                            "  {\n" +
                                            "    \"id\": 1,\n" +
                                            "    \"name\": \"Product 2\",\n" +
                                            "    \"unitsPerCarton\": 20,\n" +
                                            "    \"cartonPrice\": 175,\n" +
                                            "    \"processingSingleLaborCharge\": 1.3,\n" +
                                            "    \"discountCartonsUnitsLowerLimit\": 3,\n" +
                                            "    \"cartonDiscount\": 0.2\n" +
                                            "  }]")}))})
    @GetMapping
    public ResponseEntity<List<ProductDisplayDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "Product retrieved successfully",
                                    value = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"name\": \"Sample Product Name\",\n" +
                                            "  \"unitsPerCarton\": 20,\n" +
                                            "  \"cartonPrice\": 175,\n" +
                                            "  \"processingSingleLaborCharge\": 1.3,\n" +
                                            "  \"discountCartonsUnitsLowerLimit\": 3,\n" +
                                            "  \"cartonDiscount\": 0.2\n" +
                                            "}")})),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "404 Response",
                                    summary = "404 from the service directly",
                                    value =
                                            "{\"timestamp\": \"2023-02-18T01:20:33.0725725\","
                                                    + "\"message\": \"Product not found\""
                                                    + "}")}))})
    @GetMapping("/{id}")
    public ResponseEntity<ProductDisplayDTO> getProductById(@PathVariable long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Create a product", description = "Create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created product", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "200 from the service directly",
                                    value = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"name\": \"Sample Product Name\",\n" +
                                            "  \"unitsPerCarton\": 20,\n" +
                                            "  \"cartonPrice\": 175,\n" +
                                            "  \"processingSingleLaborCharge\": 1.3,\n" +
                                            "  \"discountCartonsUnitsLowerLimit\": 3,\n" +
                                            "  \"cartonDiscount\": 0.2\n" +
                                            "}")})),
            @ApiResponse(responseCode = "400", description = "Bad Request - The product was not created", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "400 Response",
                                    summary = "400 from the service directly",
                                    value = "{\n" +
                                            "  \"errors\": [\n" +
                                            "    \"Invalid processingSingleLaborCharge: Should be 1 or more than 1\",\n" +
                                            "    \"Invalid unitsPerCarton: Should be 1 or more than 1\"\n" +
                                            "  ]\n" +
                                            "}")}))})
    @PostMapping
    public ResponseEntity<ProductDisplayDTO> createProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO){
        return ResponseEntity.ok(productService.createProduct(productCreateDTO));
    }

    @Operation(summary = "Update product by id", description = "Returns the updated product as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved updated product", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "Product updated successfully",
                                    value = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"name\": \"Sample Product Name\",\n" +
                                            "  \"unitsPerCarton\": 20,\n" +
                                            "  \"cartonPrice\": 175,\n" +
                                            "  \"processingSingleLaborCharge\": 1.3,\n" +
                                            "  \"discountCartonsUnitsLowerLimit\": 3,\n" +
                                            "  \"cartonDiscount\": 0.2\n" +
                                            "}")})),
            @ApiResponse(responseCode = "400", description = "Bad Request - The product was not updated", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "400 Response",
                                    summary = "400 from the service directly",
                                    value = "{\n" +
                                                    "  \"errors\": [\n" +
                                                    "    \"Invalid processingSingleLaborCharge: Should be 1 or more than 1\",\n" +
                                                    "    \"Invalid unitsPerCarton: Should be 1 or more than 1\"\n" +
                                                    "  ]\n" +
                                                    "}")}))})
    @PutMapping("/{id}")
    public ResponseEntity<ProductDisplayDTO> updateProduct(@PathVariable long id, @RequestBody @Valid ProductCreateDTO productCreateDTO){
        return ResponseEntity.ok(productService.updateProduct(id, productCreateDTO));
    }

    @Operation(summary = "Delete a product by id", description = "Returns deleted product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "Product deleted successfully",
                                    value = "Product id: 1001 deleted successfully!")})),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "404 Response",
                                    summary = "Product not found",
                                    value = "{\n" +
                                                    "  \"timestamp\": \"2023-02-18T15:36:27.4621382\",\n" +
                                                    "  \"message\": \"Couldn't delete product. Product not found by id : 111\"\n" +
                                                    "}")}))})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product id: " + id + " deleted successfully!");
    }
}

