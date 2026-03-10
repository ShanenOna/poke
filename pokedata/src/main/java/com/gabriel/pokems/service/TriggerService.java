package com.gabriel.pokems.service;
import com.gabriel.pokems.model.Trigger;
public interface TriggerService {
	Trigger[] getAll() throws Exception;
	Trigger get(Integer id) throws Exception;
	Trigger create(Trigger trigger) throws Exception;
	Trigger update(Trigger trigger) throws Exception;
	void delete(Integer id) throws Exception;
}
