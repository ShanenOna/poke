package com.gabriel.pokems.controller;
import com.gabriel.pokems.model.Pokemon;
import com.gabriel.pokems.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PokemonController {
	Logger logger = LoggerFactory.getLogger( PokemonController.class);
	@Autowired
	private PokemonService pokemonService;
	@GetMapping("/api/pokemon")
	public ResponseEntity<?> listPokemon()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pokemon[] pokemon = pokemonService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(pokemon);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/pokemon")
	public ResponseEntity<?> add(@RequestBody Pokemon pokemon){
		logger.info("Input >> " + pokemon.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pokemon newPokemon = pokemonService.create(pokemon);
			logger.info("created pokemon >> " + newPokemon.toString() );
			response = ResponseEntity.ok(newPokemon);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pokemon with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/pokemon")
	public ResponseEntity<?> update(@RequestBody Pokemon pokemon){
		logger.info("Update Input >> pokemon.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pokemon newPokemon = pokemonService.update(pokemon);
			response = ResponseEntity.ok(pokemon);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pokemon with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/pokemon/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input pokemon id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pokemon pokemon = pokemonService.get(id);
			response = ResponseEntity.ok(pokemon);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/pokemon/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			pokemonService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
