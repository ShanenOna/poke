package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;

@Data
public class PokeLuck{
	private int id;
	private String name;
	private int playerId;
	private String playerName;
	private int positionX;
	private int positionY;
	private int pokemonId;
	private String pokemonName;
	private String pokemonType;
	private int triggerId;
	private String triggerName;
	private int triggerPositionX;
	private int triggerPositionY;
	private int coinFlipId;
	private String coinFlipName;
	private String coinFlipResult;
	private int catchResultId;
	private String catchResultName;
	private String catchResultStatus;
	private int eventLogId;
	private String eventLogName;
	private String eventType;
	private Date eventTimestamp;
	private int inventoryId;
	private String inventoryName;
	private int inventoryCount;
	private Date lastUpdated;
	private Date created;
}