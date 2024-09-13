package com.linokhan.restful_api.service;

import com.linokhan.restful_api.dtos.ProductDTO;
import java.util.List;

/**
 * Service interface for managing product-related operations.
 * Provides methods for creating, retrieving, updating, and deleting products.
 */
public interface ProductService {

    /**
     * Retrieves a list of all products.
     *
     * @return a list of {@link ProductDTO} representing all products.
     */
    List<ProductDTO> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link ProductDTO} representing the product.
     */
    ProductDTO getProductById(Long id);

    /**
     * Adds a new product to the system.
     *
     * @param productDTO the data of the product to add.
     * @return a {@link ProductDTO} representing the added product.
     */
    ProductDTO addProduct(ProductDTO product);

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update.
     * @param productDTO the new data for the product.
     * @return a {@link ProductDTO} representing the updated product.
     */
    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     */
    void deleteProduct(Long id);
}
