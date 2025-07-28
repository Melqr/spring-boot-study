package com.bank.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;
import com.bank.entity.ProductEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // application-test.properties with H2
class AccountRepoTest {

    @Autowired
    private IAccountRepo accountRepo;

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private IProductRepo productRepo;

    // Helper to create a dummy customer
    private CustomerEntity createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC0001");
        customer.setLastname("Ali");
        customer.setSurname("Bin");
        customer.setDescription("Test customer");
        customer.setCreationDate(LocalDateTime.now());
        return customerRepo.save(customer);
    }

    // Helper to create a dummy product
    private ProductEntity createProduct() {
        ProductEntity product = new ProductEntity();
        product.setProductName("Test Product");
        product.setDescription("Product Desc");
        return productRepo.save(product);
    }

    @Test
    void testCreateAccount() {
        CustomerEntity customer = createCustomer();
        ProductEntity product = createProduct();

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("9000");
        account.setBalance(20.29);
        account.setCustomerEntity(customer);
        account.setProductEntity(product);
        account.setCreationDate(LocalDateTime.now());

        AccountEntity saved = accountRepo.save(account);

        assertNotNull(saved.getAccountID());
        assertEquals("9000", saved.getAccountNumber());
        assertEquals(20.29, saved.getBalance());
    }

    @Test
    void testFindAccountById() {
        CustomerEntity customer = createCustomer();
        ProductEntity product = createProduct();

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("1234");
        account.setBalance(100.00);
        account.setCustomerEntity(customer);
        account.setProductEntity(product);
        account.setCreationDate(LocalDateTime.now());

        AccountEntity saved = accountRepo.save(account);

        Optional<AccountEntity> found = accountRepo.findById(saved.getAccountID());

        assertTrue(found.isPresent());
        assertEquals("1234", found.get().getAccountNumber());
    }

    @Test
    void testDeleteAccount() {
        CustomerEntity customer = createCustomer();
        ProductEntity product = createProduct();

        AccountEntity account = new AccountEntity();
        account.setAccountNumber("5678");
        account.setBalance(50.00);
        account.setCustomerEntity(customer);
        account.setProductEntity(product);
        account.setCreationDate(LocalDateTime.now());

        AccountEntity saved = accountRepo.save(account);
        Long id = saved.getAccountID();

        accountRepo.deleteById(id);

        Optional<AccountEntity> deleted = accountRepo.findById(id);
        assertFalse(deleted.isPresent());
    }
}
