package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.CustomerEntity;

@SpringBootTest
@ActiveProfiles("test") // Uses application-test.properties for H2 setup

class CustomerRepoTest {

    @Autowired
    private ICustomerRepo customerRepo;

    @Test
    @Order(1)
    void testCreateCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC123456");
        customer.setLastname("Doe");
        customer.setSurname("John");
        customer.setDescription("Test customer");
        customer.setCreationDate(LocalDateTime.now());

        CustomerEntity saved = customerRepo.save(customer);

        assertNotNull(saved.getCustomerID());
		assertNotNull("Doe", saved.getLastname());
    }

    @Test
    @Order(2)
    void testFindCustomerById() {
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC789012");
        customer.setLastname("Smith");
        customer.setSurname("Anna");
        customer.setDescription("Lookup test");
        customer.setCreationDate(LocalDateTime.now());

        CustomerEntity saved = customerRepo.save(customer);

        Optional<CustomerEntity> found = customerRepo.findById(saved.getCustomerID());
        
        assertTrue(found.isPresent());
		assertNotNull("Smith", found.get().getLastname());
    }

    @Test
    @Order(3)
    void testDeleteCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC999999");
        customer.setLastname("Chan");
        customer.setSurname("Kai");
        customer.setDescription("Delete test");
        customer.setCreationDate(LocalDateTime.now());

        CustomerEntity saved = customerRepo.save(customer);
        Long id = saved.getCustomerID();

        customerRepo.deleteById(id);

        Optional<CustomerEntity> deleted = customerRepo.findById(id);
        assertFalse(deleted.isPresent());
    }
}
