package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.TriggerData;
import com.gabriel.pokems.model.Trigger;
import com.gabriel.pokems.repository.TriggerDataRepository;
import com.gabriel.pokems.service.TriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class TriggerServiceImpl implements TriggerService {
	Logger logger = LoggerFactory.getLogger(TriggerServiceImpl.class);
	@Autowired
	TriggerDataRepository triggerDataRepository;
	@Autowired
	@Override
	public Trigger[] getAll() {
		List<TriggerData> triggersData = new ArrayList<>();
		List<Trigger> triggers = new ArrayList<>();
		triggerDataRepository.findAll().forEach(triggersData::add);
		Iterator<TriggerData> it = triggersData.iterator();
		while(it.hasNext()) {
			TriggerData triggerData = it.next();
			Trigger trigger = new Trigger();
			trigger.setId(triggerData.getId());
			trigger.setName(triggerData.getName());
			triggers.add(trigger);
		}
		Trigger[] array = new Trigger[triggers.size()];
		for  (int i=0; i<triggers.size(); i++){
			array[i] = triggers.get(i);
		}
		return array;
	}
	@Override
	public Trigger create(Trigger trigger) {
		logger.info(" add:Input " + trigger.toString());
		TriggerData triggerData = new TriggerData();
		triggerData.setName(trigger.getName());
		triggerData = triggerDataRepository.save(triggerData);
		logger.info(" add:Input " + triggerData.toString());
			Trigger newTrigger = new Trigger();
			newTrigger.setId(triggerData.getId());
			newTrigger.setName(triggerData.getName());
		return newTrigger;
	}

	@Override
	public Trigger update(Trigger trigger) {
		Trigger updatedTrigger = null;
		int id = trigger.getId();
		Optional<TriggerData> optional  = triggerDataRepository.findById(trigger.getId());
		if(optional.isPresent()){
			TriggerData originalTriggerData = new TriggerData();
			originalTriggerData.setId(trigger.getId());
			originalTriggerData.setName(trigger.getName());
			originalTriggerData.setCreated(optional.get().getCreated());
			TriggerData triggerData = triggerDataRepository.save(originalTriggerData);
			updatedTrigger = new Trigger();
			updatedTrigger.setId(triggerData.getId());
			updatedTrigger.setName(triggerData.getName());
			updatedTrigger.setCreated(triggerData.getCreated());
			updatedTrigger.setLastUpdated(triggerData.getLastUpdated());
		}
		else {
			logger.error("Trigger record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedTrigger;
	}

	@Override
	public Trigger get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Trigger trigger = null;
		Optional<TriggerData> optional = triggerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			trigger = new Trigger();
			trigger.setId(optional.get().getId());
			trigger.setName(optional.get().getName());
			trigger.setCreated(optional.get().getCreated());
			trigger.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return trigger;
	}
	@Override
	public void delete(Integer id) {
		Trigger trigger = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<TriggerData> optional = triggerDataRepository.findById(id);
		if( optional.isPresent()) {
			TriggerData triggerDatum = optional.get();
			triggerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Trigger record with id: " + Integer.toString(id));
			trigger = new Trigger();
			trigger.setId(optional.get().getId());
			trigger.setName(optional.get().getName());
			trigger.setCreated(optional.get().getCreated());
			trigger.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate trigger with id:" +  Integer.toString(id));
		}
	}
}
