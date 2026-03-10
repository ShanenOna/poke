package com.gabriel.pokems.model;
import lombok.Data;
import java.util.Date;

@Data
public class CatchResult{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	private int catchResultId;
	private String catchResultStatus;
	private boolean kept;
	private String message;

	public CatchResult(boolean kept, String message) {
		this.kept = kept;
		this.message = message;
	}

	public CatchResult() {}
}