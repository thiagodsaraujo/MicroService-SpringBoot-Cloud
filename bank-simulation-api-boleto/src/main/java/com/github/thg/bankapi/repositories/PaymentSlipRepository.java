package com.github.thg.bankapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thg.bankapi.entities.PaymentSlip;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {

}
