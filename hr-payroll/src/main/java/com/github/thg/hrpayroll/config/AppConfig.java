package com.github.thg.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	// Essa Classe é necessária para realizar a configuração da comunicação
	// Com o RestTemplate vamos comunicar os dois projetos
	// Precisamos criar um bean
	
	
	
	// Esse método serve para registrar uma instancia única, um singleton de um objeto do tipo resttemplate
	// essa instancia unica vai ficar disponivel para eu injetar em outros componentes
	// Basicamente criar um componente a partir de uma chamada de método
	// Implementou o padrão de projeto Singleton(instancia única) para ter a disposição um objeto rest template
	// para injetar em outros serviços
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
