package com.gabriel.pokems.service;
import com.gabriel.pokems.model.PokeLuck;
public interface PokeLuckService {
	PokeLuck[] getAll() throws Exception;
	PokeLuck get(Integer id) throws Exception;
	PokeLuck create(PokeLuck pokeLuck) throws Exception;
	PokeLuck update(PokeLuck pokeLuck) throws Exception;
	void delete(Integer id) throws Exception;
}
