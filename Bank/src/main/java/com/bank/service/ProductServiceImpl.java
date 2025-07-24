package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.entity.ProductEntity;
import com.bank.repo.IProductRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

	private final IProductRepo productRepo;
	
	//To getAllproducts
    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    //getId product
    @Override
    public ProductEntity getProductById(Long id) {
    	 return productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Proudct not found"));
    }
    
    //create
    @Override
    public ProductEntity createProduct(ProductEntity product) {
        return productRepo.save(product);
    }

    //update
    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        ProductEntity existing = getProductById(id);
        existing.setProductName(updatedProduct.getProductName());
        existing.setDescription(updatedProduct.getDescription());
        return productRepo.save(existing);
        
    }

    //delete
    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
