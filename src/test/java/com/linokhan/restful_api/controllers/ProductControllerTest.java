package com.linokhan.restful_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linokhan.restful_api.dtos.ProductDTO;
import com.linokhan.restful_api.model.Product;
import com.linokhan.restful_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        productRepository.deleteAll();
        productRepository.save(new Product("Book 1", "Description 1", 10.0));
        productRepository.save(new Product("Book 2", "Description 2", 20.0));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))  // We expect two products
                .andExpect(jsonPath("$[0].name").value("Book 1"));

    }

    @Test
    void shouldReturnProductById() throws Exception {
        Product product = productRepository.save(new Product("Book 3", "Description 3", 30.0));

        mockMvc.perform(get("/api/products/" + product.getId())  // Use product.getId()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Book 3"));
    }

    @Test
    void shouldCreateNewProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("New Book", "New Description", 15.0);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Book"));
    }

   /* @Test
    void shouldUpdateExistingProduct() throws Exception {
        Product existingProduct = productRepository.save(new Product("Old Book", "Old Description", 40.0));
        ProductDTO updatedProductDTO = new ProductDTO("Updated Book", "Updated Description", 45.0);

        mockMvc.perform(put("/api/products/" + existingProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Book"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.price").value(45.0));  // Ensure price is also updated
    }*/

    @Test
    void shouldDeleteProduct() throws Exception {
        Product product = productRepository.save(new Product("Delete Book", "To Be Deleted", 50.0));

        mockMvc.perform(delete("/api/products/" + product.getId())  // Use product.getId()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
