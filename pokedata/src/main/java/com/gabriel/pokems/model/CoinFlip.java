package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;


@Data
public class CoinFlip{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int coinFlipId;
private String coinFlipResult;
}
