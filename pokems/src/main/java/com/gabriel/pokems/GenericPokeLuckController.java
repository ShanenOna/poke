package com.gabriel.pokems;
import com.gabriel.pokems.model.PokeLuck;
import com.gabriel.pokems.model.Player;
import com.gabriel.pokems.service.PlayerService;
import com.gabriel.pokems.model.Pokemon;
import com.gabriel.pokems.service.PokemonService;
import com.gabriel.pokems.model.Trigger;
import com.gabriel.pokems.service.TriggerService;
import com.gabriel.pokems.model.CoinFlip;
import com.gabriel.pokems.service.CoinFlipService;
import com.gabriel.pokems.model.CatchResult;
import com.gabriel.pokems.service.CatchResultService;
import com.gabriel.pokems.model.EventLog;
import com.gabriel.pokems.service.EventLogService;
import com.gabriel.pokems.model.Inventory;
import com.gabriel.pokems.service.InventoryService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.Window;
import lombok.Setter;
import javafx.util.StringConverter;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericPokeLuckController implements Initializable{
	@Setter
	CreatePokeLuckController createPokeLuckController;

	@Setter
	DeletePokeLuckController deletePokeLuckController ;

	@Setter
	EditPokeLuckController editPokeLuckController;

	@Setter
	ManagePokeLuckController managePokeLuckController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<PokeLuck> lvPokeLucks;

	@Setter
	public static PokeLuck selectedItem;
	public TextField txtId;
	public ComboBox<Player> cmbPlayer;
	public TextField txtPlayerName;
	public TextField txtPositionX;
	public TextField txtPositionY;
	public ComboBox<Pokemon> cmbPokemon;
	public TextField txtPokemonName;
	public TextField txtPokemonType;
	public ComboBox<Trigger> cmbTrigger;
	public TextField txtTriggerName;
	public TextField txtTriggerPositionX;
	public TextField txtTriggerPositionY;
	public ComboBox<CoinFlip> cmbCoinFlip;
public TextField txtCoinFlipName;
public TextField txtCoinFlipResult;
public ComboBox<CatchResult> cmbCatchResult;
public TextField txtCatchResultName;
public TextField txtCatchResultStatus;
public ComboBox<EventLog> cmbEventLog;
public TextField txtEventLogName;
public TextField txtEventType;
public DatePicker dtEventTimestamp;
public ComboBox<Inventory> cmbInventory;
public TextField txtInventoryName;
public TextField txtInventoryCount;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Player[] players =  (Player[]) PlayerService.getService().getAll();
		cmbPlayer.getItems().addAll(players);
		StringConverter<Player> playerConverter = new StringConverter<Player>() {
			@Override
			public String toString(Player player) {
			if(player==null)
				return "";
			else
				return player.toString();
			}
			@Override
			public Player fromString(String s) {
				if(!s.isEmpty()){
					for (Player player : players) {
						if (s.equals(player.getName())){
							return player;
						}
					}
				}
				return null;
			}
		};
		cmbPlayer.setConverter(playerConverter);
		Pokemon[] pokemons =  (Pokemon[]) PokemonService.getService().getAll();
		cmbPokemon.getItems().addAll(pokemons);
		StringConverter<Pokemon> pokemonConverter = new StringConverter<Pokemon>() {
			@Override
			public String toString(Pokemon pokemon) {
			if(pokemon==null)
				return "";
			else
				return pokemon.toString();
			}
			@Override
			public Pokemon fromString(String s) {
				if(!s.isEmpty()){
					for (Pokemon pokemon : pokemons) {
						if (s.equals(pokemon.getName())){
							return pokemon;
						}
					}
				}
				return null;
			}
		};
		cmbPokemon.setConverter(pokemonConverter);
		Trigger[] triggers =  (Trigger[]) TriggerService.getService().getAll();
		cmbTrigger.getItems().addAll(triggers);
		StringConverter<Trigger> triggerConverter = new StringConverter<Trigger>() {
			@Override
			public String toString(Trigger trigger) {
			if(trigger==null)
				return "";
			else
				return trigger.toString();
			}
			@Override
			public Trigger fromString(String s) {
				if(!s.isEmpty()){
					for (Trigger trigger : triggers) {
						if (s.equals(trigger.getName())){
							return trigger;
						}
					}
				}
				return null;
			}
		};
		cmbTrigger.setConverter(triggerConverter);
		CoinFlip[] coinFlips =  (CoinFlip[]) CoinFlipService.getService().getAll();
		cmbCoinFlip.getItems().addAll(coinFlips);
		StringConverter<CoinFlip> coinFlipConverter = new StringConverter<CoinFlip>() {
			@Override
			public String toString(CoinFlip coinFlip) {
			if(coinFlip==null)
				return "";
			else
				return coinFlip.toString();
			}
			@Override
			public CoinFlip fromString(String s) {
				if(!s.isEmpty()){
					for (CoinFlip coinFlip : coinFlips) {
						if (s.equals(coinFlip.getName())){
							return coinFlip;
						}
					}
				}
				return null;
			}
		};
		cmbCoinFlip.setConverter(coinFlipConverter);
		CatchResult[] catchResults =  (CatchResult[]) CatchResultService.getService().getAll();
		cmbCatchResult.getItems().addAll(catchResults);
		StringConverter<CatchResult> catchResultConverter = new StringConverter<CatchResult>() {
			@Override
			public String toString(CatchResult catchResult) {
			if(catchResult==null)
				return "";
			else
				return catchResult.toString();
			}
			@Override
			public CatchResult fromString(String s) {
				if(!s.isEmpty()){
					for (CatchResult catchResult : catchResults) {
						if (s.equals(catchResult.getName())){
							return catchResult;
						}
					}
				}
				return null;
			}
		};
		cmbCatchResult.setConverter(catchResultConverter);
		EventLog[] eventLogs =  (EventLog[]) EventLogService.getService().getAll();
		cmbEventLog.getItems().addAll(eventLogs);
		StringConverter<EventLog> eventLogConverter = new StringConverter<EventLog>() {
			@Override
			public String toString(EventLog eventLog) {
			if(eventLog==null)
				return "";
			else
				return eventLog.toString();
			}
			@Override
			public EventLog fromString(String s) {
				if(!s.isEmpty()){
					for (EventLog eventLog : eventLogs) {
						if (s.equals(eventLog.getName())){
							return eventLog;
						}
					}
				}
				return null;
			}
		};
		cmbEventLog.setConverter(eventLogConverter);
		Inventory[] inventorys =  (Inventory[]) InventoryService.getService().getAll();
		cmbInventory.getItems().addAll(inventorys);
		StringConverter<Inventory> inventoryConverter = new StringConverter<Inventory>() {
			@Override
			public String toString(Inventory inventory) {
			if(inventory==null)
				return "";
			else
				return inventory.toString();
			}
			@Override
			public Inventory fromString(String s) {
				if(!s.isEmpty()){
					for (Inventory inventory : inventorys) {
						if (s.equals(inventory.getName())){
							return inventory;
						}
					}
				}
				return null;
			}
		};
		cmbInventory.setConverter(inventoryConverter);
		init();
	}
	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}
	protected PokeLuck toObject(boolean isEdit){
		PokeLuck pokeLuck= new PokeLuck();
		try {
			if(isEdit) {
				pokeLuck.setId(Integer.parseInt(txtId.getText()));
			}
			Player player = cmbPlayer.getSelectionModel().getSelectedItem();
			pokeLuck.setPlayerId(player.getId());
			pokeLuck.setPlayerName(player.getName());
			pokeLuck.setPositionX(Integer.parseInt(txtPositionX.getText()));
			pokeLuck.setPositionY(Integer.parseInt(txtPositionY.getText()));
			Pokemon pokemon = cmbPokemon.getSelectionModel().getSelectedItem();
			pokeLuck.setPokemonId(pokemon.getId());
			pokeLuck.setPokemonName(pokemon.getName());
			pokeLuck.setPokemonType(txtPokemonType.getText());
			Trigger trigger = cmbTrigger.getSelectionModel().getSelectedItem();
			pokeLuck.setTriggerId(trigger.getId());
			pokeLuck.setTriggerName(trigger.getName());
			pokeLuck.setTriggerPositionX(Integer.parseInt(txtTriggerPositionX.getText()));
			pokeLuck.setTriggerPositionY(Integer.parseInt(txtTriggerPositionY.getText()));
			CoinFlip coinFlip = cmbCoinFlip.getSelectionModel().getSelectedItem();
			pokeLuck.setCoinFlipId(coinFlip.getId());
			pokeLuck.setCoinFlipName(coinFlip.getName());
			pokeLuck.setCoinFlipResult(txtCoinFlipResult.getText());
			CatchResult catchResult = cmbCatchResult.getSelectionModel().getSelectedItem();
			pokeLuck.setCatchResultId(catchResult.getId());
			pokeLuck.setCatchResultName(catchResult.getName());
			pokeLuck.setCatchResultStatus(txtCatchResultStatus.getText());
			EventLog eventLog = cmbEventLog.getSelectionModel().getSelectedItem();
			pokeLuck.setEventLogId(eventLog.getId());
			pokeLuck.setEventLogName(eventLog.getName());
			pokeLuck.setEventType(txtEventType.getText());
			pokeLuck.setEventTimestamp(toDate(dtEventTimestamp.getValue()));
			Inventory inventory = cmbInventory.getSelectionModel().getSelectedItem();
			pokeLuck.setInventoryId(inventory.getId());
			pokeLuck.setInventoryName(inventory.getName());
			pokeLuck.setInventoryCount(Integer.parseInt(txtInventoryCount.getText()));
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return pokeLuck;
	}
	protected void setFields(String action){
		String formattedDate;
		PokeLuck pokeLuck = GenericPokeLuckController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		txtId.setText(Integer.toString(pokeLuck.getId()));
		Player player = PlayerService.getService().get(pokeLuck.getPlayerId());
		cmbPlayer.getSelectionModel().select(player);
		if(action.equals("Create") || action.equals("Edit")){
			cmbPlayer.setVisible(true);
			txtPlayerName.setVisible(false);
			cmbPlayer.getSelectionModel().select(player);
		}
		else{
			cmbPlayer.setVisible(false);
			txtPlayerName.setVisible(true);
			txtPlayerName.setText(player.getName());
		}
		txtPlayerName.setText(pokeLuck.getPlayerName());
		txtPositionX.setText(Integer.toString(pokeLuck.getPositionX()));
		txtPositionY.setText(Integer.toString(pokeLuck.getPositionY()));
		Pokemon pokemon = PokemonService.getService().get(pokeLuck.getPokemonId());
		cmbPokemon.getSelectionModel().select(pokemon);
		if(action.equals("Create") || action.equals("Edit")){
			cmbPokemon.setVisible(true);
			txtPokemonName.setVisible(false);
			cmbPokemon.getSelectionModel().select(pokemon);
		}
		else{
			cmbPokemon.setVisible(false);
			txtPokemonName.setVisible(true);
			txtPokemonName.setText(pokemon.getName());
		}
		txtPokemonName.setText(pokeLuck.getPokemonName());
		txtPokemonType.setText(pokeLuck.getPokemonType());
		Trigger trigger = TriggerService.getService().get(pokeLuck.getTriggerId());
		cmbTrigger.getSelectionModel().select(trigger);
		if(action.equals("Create") || action.equals("Edit")){
			cmbTrigger.setVisible(true);
			txtTriggerName.setVisible(false);
			cmbTrigger.getSelectionModel().select(trigger);
		}
		else{
			cmbTrigger.setVisible(false);
			txtTriggerName.setVisible(true);
			txtTriggerName.setText(trigger.getName());
		}
		txtTriggerName.setText(pokeLuck.getTriggerName());
		txtTriggerPositionX.setText(Integer.toString(pokeLuck.getTriggerPositionX()));
		txtTriggerPositionY.setText(Integer.toString(pokeLuck.getTriggerPositionY()));
		CoinFlip coinFlip = CoinFlipService.getService().get(pokeLuck.getCoinFlipId());
		cmbCoinFlip.getSelectionModel().select(coinFlip);
		if(action.equals("Create") || action.equals("Edit")){
			cmbCoinFlip.setVisible(true);
			txtCoinFlipName.setVisible(false);
			cmbCoinFlip.getSelectionModel().select(coinFlip);
		}
		else{
			cmbCoinFlip.setVisible(false);
			txtCoinFlipName.setVisible(true);
			txtCoinFlipName.setText(coinFlip.getName());
		}
		txtCoinFlipResult.setText(pokeLuck.getCoinFlipResult());
		CatchResult catchResult = CatchResultService.getService().get(pokeLuck.getCatchResultId());
		cmbCatchResult.getSelectionModel().select(catchResult);
		if(action.equals("Create") || action.equals("Edit")){
			cmbCatchResult.setVisible(true);
			txtCatchResultName.setVisible(false);
			cmbCatchResult.getSelectionModel().select(catchResult);
		}
		else{
			cmbCatchResult.setVisible(false);
			txtCatchResultName.setVisible(true);
			txtCatchResultName.setText(catchResult.getName());
		}
		txtCatchResultStatus.setText(pokeLuck.getCatchResultStatus());
		EventLog eventLog = EventLogService.getService().get(pokeLuck.getEventLogId());
		cmbEventLog.getSelectionModel().select(eventLog);
		if(action.equals("Create") || action.equals("Edit")){
			cmbEventLog.setVisible(true);
			txtEventLogName.setVisible(false);
			cmbEventLog.getSelectionModel().select(eventLog);
		}
		else{
			cmbEventLog.setVisible(false);
			txtEventLogName.setVisible(true);
			txtEventLogName.setText(eventLog.getName());
		}
		txtEventType.setText(pokeLuck.getEventType());
		dtEventTimestamp.setValue(toLocalDate(pokeLuck.getEventTimestamp()));
		Inventory inventory = InventoryService.getService().get(pokeLuck.getInventoryId());
		cmbInventory.getSelectionModel().select(inventory);
		if(action.equals("Create") || action.equals("Edit")){
			cmbInventory.setVisible(true);
			txtInventoryName.setVisible(false);
			cmbInventory.getSelectionModel().select(inventory);
		}
		else{
			cmbInventory.setVisible(false);
			txtInventoryName.setVisible(true);
			txtInventoryName.setText(inventory.getName());
		}
		txtInventoryCount.setText(Integer.toString(pokeLuck.getInventoryCount()));
	}

	protected void clearFields(String action){
		txtId.setText("");
		cmbPlayer.getSelectionModel().clearSelection();
		txtPlayerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbPlayer.setVisible(true);
			txtPlayerName.setVisible(false);
		}
		else{
			cmbPlayer.setVisible(false);
			txtPlayerName.setVisible(true);
		}
		//txtPlayerName.setText("");
		//txtPositionX.setText("");
		//txtPositionY.setText("");
		cmbPokemon.getSelectionModel().clearSelection();
		txtPokemonName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbPokemon.setVisible(true);
			txtPokemonName.setVisible(false);
		}
		else{
			cmbPokemon.setVisible(false);
			txtPokemonName.setVisible(true);
		}
		//txtPokemonName.setText("");
		//txtPokemonType.setText("");
		cmbTrigger.getSelectionModel().clearSelection();
		txtTriggerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbTrigger.setVisible(true);
			txtTriggerName.setVisible(false);
		}
		else{
			cmbTrigger.setVisible(false);
			txtTriggerName.setVisible(true);
		}
		//txtTriggerName.setText("");
		//txtTriggerPositionX.setText("");
		//txtTriggerPositionY.setText("");
		cmbCoinFlip.getSelectionModel().clearSelection();
		txtCoinFlipName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbCoinFlip.setVisible(true);
			txtCoinFlipName.setVisible(false);
		}
		else{
			cmbCoinFlip.setVisible(false);
			txtCoinFlipName.setVisible(true);
		}
		//txtCoinFlipResult.setText("");
		cmbCatchResult.getSelectionModel().clearSelection();
		txtCatchResultName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbCatchResult.setVisible(true);
			txtCatchResultName.setVisible(false);
		}
		else{
			cmbCatchResult.setVisible(false);
			txtCatchResultName.setVisible(true);
		}
		//txtCatchResultStatus.setText("");
		cmbEventLog.getSelectionModel().clearSelection();
		txtEventLogName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbEventLog.setVisible(true);
			txtEventLogName.setVisible(false);
		}
		else{
			cmbEventLog.setVisible(false);
			txtEventLogName.setVisible(true);
		}
		//txtEventType.setText("");
		//dtEventTimestamp.setText("");
		cmbInventory.getSelectionModel().clearSelection();
		txtInventoryName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbInventory.setVisible(true);
			txtInventoryName.setVisible(false);
		}
		else{
			cmbInventory.setVisible(false);
			txtInventoryName.setVisible(true);
		}
		//txtInventoryCount.setText("");
	}

	protected void enableFields(boolean enable){
		cmbPlayer.editableProperty().set(enable);
		txtPlayerName.editableProperty().set(enable);
		txtPlayerName.editableProperty().set(enable);
		txtPositionX.editableProperty().set(enable);
		txtPositionY.editableProperty().set(enable);
		cmbPokemon.editableProperty().set(enable);
		txtPokemonName.editableProperty().set(enable);
		txtPokemonName.editableProperty().set(enable);
		txtPokemonType.editableProperty().set(enable);
		cmbTrigger.editableProperty().set(enable);
		txtTriggerName.editableProperty().set(enable);
		txtTriggerName.editableProperty().set(enable);
		txtTriggerPositionX.editableProperty().set(enable);
		txtTriggerPositionY.editableProperty().set(enable);
		cmbCoinFlip.editableProperty().set(enable);
		txtCoinFlipName.editableProperty().set(enable);
		txtCoinFlipResult.editableProperty().set(enable);
		cmbCatchResult.editableProperty().set(enable);
		txtCatchResultName.editableProperty().set(enable);
		txtCatchResultStatus.editableProperty().set(enable);
		cmbEventLog.editableProperty().set(enable);
		txtEventLogName.editableProperty().set(enable);
		txtEventType.editableProperty().set(enable);
		dtEventTimestamp.editableProperty().set(enable);
		cmbInventory.editableProperty().set(enable);
		txtInventoryName.editableProperty().set(enable);
		txtInventoryCount.editableProperty().set(enable);
	}

	public int getId(){
		return Integer.parseInt(txtId.getText());
	}

	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}
	public void onBack(ActionEvent actionEvent) {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit and loose changes? " , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}
	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}
	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}
}

