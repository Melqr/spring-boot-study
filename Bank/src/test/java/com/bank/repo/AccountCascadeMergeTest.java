package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;

// @SpringBootTest
// @ActiveProfiles("test") // Uses application-test.properties for H2 setup
class AccountCascadeMergeTest {

	//TODO: SpringBoot: Practical Bonus 4 - Cascade merge
	
	// Child class by default disallow to update parent class
	// You need to have Cascade type Merge
	
	// Enable the unit test
	// Run it and expect error below
	// Add Cascade type - Merge on AccountEntity.java
	
	   @Autowired
	    private IAccountRepo accountRepo;
	    
		//@Test
	    void testSaveCustomerWithAccount() {
	        CustomerEntity customer = new CustomerEntity();
	        customer.setIcNumber("IC123456789");
	        customer.setLastname("Tan");
	        customer.setSurname("Ahmad");
	        customer.setDescription("Test customer with account");
	        customer.setCreationDate(LocalDateTime.now());

	        AccountEntity account = new AccountEntity();
	        account.setAccountNumber("ACC00112233");
	        account.setBalance(5000.0);
	        account.setCreationDate(LocalDateTime.now());
	        account.setCustomerEntity(customer);

	        customer.setAccountEntities(List.of(account));

	        AccountEntity saved = accountRepo.save(account);
	        assertNotNull(saved);
	        
	        AccountEntity found = accountRepo.findById(saved.getAccountID()).get();
	        found.getCustomerEntity().setDescription("Updated");
	        accountRepo.save(found); 
	        found = accountRepo.findById(saved.getAccountID()).get();
	        assertNotNull(found);
	        assertEquals("Updated", found.getCustomerEntity().getDescription()); // expect error here
	    }
}
