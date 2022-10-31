package com.github.thg.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.thg.bankapi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
