package com.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.entity.AccountEntity;
import com.bank.model.AccountDTO;

@Mapper
public interface AccountMapper {

	@Mapping(source = "customerEntity", target = "customerDTO")
	@Mapping(source = "productEntity", target = "productDTO")
	AccountDTO toDto(AccountEntity entity);

	@Mapping(source = "customerDTO", target = "customerEntity")
	@Mapping(source = "productDTO", target = "productEntity")
	AccountEntity toEntity(AccountDTO dto);
	
    List<AccountDTO> toDtoList(List<AccountEntity> entityList);

    List<AccountEntity> toEntityList(List<AccountDTO> dtoList);
}
