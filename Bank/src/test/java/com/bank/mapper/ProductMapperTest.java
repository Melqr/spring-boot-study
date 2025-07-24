package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.ProductEntity;
import com.bank.model.ProductDTO;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;
    
	@Test
    void testEntityToDtoAndBack() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductID(1l);
        // Convert to DTO
        ProductDTO dto = productMapper.toDto(productEntity);

    	assertNotNull(dto);
    	assertNotNull(dto.getProductID());
    }
}
