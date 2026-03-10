package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.Trigger;
import com.gabriel.pokems.service.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class TriggerController {
	Logger logger = LoggerFactory.getLogger( TriggerController.class);
	@Autowired
	private TriggerService triggerService;
	@GetMapping("/api/trigger")
	public ResponseEntity<?> listTrigger()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Trigger[] trigger = triggerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(trigger);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/trigger")
	public ResponseEntity<?> add(@RequestBody Trigger trigger){
		logger.info("Input >> " + trigger.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Trigger newTrigger = triggerService.create(trigger);
			logger.info("created trigger >> " + newTrigger.toString() );
			response = ResponseEntity.ok(newTrigger);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve trigger with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/trigger")
	public ResponseEntity<?> update(@RequestBody Trigger trigger){
		logger.info("Update Input >> trigger.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Trigger newTrigger = triggerService.update(trigger);
			response = ResponseEntity.ok(trigger);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve trigger with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/trigger/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input trigger id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Trigger trigger = triggerService.get(id);
			response = ResponseEntity.ok(trigger);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/trigger/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			triggerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
