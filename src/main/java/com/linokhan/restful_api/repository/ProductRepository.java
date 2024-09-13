package com.linokhan.restful_api.repository;

import com.linokhan.restful_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for performing CRUD operations on {@link Product} entities.
 * Extends {@link JpaRepository} to provide JPA-based data access functionality.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
