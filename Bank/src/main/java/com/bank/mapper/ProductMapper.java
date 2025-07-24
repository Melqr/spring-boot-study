package com.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.bank.entity.ProductEntity;
import com.bank.model.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductDTO toDto(ProductEntity entity);

    ProductEntity toEntity(ProductDTO dto);

    List<ProductDTO> toDtoList(List<ProductEntity> entityList);

    List<ProductEntity> toEntityList(List<ProductDTO> dtoList);
    
}
