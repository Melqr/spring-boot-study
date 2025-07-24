package com.bank.service;

import com.bank.entity.ProductEntity;
import java.util.List;

public interface IProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity createProduct(ProductEntity product);
    ProductEntity updateProduct(Long id, ProductEntity updatedProduct);
    void deleteProduct(Long id);
}
