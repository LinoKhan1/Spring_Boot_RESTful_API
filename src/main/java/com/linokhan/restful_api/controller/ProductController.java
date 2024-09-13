package com.linokhan.restful_api.controller;

import com.linokhan.restful_api.dtos.ProductDTO;
import com.linokhan.restful_api.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing products.
 * Provides endpoints to create, read, update, and delete products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Constructor that initializes the {@link ProductService}.
     *
     * @param productService service layer for handling product operations.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    /**
     * Fetches all products from the database.
     *
     * @return a {@link ResponseEntity} containing a list of {@link ProductDTO}.
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Fetching all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }
    /**
     * Fetches a specific product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link ResponseEntity} containing the {@link ProductDTO}, or a 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            logger.info("Fetching product with id: {}", id);
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (EntityNotFoundException e) {
            logger.warn("Product not found: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    /**
     * Creates a new product.
     *
     * @param productDTO the product data to create.
     * @return a {@link ResponseEntity} containing the created {@link ProductDTO} and a 201 status.
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            logger.info("Creating product");
            return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * Updates an existing product.
     *
     * @param id         the ID of the product to update.
     * @param productDTO the product data to update.
     * @return a {@link ResponseEntity} containing the updated {@link ProductDTO}, or a 404 if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            logger.info("Updating product with id: {}", id);
            return ResponseEntity.ok(productService.updateProduct(id, productDTO));
        } catch (EntityNotFoundException e) {
            logger.warn("Product not found: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error updating product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @return a {@link ResponseEntity} with no content, or a 500 status if an error occurs.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            logger.info("Deleting product with id: {}", id);
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
