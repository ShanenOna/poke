package com.gabriel.pokems.service;
import com.gabriel.pokems.model.CatchResult;
public interface CatchResultService {
	CatchResult[] getAll() throws Exception;
	CatchResult get(Integer id) throws Exception;
	CatchResult create(CatchResult catchResult) throws Exception;
	CatchResult update(CatchResult catchResult) throws Exception;
	void delete(Integer id) throws Exception;
}
