package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.PokeLuckData;
import com.gabriel.pokems.model.PokeLuck;
import com.gabriel.pokems.repository.PokeLuckDataRepository;
import com.gabriel.pokems.service.PokeLuckService;
import com.gabriel.pokems.transform.TransformPokeLuckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PokeLuckServiceImpl implements PokeLuckService {
	Logger logger = LoggerFactory.getLogger(PokeLuckServiceImpl.class);
	@Autowired
	PokeLuckDataRepository pokeLuckDataRepository;
	@Autowired
	TransformPokeLuckService tansformPokeLuckService;
	@Override
	public PokeLuck[] getAll() {
		List<PokeLuckData> pokeLucksData = new ArrayList<>();
		List<PokeLuck> pokeLucks = new ArrayList<>();
		pokeLuckDataRepository.findAll().forEach(pokeLucksData::add);
		Iterator<PokeLuckData> it = pokeLucksData.iterator();
		while(it.hasNext()) {
			PokeLuckData pokeLuckData = it.next();
			PokeLuck pokeLuck = tansformPokeLuckService.transform(pokeLuckData);
			pokeLucks.add(pokeLuck);
		}
		PokeLuck[] array = new PokeLuck[pokeLucks.size()];
		for  (int i=0; i<pokeLucks.size(); i++){
			array[i] = pokeLucks.get(i);
		}
		return array;
	}
	@Override
	public PokeLuck create(PokeLuck pokeLuck) {
		logger.info(" add:Input " + pokeLuck.toString());
		PokeLuckData pokeLuckData = tansformPokeLuckService.transform(pokeLuck);
		pokeLuckData = pokeLuckDataRepository.save(pokeLuckData);
		logger.info(" add:Input " + pokeLuckData.toString());
			PokeLuck newPokeLuck = tansformPokeLuckService.transform(pokeLuckData);
		return newPokeLuck;
	}

	@Override
	public PokeLuck update(PokeLuck pokeLuck) {
		PokeLuck updatedPokeLuck = null;
		int id = pokeLuck.getId();
		Optional<PokeLuckData> optional  = pokeLuckDataRepository.findById(pokeLuck.getId());
		if(optional.isPresent()){
			PokeLuckData originalPokeLuckData = tansformPokeLuckService.transform(pokeLuck);
			originalPokeLuckData.setCreated(optional.get().getCreated());
			PokeLuckData pokeLuckData = pokeLuckDataRepository.save(originalPokeLuckData);
			updatedPokeLuck = tansformPokeLuckService.transform(pokeLuckData);
		}
		else {
			logger.error("PokeLuck record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedPokeLuck;
	}

	@Override
	public PokeLuck get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		PokeLuck pokeLuck = null;
		Optional<PokeLuckData> optional = pokeLuckDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			pokeLuck = tansformPokeLuckService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return pokeLuck;
	}
	@Override
	public void delete(Integer id) {
		PokeLuck pokeLuck = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<PokeLuckData> optional = pokeLuckDataRepository.findById(id);
		if( optional.isPresent()) {
			PokeLuckData pokeLuckDatum = optional.get();
			pokeLuckDataRepository.delete(optional.get());
			logger.info(" Successfully deleted PokeLuck record with id: " + Integer.toString(id));
			pokeLuck = tansformPokeLuckService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate pokeLuck with id:" +  Integer.toString(id));
		}
	}
}
