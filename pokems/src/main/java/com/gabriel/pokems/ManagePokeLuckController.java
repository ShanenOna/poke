package com.gabriel.pokems;
import com.gabriel.pokems.model.PokeLuck;
import com.gabriel.pokems.service.PokeLuckService;
import com.gabriel.pokems.GenericPokeLuckController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;

public class ManagePokeLuckController extends GenericPokeLuckController{
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

	@Setter
	Scene deleteViewScene;

	public ImageView pokeLuckImage;
	@FXML
	public Button btnCreate;

	@FXML
	public Button btnEdit;

	@FXML
	public Button btnDelete;

	@FXML
	public Button btnClose;

	@FXML
	public Button imageButton;
	PokeLuck selectedItem;

	@FXML
	private ListView<PokeLuck> lvPokeLucks;

		public void refresh() {
			PokeLuck[] pokeLucks = PokeLuckService.getService().getAll();
			lvPokeLucks.getItems().clear();
			lvPokeLucks.getItems().addAll(pokeLucks);
			enableFields(false);
		}

	@Override
	public void init() {
		try {
			refresh();
		}
		catch (Exception e){
			showErrorDialog("Message: ", e.getMessage());
		}
	}

	public void onAction(MouseEvent mouseEvent) {
		GenericPokeLuckController.selectedItem = lvPokeLucks.getSelectionModel().getSelectedItem();
		if(GenericPokeLuckController.selectedItem == null) {
			return;
		}
		setFields("Manage");
	}
	public void onCreate(ActionEvent actionEvent)  throws Exception {
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(createViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManagePokeLuckJFXApp.class.getResource("create-poke-view.fxml"));
			Parent root = fxmlLoader.load();
			CreatePokeLuckController controller = fxmlLoader.getController();
			controller.setStage(stage);
			createViewScene = new Scene(root, 300, 720);
			controller.setManagePokeLuckController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Create PokeLuck");
		stage.setScene(createViewScene);
		stage.show();
	}
	public void onEdit(ActionEvent actionEvent)  throws Exception {
		if(GenericPokeLuckController.selectedItem == null){
			showErrorDialog("Please select an pokeLuck from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManagePokeLuckJFXApp.class.getResource("edit-poke-view.fxml"));
			Parent root = fxmlLoader.load();
			EditPokeLuckController controller = fxmlLoader.getController();
			controller.setStage(stage);
			editViewScene = new Scene(root, 300, 720);
			controller.setManagePokeLuckController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Edit PokeLuck");
		stage.setScene(editViewScene);
		stage.show();
	}
	public void onDelete(ActionEvent actionEvent)  throws Exception {
		if(GenericPokeLuckController.selectedItem == null){
			showErrorDialog("Please select an pokeLuck from the list", "Cannot delete");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(deleteViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManagePokeLuckJFXApp.class.getResource("delete-poke-view.fxml"));
			Parent root = fxmlLoader.load();
			DeletePokeLuckController controller = fxmlLoader.getController();
			controller.setStage(stage);
			deleteViewScene = new Scene(root, 300, 720);
			controller.setManagePokeLuckController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Delete PokeLuck");
		stage.setScene(deleteViewScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
