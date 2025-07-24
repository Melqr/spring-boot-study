package com.bank.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long accountID;
    private String accountNumber;
    private Double balance;
    private LocalDateTime creationDate;

    private CustomerDTO customerDTO;

    private ProductDTO productDTO;
}
