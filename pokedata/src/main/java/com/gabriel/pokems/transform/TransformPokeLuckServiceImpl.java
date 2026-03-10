package com.gabriel.pokems.transform;
import com.gabriel.pokems.entity.PokeLuckData;
import com.gabriel.pokems.model.PokeLuck;
import org.springframework.stereotype.Service;
@Service
public class TransformPokeLuckServiceImpl implements TransformPokeLuckService {
	@Override
	public PokeLuckData transform(PokeLuck pokeLuck){
		PokeLuckData pokeLuckData = new PokeLuckData();
		pokeLuckData.setId(pokeLuck.getId());
		pokeLuckData.setPlayerId(pokeLuck.getPlayerId());
		pokeLuckData.setPlayerName(pokeLuck.getPlayerName());
		pokeLuckData.setPositionX(pokeLuck.getPositionX());
		pokeLuckData.setPositionY(pokeLuck.getPositionY());
		pokeLuckData.setPokemonId(pokeLuck.getPokemonId());
		pokeLuckData.setPokemonName(pokeLuck.getPokemonName());
		pokeLuckData.setPokemonType(pokeLuck.getPokemonType());
		pokeLuckData.setTriggerId(pokeLuck.getTriggerId());
		pokeLuckData.setTriggerName(pokeLuck.getTriggerName());
		pokeLuckData.setTriggerPositionX(pokeLuck.getTriggerPositionX());
		pokeLuckData.setTriggerPositionY(pokeLuck.getTriggerPositionY());
		pokeLuckData.setCoinFlipId(pokeLuck.getCoinFlipId());
		pokeLuckData.setCoinFlipResult(pokeLuck.getCoinFlipResult());
		pokeLuckData.setCatchResultId(pokeLuck.getCatchResultId());
		pokeLuckData.setCatchResultStatus(pokeLuck.getCatchResultStatus());
		pokeLuckData.setEventLogId(pokeLuck.getEventLogId());
		pokeLuckData.setEventType(pokeLuck.getEventType());
		pokeLuckData.setEventTimestamp(pokeLuck.getEventTimestamp());
		pokeLuckData.setInventoryId(pokeLuck.getInventoryId());
		pokeLuckData.setInventoryCount(pokeLuck.getInventoryCount());
		return pokeLuckData;
	}
	@Override

	public PokeLuck transform(PokeLuckData pokeLuckData){;
		PokeLuck pokeLuck = new PokeLuck();
		pokeLuck.setId(pokeLuckData.getId());
		pokeLuck.setPlayerId(pokeLuckData.getPlayerId());
		pokeLuck.setPlayerName(pokeLuckData.getPlayerName());
		pokeLuck.setPositionX(pokeLuckData.getPositionX());
		pokeLuck.setPositionY(pokeLuckData.getPositionY());
		pokeLuck.setPokemonId(pokeLuckData.getPokemonId());
		pokeLuck.setPokemonName(pokeLuckData.getPokemonName());
		pokeLuck.setPokemonType(pokeLuckData.getPokemonType());
		pokeLuck.setTriggerId(pokeLuckData.getTriggerId());
		pokeLuck.setTriggerName(pokeLuckData.getTriggerName());
		pokeLuck.setTriggerPositionX(pokeLuckData.getTriggerPositionX());
		pokeLuck.setTriggerPositionY(pokeLuckData.getTriggerPositionY());
		pokeLuck.setCoinFlipId(pokeLuckData.getCoinFlipId());
		pokeLuck.setCoinFlipResult(pokeLuckData.getCoinFlipResult());
		pokeLuck.setCatchResultId(pokeLuckData.getCatchResultId());
		pokeLuck.setCatchResultStatus(pokeLuckData.getCatchResultStatus());
		pokeLuck.setEventLogId(pokeLuckData.getEventLogId());
		pokeLuck.setEventType(pokeLuckData.getEventType());
		pokeLuck.setEventTimestamp(pokeLuckData.getEventTimestamp());
		pokeLuck.setInventoryId(pokeLuckData.getInventoryId());
		pokeLuck.setInventoryCount(pokeLuckData.getInventoryCount());
		pokeLuck.setCreated(pokeLuckData.getCreated());
		pokeLuck.setLastUpdated(pokeLuckData.getLastUpdated());
		return pokeLuck;
	}
}
