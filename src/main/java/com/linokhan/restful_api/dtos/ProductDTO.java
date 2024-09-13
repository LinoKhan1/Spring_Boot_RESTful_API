package com.linokhan.restful_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Product entities.
 * This class is used to encapsulate and transfer product data between layers.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A short description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    private double price;
}
