package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;


@Data
public class Pokemon{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int pokemonId;
private String pokemonName;
private String pokemonType;
}
