package com.github.thg.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thg.hruser.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	
	User findByEmail(String email);
}
