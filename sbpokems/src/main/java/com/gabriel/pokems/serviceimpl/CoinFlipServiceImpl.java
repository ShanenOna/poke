package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.CoinFlipData;
import com.gabriel.pokems.model.CoinFlip;
import com.gabriel.pokems.repository.CoinFlipDataRepository;
import com.gabriel.pokems.service.CoinFlipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CoinFlipServiceImpl implements CoinFlipService {
	Logger logger = LoggerFactory.getLogger(CoinFlipServiceImpl.class);
	@Autowired
	CoinFlipDataRepository coinFlipDataRepository;
	@Autowired
	@Override
	public CoinFlip[] getAll() {
		List<CoinFlipData> coinFlipsData = new ArrayList<>();
		List<CoinFlip> coinFlips = new ArrayList<>();
		coinFlipDataRepository.findAll().forEach(coinFlipsData::add);
		Iterator<CoinFlipData> it = coinFlipsData.iterator();
		while(it.hasNext()) {
			CoinFlipData coinFlipData = it.next();
			CoinFlip coinFlip = new CoinFlip();
			coinFlip.setId(coinFlipData.getId());
			coinFlip.setName(coinFlipData.getName());
			coinFlips.add(coinFlip);
		}
		CoinFlip[] array = new CoinFlip[coinFlips.size()];
		for  (int i=0; i<coinFlips.size(); i++){
			array[i] = coinFlips.get(i);
		}
		return array;
	}
	@Override
	public CoinFlip create(CoinFlip coinFlip) {
		logger.info(" add:Input " + coinFlip.toString());
		CoinFlipData coinFlipData = new CoinFlipData();
		coinFlipData.setName(coinFlip.getName());
		coinFlipData = coinFlipDataRepository.save(coinFlipData);
		logger.info(" add:Input " + coinFlipData.toString());
			CoinFlip newCoinFlip = new CoinFlip();
			newCoinFlip.setId(coinFlipData.getId());
			newCoinFlip.setName(coinFlipData.getName());
		return newCoinFlip;
	}

	@Override
	public CoinFlip update(CoinFlip coinFlip) {
		CoinFlip updatedCoinFlip = null;
		int id = coinFlip.getId();
		Optional<CoinFlipData> optional  = coinFlipDataRepository.findById(coinFlip.getId());
		if(optional.isPresent()){
			CoinFlipData originalCoinFlipData = new CoinFlipData();
			originalCoinFlipData.setId(coinFlip.getId());
			originalCoinFlipData.setName(coinFlip.getName());
			originalCoinFlipData.setCreated(optional.get().getCreated());
			CoinFlipData coinFlipData = coinFlipDataRepository.save(originalCoinFlipData);
			updatedCoinFlip = new CoinFlip();
			updatedCoinFlip.setId(coinFlipData.getId());
			updatedCoinFlip.setName(coinFlipData.getName());
			updatedCoinFlip.setCreated(coinFlipData.getCreated());
			updatedCoinFlip.setLastUpdated(coinFlipData.getLastUpdated());
		}
		else {
			logger.error("CoinFlip record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedCoinFlip;
	}

	@Override
	public CoinFlip get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		CoinFlip coinFlip = null;
		Optional<CoinFlipData> optional = coinFlipDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			coinFlip = new CoinFlip();
			coinFlip.setId(optional.get().getId());
			coinFlip.setName(optional.get().getName());
			coinFlip.setCreated(optional.get().getCreated());
			coinFlip.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return coinFlip;
	}
	@Override
	public void delete(Integer id) {
		CoinFlip coinFlip = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<CoinFlipData> optional = coinFlipDataRepository.findById(id);
		if( optional.isPresent()) {
			CoinFlipData coinFlipDatum = optional.get();
			coinFlipDataRepository.delete(optional.get());
			logger.info(" Successfully deleted CoinFlip record with id: " + Integer.toString(id));
			coinFlip = new CoinFlip();
			coinFlip.setId(optional.get().getId());
			coinFlip.setName(optional.get().getName());
			coinFlip.setCreated(optional.get().getCreated());
			coinFlip.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate coinFlip with id:" +  Integer.toString(id));
		}
	}
}
