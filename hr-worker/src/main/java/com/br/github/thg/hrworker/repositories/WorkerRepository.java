package com.br.github.thg.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.github.thg.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
