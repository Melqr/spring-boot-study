package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;
import com.bank.model.CustomerDTO;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;
    
	@Test
    void testEntityToDtoAndBack() {
        // Create sample AccountEntity
        AccountEntity account = new AccountEntity();
        account.setAccountID(1L);
        account.setAccountNumber("ACC123");
        account.setBalance(1000.0);

        // Create sample CustomerEntity
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerID(10L);
        entity.setIcNumber("IC999");
        entity.setLastname("Tan");
        entity.setSurname("Ahmad");
        entity.setDescription("Sample description");
        entity.setCreationDate(LocalDateTime.now());
        entity.setAccountEntities(List.of(account));


        // Convert to DTO
        CustomerDTO dto = customerMapper.toDto(entity);

    	assertNotNull(dto);
    	assertNotNull(dto.getAccountDTOs());
    	assertNotNull(dto.getAccountDTOs().get(0));
    }
}
