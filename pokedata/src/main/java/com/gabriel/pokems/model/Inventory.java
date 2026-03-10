package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;


@Data
public class Inventory{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int inventoryId;
private int inventoryCount;
}
