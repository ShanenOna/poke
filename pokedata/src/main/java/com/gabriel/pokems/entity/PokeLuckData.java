package com.gabriel.pokems.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "pokeLuck_data")
public class PokeLuckData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
	private String coinFlipResult;
	private int catchResultId;
	private String catchResultStatus;
	private int eventLogId;
	private String eventType;
	private Date eventTimestamp;
	private int inventoryId;
	private int inventoryCount;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastUpdated;


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date created;

}
