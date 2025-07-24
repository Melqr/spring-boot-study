package com.bank.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long customerID;
    private String icNumber;
    private String lastname;
    private String surname;
    private String description;
    private LocalDateTime creationDate;

    private List<AccountDTO> accountDTOs;
}
