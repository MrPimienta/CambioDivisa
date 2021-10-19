package dad.cambiodivisa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	private Stage primaryStage;
	BorderPane root;
	HBox arriba, abajo, botonHBOX;
	VBox contenedorHBox;
	TextField arribaText, abajoText;
	ComboBox<Divisa> arribaCombo, abajoCombo;
	Button cambiarButton;
	
	//CREAMOS LAS DISTINTAS DIVISAS//
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra",0.8873);
	Divisa dolar = new Divisa("Dolar", 1.2007);
	Divisa yen = new Divisa("Yen", 133.59);
	
	//Divisa origen = yen;
	private Divisa[] divisa_array = {euro,libra,dolar,yen};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//TEXTFIELDS//
		arribaText = new TextField();
		arribaText.setPrefWidth(50);
		
		abajoText = new TextField();
		abajoText.setPrefWidth(50);
		abajoText.setEditable(false);
		
		//COMBOBOX
		//ObservableList<String> items_arriba = FXCollections.observableArrayList();
		//items_arriba.addAll(euro.getNombre(),libra.getNombre(),dolar.getNombre(),yen.getNombre());
		arribaCombo = new ComboBox<Divisa>();
		arribaCombo.getItems().addAll(divisa_array);
		arribaCombo.getSelectionModel().selectFirst();
		
	
		//ObservableList<String> items_abajo = FXCollections.observableArrayList();
		//items_abajo.addAll(euro.getNombre(),libra.getNombre(),dolar.getNombre(),yen.getNombre());
		abajoCombo = new ComboBox<Divisa>();
		abajoCombo.getItems().addAll(divisa_array);
		abajoCombo.getSelectionModel().selectLast();
			
		//BOTON//
		cambiarButton = new Button("Cambiar");
		cambiarButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				onCambiarAction(event);	
			}
		});
		
		//PANEL//
		arriba = new HBox();
		abajo = new HBox();
		botonHBOX = new HBox();
		contenedorHBox = new VBox();
		
		arriba.getChildren().addAll(arribaText, arribaCombo);
		arriba.setAlignment(Pos.CENTER);
		arriba.setPadding(new Insets(5));
		arriba.setSpacing(5);
		
		abajo.getChildren().addAll(abajoText, abajoCombo);
		abajo.setAlignment(Pos.CENTER);
		abajo.setPadding(new Insets(5));
		abajo.setSpacing(5);
		
		botonHBOX.getChildren().add(cambiarButton);
		botonHBOX.setAlignment(Pos.CENTER);
		botonHBOX.setPadding(new Insets(5));
		botonHBOX.setSpacing(5);
		
		contenedorHBox.getChildren().addAll(arriba,abajo,botonHBOX);
		contenedorHBox.setAlignment(Pos.CENTER);
		
		
		//ROOT//
		root = new BorderPane();
		root.setCenter(contenedorHBox);
		
		
		//MOSTRAMOS TODO//
		Scene scene = new Scene(root,320,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.show();

	}

	protected void onCambiarAction(ActionEvent event) {

		Double divisa;
		Double divisa_cambiada;
		
		try {
			divisa = Double.parseDouble(arribaText.getText());
			Divisa origen = arribaCombo.getSelectionModel().getSelectedItem();
			Divisa destino = abajoCombo.getSelectionModel().getSelectedItem();
			divisa_cambiada = destino.fromEuro(origen.toEuro(divisa));
			
			abajoText.setText(divisa_cambiada.toString());
			
		}catch (NumberFormatException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.initOwner(primaryStage);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("DEBE INTRODUCIR UN NUMERO");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
		
		
		//abajoText.setText(value);
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	

}
