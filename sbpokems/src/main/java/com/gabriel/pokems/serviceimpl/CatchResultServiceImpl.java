package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.CatchResultData;
import com.gabriel.pokems.model.CatchResult;
import com.gabriel.pokems.repository.CatchResultDataRepository;
import com.gabriel.pokems.service.CatchResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CatchResultServiceImpl implements CatchResultService {
	Logger logger = LoggerFactory.getLogger(CatchResultServiceImpl.class);
	@Autowired
	CatchResultDataRepository catchResultDataRepository;
	@Autowired
	@Override
	public CatchResult[] getAll() {
		List<CatchResultData> catchResultsData = new ArrayList<>();
		List<CatchResult> catchResults = new ArrayList<>();
		catchResultDataRepository.findAll().forEach(catchResultsData::add);
		Iterator<CatchResultData> it = catchResultsData.iterator();
		while(it.hasNext()) {
			CatchResultData catchResultData = it.next();
			CatchResult catchResult = new CatchResult();
			catchResult.setId(catchResultData.getId());
			catchResult.setName(catchResultData.getName());
			catchResults.add(catchResult);
		}
		CatchResult[] array = new CatchResult[catchResults.size()];
		for  (int i=0; i<catchResults.size(); i++){
			array[i] = catchResults.get(i);
		}
		return array;
	}
	@Override
	public CatchResult create(CatchResult catchResult) {
		logger.info(" add:Input " + catchResult.toString());
		CatchResultData catchResultData = new CatchResultData();
		catchResultData.setName(catchResult.getName());
		catchResultData = catchResultDataRepository.save(catchResultData);
		logger.info(" add:Input " + catchResultData.toString());
			CatchResult newCatchResult = new CatchResult();
			newCatchResult.setId(catchResultData.getId());
			newCatchResult.setName(catchResultData.getName());
		return newCatchResult;
	}

	@Override
	public CatchResult update(CatchResult catchResult) {
		CatchResult updatedCatchResult = null;
		int id = catchResult.getId();
		Optional<CatchResultData> optional  = catchResultDataRepository.findById(catchResult.getId());
		if(optional.isPresent()){
			CatchResultData originalCatchResultData = new CatchResultData();
			originalCatchResultData.setId(catchResult.getId());
			originalCatchResultData.setName(catchResult.getName());
			originalCatchResultData.setCreated(optional.get().getCreated());
			CatchResultData catchResultData = catchResultDataRepository.save(originalCatchResultData);
			updatedCatchResult = new CatchResult();
			updatedCatchResult.setId(catchResultData.getId());
			updatedCatchResult.setName(catchResultData.getName());
			updatedCatchResult.setCreated(catchResultData.getCreated());
			updatedCatchResult.setLastUpdated(catchResultData.getLastUpdated());
		}
		else {
			logger.error("CatchResult record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedCatchResult;
	}

	@Override
	public CatchResult get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		CatchResult catchResult = null;
		Optional<CatchResultData> optional = catchResultDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			catchResult = new CatchResult();
			catchResult.setId(optional.get().getId());
			catchResult.setName(optional.get().getName());
			catchResult.setCreated(optional.get().getCreated());
			catchResult.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return catchResult;
	}
	@Override
	public void delete(Integer id) {
		CatchResult catchResult = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<CatchResultData> optional = catchResultDataRepository.findById(id);
		if( optional.isPresent()) {
			CatchResultData catchResultDatum = optional.get();
			catchResultDataRepository.delete(optional.get());
			logger.info(" Successfully deleted CatchResult record with id: " + Integer.toString(id));
			catchResult = new CatchResult();
			catchResult.setId(optional.get().getId());
			catchResult.setName(optional.get().getName());
			catchResult.setCreated(optional.get().getCreated());
			catchResult.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate catchResult with id:" +  Integer.toString(id));
		}
	}
}
