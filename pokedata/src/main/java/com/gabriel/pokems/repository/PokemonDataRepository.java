package com.gabriel.pokems.repository;
import com.gabriel.pokems.entity.PokemonData;
import org.springframework.data.repository.CrudRepository;
public interface PokemonDataRepository extends CrudRepository<PokemonData,Integer> {}