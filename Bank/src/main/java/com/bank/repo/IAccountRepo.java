package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.AccountEntity;

@Repository
public interface IAccountRepo extends JpaRepository<AccountEntity, Long> {

}