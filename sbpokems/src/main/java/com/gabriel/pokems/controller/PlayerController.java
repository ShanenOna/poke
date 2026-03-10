package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.Player;
import com.gabriel.pokems.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PlayerController {
	Logger logger = LoggerFactory.getLogger( PlayerController.class);
	@Autowired
	private PlayerService playerService;
	@GetMapping("/api/player")
	public ResponseEntity<?> listPlayer()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Player[] player = playerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(player);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/player")
	public ResponseEntity<?> add(@RequestBody Player player){
		logger.info("Input >> " + player.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Player newPlayer = playerService.create(player);
			logger.info("created player >> " + newPlayer.toString() );
			response = ResponseEntity.ok(newPlayer);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve player with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/player")
	public ResponseEntity<?> update(@RequestBody Player player){
		logger.info("Update Input >> player.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Player newPlayer = playerService.update(player);
			response = ResponseEntity.ok(player);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve player with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/player/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input player id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Player player = playerService.get(id);
			response = ResponseEntity.ok(player);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/player/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			playerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
