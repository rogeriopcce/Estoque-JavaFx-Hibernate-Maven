package clipper.errorsys;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {

	public static void showAlerta(String titulo, String cabecalho, String texto, AlertType tipo) {

		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(texto);

		alert.show();

	}

}
