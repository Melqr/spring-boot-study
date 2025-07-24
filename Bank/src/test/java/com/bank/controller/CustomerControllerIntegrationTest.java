package com.bank.controller;

import com.bank.entity.CustomerEntity;
import com.bank.repo.ICustomerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICustomerRepo customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerEntity testCustomer;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();

        testCustomer = new CustomerEntity();
        testCustomer.setIcNumber("IC123456");
        testCustomer.setLastname("Lee");
        testCustomer.setSurname("Min");
        testCustomer.setDescription("Test customer");
        testCustomer.setCreationDate(LocalDateTime.now());

        testCustomer = customerRepository.save(testCustomer);
    }

    @Test
    void testGetCustomerById_success() throws Exception {
        mockMvc.perform(get("/api/customers/v1/{id}", testCustomer.getCustomerID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.icNumber").value("IC123456"));
    }

    @Test
    void testGetCustomerById_notFound() throws Exception {
        mockMvc.perform(get("/api/customers/v1/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setIcNumber("IC654321");
        newCustomer.setLastname("Tan");
        newCustomer.setSurname("Ahmad");
        newCustomer.setDescription("New customer");
        newCustomer.setCreationDate(LocalDateTime.now());

        mockMvc.perform(post("/api/customers/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.icNumber").value("IC654321"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        testCustomer.setSurname("UpdatedSurname");

        mockMvc.perform(put("/api/customers/v1/{id}", testCustomer.getCustomerID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("UpdatedSurname"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/v1/{id}", testCustomer.getCustomerID()))
                .andExpect(status().isNoContent());
    }
}
