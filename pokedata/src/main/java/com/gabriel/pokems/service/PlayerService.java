package com.gabriel.pokems.service;
import com.gabriel.pokems.model.Player;
public interface PlayerService {
	Player[] getAll() throws Exception;
	Player get(Integer id) throws Exception;
	Player create(Player player) throws Exception;
	Player update(Player player) throws Exception;
	void delete(Integer id) throws Exception;
}
