package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;

@SpringBootTest
@ActiveProfiles("test") // Uses application-test.properties for H2 setup
class AccountCascadePersistTest {
	//TODO: SpringBoot: Practical Bonus 3 - Cascade persist
	
		// Child class by default disallow to save parent class
		// You need to create parent first then save child class
		
		// Enable the unit test
		// Run it and expect error below
		// Add Cascade type - Persist on AccountEntity.java
		
	    @Autowired
	    private IAccountRepo accountRepo;
	    
		//@Test
	    void testSaveAccount() {
	        // Step 1: Create Customer
	        CustomerEntity customer = new CustomerEntity();
	        customer.setIcNumber("IC123456789");
	        customer.setLastname("Tan");
	        customer.setSurname("Ahmad");
	        customer.setDescription("Test customer with account");
	        customer.setCreationDate(LocalDateTime.now());

	        // Step 2: Create Account and associate with Customer
	        AccountEntity account = new AccountEntity();
	        account.setAccountNumber("ACC00112233");
	        account.setBalance(5000.0);
	        account.setCreationDate(LocalDateTime.now());
	        account.setCustomerEntity(customer);

	        // Step 3: Add account to customer
	        customer.setAccountEntities(List.of(account));

	        // Step 4: Save customer (cascades account save)
	        AccountEntity saved = accountRepo.save(account); // expect transient instance must be saved before current operation error
	        assertNotNull(saved);
	        

	    }
	}
