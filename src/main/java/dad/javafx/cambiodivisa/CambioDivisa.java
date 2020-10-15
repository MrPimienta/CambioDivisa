package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application{
	
	//creamos los dos textfield
	private TextField tx_divisa_1 = new TextField();
	private TextField tx_divisa_2 = new TextField();
	
	//creamos los combobox
	ComboBox<Divisa> cb_divisa_1 = new ComboBox<Divisa>();
	ComboBox<Divisa> cb_divisa_2 = new ComboBox<Divisa>();
	
	
	//creamos los objetos Divisa
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra Esterlina", 0.91);
	Divisa yen = new Divisa("Yen", 123.36);
	Divisa dolar = new Divisa("Dólar Estadounidense", 1.17);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//creamos el boton Cambiar
		Button bt_cambiar  = new Button();
		bt_cambiar.setText("Cambiar");
		bt_cambiar.setAlignment(Pos.CENTER);
		bt_cambiar.setLayoutX(20);
		bt_cambiar.setLayoutY(80);
		
		//textfield
		tx_divisa_1.setAlignment(Pos.CENTER);
		tx_divisa_2.setAlignment(Pos.CENTER);
		
		tx_divisa_1.setMaxWidth(50);
		
		tx_divisa_2.setMaxWidth(50);
		tx_divisa_2.setEditable(false);
		
		
		//COMBOBOX
		cb_divisa_1.getItems().addAll(euro,libra,yen,dolar);
		cb_divisa_2.getItems().addAll(euro,libra,yen,dolar);
		
		HBox hb_moneda = new HBox();
		HBox hb_cambio = new HBox();
		
		hb_moneda.setSpacing(1);
		hb_cambio.setSpacing(1);
		
		hb_moneda.setAlignment(Pos.BASELINE_CENTER);
		hb_cambio.setAlignment(Pos.BASELINE_CENTER);
		
		hb_moneda.getChildren().addAll(tx_divisa_1, cb_divisa_1);
		hb_cambio.getChildren().addAll(tx_divisa_2, cb_divisa_2);
		
		bt_cambiar.setOnAction( event -> {
			onCambiarDivisa(event);
		});
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(hb_moneda,hb_cambio,bt_cambiar);
		
		Scene escena = new Scene(root,320,300);
		primaryStage.setScene(escena);
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main (String[] ar) {
		launch(ar);
	}
	
	public void onCambiarDivisa(ActionEvent e) {
		
		try {
			
			Double origen = Double.parseDouble(tx_divisa_1.getText());
			Divisa divisa_origen = cb_divisa_1.getSelectionModel().getSelectedItem();
			Divisa divisa_destino = cb_divisa_2.getSelectionModel().getSelectedItem();
			Double resultado = divisa_destino.fromEuro(divisa_origen.toEuro(origen));
			tx_divisa_2.setText(resultado.toString());
			
		}catch (NumberFormatException err) {
			Alert alerta_error = new Alert(AlertType.ERROR);
			alerta_error.setTitle("Cambio de Divisa");
			alerta_error.setHeaderText("ERROR");
			alerta_error.setContentText("No se ha introducido ningún número, o el caracter introducido no es válido.");
			alerta_error.showAndWait();
		}
	}
	
}
