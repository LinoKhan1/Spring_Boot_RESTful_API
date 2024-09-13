package com.linokhan.restful_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a product in the system.
 * This class maps to the 'product' table in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    /**
     * The unique identifier for the product.
     * Mapped to the 'id' column in the 'product' table.
     * Auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     * Mapped to the 'name' column in the 'product' table.
     */
    private String name;

    /**
     * A short description of the product.
     * Mapped to the 'description' column in the 'product' table.
     */
    private String description;

    /**
     * The price of the product.
     * Mapped to the 'price' column in the 'product' table.
     */
    private double price;
}
