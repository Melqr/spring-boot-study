package com.bank.service;

import com.bank.entity.CustomerEntity;
import com.bank.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepo customerRepo;

    @Override
    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public CustomerEntity updateCustomer(Long id, CustomerEntity updatedCustomer) {
        return customerRepo.findById(id).map(existing -> {
            existing.setIcNumber(updatedCustomer.getIcNumber());
            existing.setLastname(updatedCustomer.getLastname());
            existing.setSurname(updatedCustomer.getSurname());
            existing.setDescription(updatedCustomer.getDescription());
            return customerRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
}
