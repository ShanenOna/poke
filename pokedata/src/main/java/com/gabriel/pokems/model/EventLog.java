package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;

@Data
public class EventLog{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int eventLogId;
private String eventType;
private Date eventTimestamp;
}
