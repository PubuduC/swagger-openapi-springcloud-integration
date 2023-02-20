package com.anjispringbootseries.priceservice.controller;

import com.anjispringbootseries.priceservice.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @Operation(summary = "Calculate price by product and units needed", description = "Returns price for given product with amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "Price calculated successfully",
                                    value = "3389.08")})),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "404 Response",
                                    summary = "404 from the service directly",
                                    value =
                                            "{\"timestamp\": \"2023-02-18T01:20:33.0725725\","
                                                    + "\"message\": \"Product not found by id : 1\""
                                                    + "}")}))})
    @GetMapping
    public float priceGenerator(@RequestParam long id, @RequestParam int unitsRequired){
        return priceService.calculatePrice(id, unitsRequired);
    }

}
