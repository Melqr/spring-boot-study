package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.CustomerEntity;

@Repository
public interface ICustomerRepo extends JpaRepository<CustomerEntity, Long> {
}
