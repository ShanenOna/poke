package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.PokeLuck;
import com.gabriel.pokems.service.PokeLuckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PokeLuckController {
	Logger logger = LoggerFactory.getLogger( PokeLuckController.class);
	@Autowired
	private PokeLuckService pokeLuckService;
	@GetMapping("/api/pokeLuck")
	public ResponseEntity<?> listPokeLuck()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			PokeLuck[] pokeLuck = pokeLuckService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(pokeLuck);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/pokeLuck")
	public ResponseEntity<?> add(@RequestBody PokeLuck pokeLuck){
		logger.info("Input >> " + pokeLuck.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			PokeLuck newPokeLuck = pokeLuckService.create(pokeLuck);
			logger.info("created pokeLuck >> " + newPokeLuck.toString() );
			response = ResponseEntity.ok(newPokeLuck);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pokeLuck with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/pokeLuck")
	public ResponseEntity<?> update(@RequestBody PokeLuck pokeLuck){
		logger.info("Update Input >> pokeLuck.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			PokeLuck newPokeLuck = pokeLuckService.update(pokeLuck);
			response = ResponseEntity.ok(pokeLuck);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pokeLuck with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/pokeLuck/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input pokeLuck id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			PokeLuck pokeLuck = pokeLuckService.get(id);
			response = ResponseEntity.ok(pokeLuck);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/pokeLuck/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			pokeLuckService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
