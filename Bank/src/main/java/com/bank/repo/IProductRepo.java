package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<ProductEntity, Long>  {
	
}