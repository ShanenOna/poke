package com.gabriel.pokems.service;
import com.gabriel.pokems.model.Pokemon;
public interface PokemonService {
	Pokemon[] getAll() throws Exception;
	Pokemon get(Integer id) throws Exception;
	Pokemon create(Pokemon pokemon) throws Exception;
	Pokemon update(Pokemon pokemon) throws Exception;
	void delete(Integer id) throws Exception;
}
