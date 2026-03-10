package com.gabriel.pokems.service;
import com.gabriel.pokems.model.CoinFlip;
public interface CoinFlipService {
	CoinFlip[] getAll() throws Exception;
	CoinFlip get(Integer id) throws Exception;
	CoinFlip create(CoinFlip coinFlip) throws Exception;
	CoinFlip update(CoinFlip coinFlip) throws Exception;
	void delete(Integer id) throws Exception;
}
