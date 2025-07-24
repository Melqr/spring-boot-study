package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.entity.AccountEntity;
import com.bank.repo.IAccountRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepo accountRepo;
    
    @Override
    public AccountEntity createAccount(AccountEntity account) {
        return accountRepo.save(account);
    }

    @Override
    public AccountEntity getAccountById(Long id) {
        return accountRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    @Override
    public List<AccountEntity> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public AccountEntity updateAccount(Long id, AccountEntity updated) {
        AccountEntity existing = getAccountById(id);
        existing.setAccountNumber(updated.getAccountNumber());
        existing.setBalance(updated.getBalance());
        return accountRepo.save(existing);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }
}
