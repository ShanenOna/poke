package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.PokemonData;
import com.gabriel.pokems.model.Pokemon;
import com.gabriel.pokems.repository.PokemonDataRepository;
import com.gabriel.pokems.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PokemonServiceImpl implements PokemonService {
	Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);
	@Autowired
	PokemonDataRepository pokemonDataRepository;
	@Autowired
	@Override
	public Pokemon[] getAll() {
		List<PokemonData> pokemonsData = new ArrayList<>();
		List<Pokemon> pokemons = new ArrayList<>();
		pokemonDataRepository.findAll().forEach(pokemonsData::add);
		Iterator<PokemonData> it = pokemonsData.iterator();
		while(it.hasNext()) {
			PokemonData pokemonData = it.next();
			Pokemon pokemon = new Pokemon();
			pokemon.setId(pokemonData.getId());
			pokemon.setName(pokemonData.getName());
			pokemons.add(pokemon);
		}
		Pokemon[] array = new Pokemon[pokemons.size()];
		for  (int i=0; i<pokemons.size(); i++){
			array[i] = pokemons.get(i);
		}
		return array;
	}
	@Override
	public Pokemon create(Pokemon pokemon) {
		logger.info(" add:Input " + pokemon.toString());
		PokemonData pokemonData = new PokemonData();
		pokemonData.setName(pokemon.getName());
		pokemonData = pokemonDataRepository.save(pokemonData);
		logger.info(" add:Input " + pokemonData.toString());
			Pokemon newPokemon = new Pokemon();
			newPokemon.setId(pokemonData.getId());
			newPokemon.setName(pokemonData.getName());
		return newPokemon;
	}

	@Override
	public Pokemon update(Pokemon pokemon) {
		Pokemon updatedPokemon = null;
		int id = pokemon.getId();
		Optional<PokemonData> optional  = pokemonDataRepository.findById(pokemon.getId());
		if(optional.isPresent()){
			PokemonData originalPokemonData = new PokemonData();
			originalPokemonData.setId(pokemon.getId());
			originalPokemonData.setName(pokemon.getName());
			originalPokemonData.setCreated(optional.get().getCreated());
			PokemonData pokemonData = pokemonDataRepository.save(originalPokemonData);
			updatedPokemon = new Pokemon();
			updatedPokemon.setId(pokemonData.getId());
			updatedPokemon.setName(pokemonData.getName());
			updatedPokemon.setCreated(pokemonData.getCreated());
			updatedPokemon.setLastUpdated(pokemonData.getLastUpdated());
		}
		else {
			logger.error("Pokemon record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedPokemon;
	}

	@Override
	public Pokemon get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Pokemon pokemon = null;
		Optional<PokemonData> optional = pokemonDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			pokemon = new Pokemon();
			pokemon.setId(optional.get().getId());
			pokemon.setName(optional.get().getName());
			pokemon.setCreated(optional.get().getCreated());
			pokemon.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return pokemon;
	}
	@Override
	public void delete(Integer id) {
		Pokemon pokemon = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<PokemonData> optional = pokemonDataRepository.findById(id);
		if( optional.isPresent()) {
			PokemonData pokemonDatum = optional.get();
			pokemonDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Pokemon record with id: " + Integer.toString(id));
			pokemon = new Pokemon();
			pokemon.setId(optional.get().getId());
			pokemon.setName(optional.get().getName());
			pokemon.setCreated(optional.get().getCreated());
			pokemon.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate pokemon with id:" +  Integer.toString(id));
		}
	}
}
