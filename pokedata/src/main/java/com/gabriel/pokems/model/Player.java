package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;


@Data
public class Player{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int playerId;
private String playerName;
private int positionX;
private int positionY;
}
