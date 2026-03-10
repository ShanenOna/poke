package com.gabriel.pokems.serviceimpl;
import com.gabriel.pokems.entity.PlayerData;
import com.gabriel.pokems.model.Player;
import com.gabriel.pokems.repository.PlayerDataRepository;
import com.gabriel.pokems.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PlayerServiceImpl implements PlayerService {
	Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
	@Autowired
	PlayerDataRepository playerDataRepository;
	@Autowired
	@Override
	public Player[] getAll() {
		List<PlayerData> playersData = new ArrayList<>();
		List<Player> players = new ArrayList<>();
		playerDataRepository.findAll().forEach(playersData::add);
		Iterator<PlayerData> it = playersData.iterator();
		while(it.hasNext()) {
			PlayerData playerData = it.next();
			Player player = new Player();
			player.setId(playerData.getId());
			player.setName(playerData.getName());
			players.add(player);
		}
		Player[] array = new Player[players.size()];
		for  (int i=0; i<players.size(); i++){
			array[i] = players.get(i);
		}
		return array;
	}
	@Override
	public Player create(Player player) {
		logger.info(" add:Input " + player.toString());
		PlayerData playerData = new PlayerData();
		playerData.setName(player.getName());
		playerData = playerDataRepository.save(playerData);
		logger.info(" add:Input " + playerData.toString());
			Player newPlayer = new Player();
			newPlayer.setId(playerData.getId());
			newPlayer.setName(playerData.getName());
		return newPlayer;
	}

	@Override
	public Player update(Player player) {
		Player updatedPlayer = null;
		int id = player.getId();
		Optional<PlayerData> optional  = playerDataRepository.findById(player.getId());
		if(optional.isPresent()){
			PlayerData originalPlayerData = new PlayerData();
			originalPlayerData.setId(player.getId());
			originalPlayerData.setName(player.getName());
			originalPlayerData.setCreated(optional.get().getCreated());
			PlayerData playerData = playerDataRepository.save(originalPlayerData);
			updatedPlayer = new Player();
			updatedPlayer.setId(playerData.getId());
			updatedPlayer.setName(playerData.getName());
			updatedPlayer.setCreated(playerData.getCreated());
			updatedPlayer.setLastUpdated(playerData.getLastUpdated());
		}
		else {
			logger.error("Player record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedPlayer;
	}

	@Override
	public Player get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Player player = null;
		Optional<PlayerData> optional = playerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			player = new Player();
			player.setId(optional.get().getId());
			player.setName(optional.get().getName());
			player.setCreated(optional.get().getCreated());
			player.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return player;
	}
	@Override
	public void delete(Integer id) {
		Player player = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<PlayerData> optional = playerDataRepository.findById(id);
		if( optional.isPresent()) {
			PlayerData playerDatum = optional.get();
			playerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Player record with id: " + Integer.toString(id));
			player = new Player();
			player.setId(optional.get().getId());
			player.setName(optional.get().getName());
			player.setCreated(optional.get().getCreated());
			player.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate player with id:" +  Integer.toString(id));
		}
	}
}
