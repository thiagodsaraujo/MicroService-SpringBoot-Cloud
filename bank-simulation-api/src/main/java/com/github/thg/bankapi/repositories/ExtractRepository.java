package com.github.thg.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thg.bankapi.entities.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Long>{
	
}
