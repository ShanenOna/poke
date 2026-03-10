package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.EventLogData;
import com.gabriel.pokems.model.EventLog;
import com.gabriel.pokems.repository.EventLogDataRepository;
import com.gabriel.pokems.service.EventLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EventLogServiceImpl implements EventLogService {
	Logger logger = LoggerFactory.getLogger(EventLogServiceImpl.class);
	@Autowired
	EventLogDataRepository eventLogDataRepository;
	@Autowired
	@Override
	public EventLog[] getAll() {
		List<EventLogData> eventLogsData = new ArrayList<>();
		List<EventLog> eventLogs = new ArrayList<>();
		eventLogDataRepository.findAll().forEach(eventLogsData::add);
		Iterator<EventLogData> it = eventLogsData.iterator();
		while(it.hasNext()) {
			EventLogData eventLogData = it.next();
			EventLog eventLog = new EventLog();
			eventLog.setId(eventLogData.getId());
			eventLog.setName(eventLogData.getName());
			eventLogs.add(eventLog);
		}
		EventLog[] array = new EventLog[eventLogs.size()];
		for  (int i=0; i<eventLogs.size(); i++){
			array[i] = eventLogs.get(i);
		}
		return array;
	}
	@Override
	public EventLog create(EventLog eventLog) {
		logger.info(" add:Input " + eventLog.toString());
		EventLogData eventLogData = new EventLogData();
		eventLogData.setName(eventLog.getName());
		eventLogData = eventLogDataRepository.save(eventLogData);
		logger.info(" add:Input " + eventLogData.toString());
			EventLog newEventLog = new EventLog();
			newEventLog.setId(eventLogData.getId());
			newEventLog.setName(eventLogData.getName());
		return newEventLog;
	}

	@Override
	public EventLog update(EventLog eventLog) {
		EventLog updatedEventLog = null;
		int id = eventLog.getId();
		Optional<EventLogData> optional  = eventLogDataRepository.findById(eventLog.getId());
		if(optional.isPresent()){
			EventLogData originalEventLogData = new EventLogData();
			originalEventLogData.setId(eventLog.getId());
			originalEventLogData.setName(eventLog.getName());
			originalEventLogData.setCreated(optional.get().getCreated());
			EventLogData eventLogData = eventLogDataRepository.save(originalEventLogData);
			updatedEventLog = new EventLog();
			updatedEventLog.setId(eventLogData.getId());
			updatedEventLog.setName(eventLogData.getName());
			updatedEventLog.setCreated(eventLogData.getCreated());
			updatedEventLog.setLastUpdated(eventLogData.getLastUpdated());
		}
		else {
			logger.error("EventLog record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedEventLog;
	}

	@Override
	public EventLog get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		EventLog eventLog = null;
		Optional<EventLogData> optional = eventLogDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			eventLog = new EventLog();
			eventLog.setId(optional.get().getId());
			eventLog.setName(optional.get().getName());
			eventLog.setCreated(optional.get().getCreated());
			eventLog.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return eventLog;
	}
	@Override
	public void delete(Integer id) {
		EventLog eventLog = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<EventLogData> optional = eventLogDataRepository.findById(id);
		if( optional.isPresent()) {
			EventLogData eventLogDatum = optional.get();
			eventLogDataRepository.delete(optional.get());
			logger.info(" Successfully deleted EventLog record with id: " + Integer.toString(id));
			eventLog = new EventLog();
			eventLog.setId(optional.get().getId());
			eventLog.setName(optional.get().getName());
			eventLog.setCreated(optional.get().getCreated());
			eventLog.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate eventLog with id:" +  Integer.toString(id));
		}
	}
}
