package com.bank.service;

import com.bank.entity.CustomerEntity;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    CustomerEntity createCustomer(CustomerEntity customer);
    Optional<CustomerEntity> getCustomerById(Long id);
    List<CustomerEntity> getAllCustomers();
    CustomerEntity updateCustomer(Long id, CustomerEntity updatedCustomer);
    void deleteCustomer(Long id);
}
