package com.br.github.thg.hrworker.resources;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.github.thg.hrworker.entities.Worker;
import com.br.github.thg.hrworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
//	@Value("${test.config}")
//	private String testeConfig;
//	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerRepository repository;
	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs(){
//		logger.info("CONFIG: " + testeConfig);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/createWorker")
	public ResponseEntity<Worker> createWorker(@Valid @RequestBody Worker worker){
		final Worker updateWorker = repository.save(worker);
		return ResponseEntity.ok(updateWorker);
		
	}

	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		
//		Teste Hystrix fault tolerance
//		try {
//			Thread.sleep(3000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		logger.info("PORT = " + env.getProperty("local.server.port"));
		
		Worker obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

	
	@PutMapping(value = "/updateWorker/{id}")
	public ResponseEntity<Worker> updateWorkerById(@PathVariable Long id, @RequestBody Worker workerDetails)
	throws Exception {
		Worker worker = repository.findById(id)
				.orElseThrow(() -> new Exception("Worker not found for this id :" + id));
		
		worker.setId(workerDetails.getId());
		worker.setName(workerDetails.getName());
		worker.setDailyIncome(workerDetails.getDailyIncome());
		
		final Worker updateWorker = repository.save(worker);
		
		return ResponseEntity.ok(updateWorker);

	}
	
//	@DeleteMapping(value = "/deleteWorker/{id}")
//	public ResponseEntity<Worker> deleteWorker(@PathVariable(value = "id") Long id) throws Exception {
//		Worker worker = repository.findById(id)
//				.orElseThrow(() -> new Exception("Worker not found for this id :" + id));
//		final Worker deletedWorker = repository.deleteById(id);
//		
//	}
	
	
}
