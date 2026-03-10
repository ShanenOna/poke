package com.gabriel.pokems;
import com.gabriel.pokems.model.PokeLuck;
import com.gabriel.pokems.service.PokeLuckService;
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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;
import lombok.Setter;

public class EditPokeLuckController extends GenericPokeLuckController {
	public ImageView imgPokeLuck;
	@Override
	public void init() {
		setFields("Edit");
		enableFields(true);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			PokeLuck pokeLuck = toObject(true);
			PokeLuck newPokeLuck = PokeLuckService.getService().update(pokeLuck);
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage PokeLuck");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating pokeLuck", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
