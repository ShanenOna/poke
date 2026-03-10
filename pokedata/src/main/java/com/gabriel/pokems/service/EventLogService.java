package com.gabriel.pokems.service;
import com.gabriel.pokems.model.EventLog;
public interface EventLogService {
	EventLog[] getAll() throws Exception;
	EventLog get(Integer id) throws Exception;
	EventLog create(EventLog eventLog) throws Exception;
	EventLog update(EventLog eventLog) throws Exception;
	void delete(Integer id) throws Exception;
}
