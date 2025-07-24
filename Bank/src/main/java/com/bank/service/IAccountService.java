package com.bank.service;

import java.util.List;

import com.bank.entity.AccountEntity;

public interface IAccountService {
    AccountEntity createAccount(AccountEntity account);
    AccountEntity getAccountById(Long id);
    List<AccountEntity> getAllAccounts();
    AccountEntity updateAccount(Long id, AccountEntity updated);
    void deleteAccount(Long id);
}
