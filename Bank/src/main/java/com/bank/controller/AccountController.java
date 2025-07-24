package com.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.AccountEntity;
import com.bank.mapper.AccountMapper;
import com.bank.model.AccountDTO;
import com.bank.service.IAccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts/v1")
public class AccountController {

    private final IAccountService accountService;

    private final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO accountDto) {
        var savedEntity = accountService.createAccount(accountMapper.toEntity(accountDto));
        return ResponseEntity.ok(accountMapper.toDto(savedEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
    	AccountEntity accountEntity = accountService.getAccountById(id);
        return ResponseEntity.ok(accountMapper.toDto(accountEntity));
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAll() {
        var entities = accountService.getAllAccounts();
        return ResponseEntity.ok(accountMapper.toDtoList(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @RequestBody AccountDTO accountDto) {
        var updatedEntity = accountService.updateAccount(id, accountMapper.toEntity(accountDto));
        return ResponseEntity.ok(accountMapper.toDto(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
