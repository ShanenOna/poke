package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.CoinFlip;
import com.gabriel.pokems.service.CoinFlipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CoinFlipController {
	Logger logger = LoggerFactory.getLogger( CoinFlipController.class);
	@Autowired
	private CoinFlipService coinFlipService;
	@GetMapping("/api/coinFlip")
	public ResponseEntity<?> listCoinFlip()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CoinFlip[] coinFlip = coinFlipService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(coinFlip);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/coinFlip")
	public ResponseEntity<?> add(@RequestBody CoinFlip coinFlip){
		logger.info("Input >> " + coinFlip.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CoinFlip newCoinFlip = coinFlipService.create(coinFlip);
			logger.info("created coinFlip >> " + newCoinFlip.toString() );
			response = ResponseEntity.ok(newCoinFlip);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve coinFlip with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/coinFlip")
	public ResponseEntity<?> update(@RequestBody CoinFlip coinFlip){
		logger.info("Update Input >> coinFlip.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CoinFlip newCoinFlip = coinFlipService.update(coinFlip);
			response = ResponseEntity.ok(coinFlip);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve coinFlip with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/coinFlip/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input coinFlip id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			CoinFlip coinFlip = coinFlipService.get(id);
			response = ResponseEntity.ok(coinFlip);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/coinFlip/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			coinFlipService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
