package com.linokhan.restful_api.service;

import com.linokhan.restful_api.dtos.ProductDTO;
import com.linokhan.restful_api.model.Product;
import com.linokhan.restful_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of the {@link ProductService} interface.
 * This service class handles the business logic for managing products.
 * It interacts with the {@link ProductRepository} to access product data and uses {@link ModelMapper} for DTO mapping.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    /**
     * Constructor for dependency injection.
     *
     * @param productRepository the repository for managing {@link Product} entities.
     * @param modelMapper       the mapper for converting between entities and DTOs.
     */
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        try {
            return productRepository.findAll().stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching products", e);
            throw new RuntimeException("Error fetching products");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO getProductById(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            return modelMapper.map(product, ProductDTO.class);
        } catch (EntityNotFoundException e) {
            logger.warn("Product not found with id: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching product with id: {}", id, e);
            throw new RuntimeException("Error fetching product");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            product = productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        } catch (Exception e) {
            logger.error("Error creating product", e);
            throw new RuntimeException("Error creating product");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            modelMapper.map(productDTO, product);
            product = productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        } catch (EntityNotFoundException e) {
            logger.warn("Product not found with id: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating product with id: {}", id, e);
            throw new RuntimeException("Error updating product");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting product with id: {}", id, e);
            throw new RuntimeException("Error deleting product");
        }
    }






}
