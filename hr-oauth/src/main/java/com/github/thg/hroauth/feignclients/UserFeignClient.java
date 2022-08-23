package com.github.thg.hroauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.thg.hroauth.entities.User;

@Component // componente gerenciado pelo springboot
@FeignClient(name = "hr-user", path = "/users") // nome do serviço que vai se comunicar e o path
public interface UserFeignClient {

	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);
}
