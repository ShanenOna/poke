package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.CatchResult;
import com.gabriel.pokems.service.CatchResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CatchResultController {
	Logger logger = LoggerFactory.getLogger( CatchResultController.class);
	@Autowired
	private CatchResultService catchResultService;
	@GetMapping("/api/catchResult")
	public ResponseEntity<?> listCatchResult()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CatchResult[] catchResult = catchResultService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(catchResult);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/catchResult")
	public ResponseEntity<?> add(@RequestBody CatchResult catchResult){
		logger.info("Input >> " + catchResult.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CatchResult newCatchResult = catchResultService.create(catchResult);
			logger.info("created catchResult >> " + newCatchResult.toString() );
			response = ResponseEntity.ok(newCatchResult);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve catchResult with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/catchResult")
	public ResponseEntity<?> update(@RequestBody CatchResult catchResult){
		logger.info("Update Input >> catchResult.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CatchResult newCatchResult = catchResultService.update(catchResult);
			response = ResponseEntity.ok(catchResult);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve catchResult with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/catchResult/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input catchResult id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CatchResult catchResult = catchResultService.get(id);
			response = ResponseEntity.ok(catchResult);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/catchResult/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			catchResultService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
