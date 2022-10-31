package com.github.thg.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thg.bankapi.entities.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Long>{

}
