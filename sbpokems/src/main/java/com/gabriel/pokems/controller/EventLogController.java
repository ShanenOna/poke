package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.EventLog;
import com.gabriel.pokems.service.EventLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class EventLogController {
	Logger logger = LoggerFactory.getLogger( EventLogController.class);
	@Autowired
	private EventLogService eventLogService;
	@GetMapping("/api/eventLog")
	public ResponseEntity<?> listEventLog()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			EventLog[] eventLog = eventLogService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(eventLog);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/eventLog")
	public ResponseEntity<?> add(@RequestBody EventLog eventLog){
		logger.info("Input >> " + eventLog.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			EventLog newEventLog = eventLogService.create(eventLog);
			logger.info("created eventLog >> " + newEventLog.toString() );
			response = ResponseEntity.ok(newEventLog);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve eventLog with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/eventLog")
	public ResponseEntity<?> update(@RequestBody EventLog eventLog){
		logger.info("Update Input >> eventLog.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			EventLog newEventLog = eventLogService.update(eventLog);
			response = ResponseEntity.ok(eventLog);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve eventLog with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/eventLog/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input eventLog id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			EventLog eventLog = eventLogService.get(id);
			response = ResponseEntity.ok(eventLog);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/eventLog/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			eventLogService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
