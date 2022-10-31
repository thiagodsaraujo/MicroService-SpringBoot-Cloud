package com.github.thg.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thg.bankapi.entities.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

}
