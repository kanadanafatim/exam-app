package com.something.controllers.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.something.controllers.BaseController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class ScreenController extends BaseController implements Initializable {

	@FXML
	private Label labelPct;
	
	@FXML
	private Label moyenneEt;

	@FXML
	private TextField champMatricule;

	@FXML
	private Label statutResultat;
	
	@FXML
	private AnchorPane ecranAccueil;
	
	@FXML
	private AnchorPane ecranRecherche;
	
	@FXML
	private AnchorPane conteneurApresRecherche;
	
	@FXML
	private AnchorPane ecranDetails;

	
	List<AnchorPane> listEcrans = List.of(
			ecranAccueil, ecranRecherche, ecranDetails
					);
	
	@FXML
	private void afficherPageRecherche() {
		navigate(ecranRecherche, listEcrans);
	}
	
	@FXML
	private void afficherPageAccueil() {
		navigate(ecranAccueil, listEcrans);
	}
	
	@FXML
	private void afficherPageDetails() {
		navigate(ecranDetails, listEcrans);
	}
	
	
	// Actions
	@FXML
	private void rechercherResultat() {
		if (champMatricule.getText() == null || champMatricule.getText().isEmpty()) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Avertissement");
			alert.setContentText("Veuillez renseigner votre matricule");
		} else {
			try {
				Integer.valueOf(champMatricule.getText());
				String matricule = champMatricule.getText();

				if (getResult(matricule)) {
					afficherConteneurApresRecherche();
				}
			} catch (NumberFormatException e) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setContentText("Vous devez renseigner que des nombres");
			}
		}

		if (alert != null) {
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fetch();

		labelPct.setText(df.format(rate) + " %");
		conteneurApresRecherche.setVisible(false);
		
		setTextColor(labelPct);
		setTextColor(moyenneEt);
		setTextColor(statutResultat);
	}

	private void setTextColor(Label label) {
		if (Double.parseDouble(label.getText()) >= 50) {
			label.setTextFill(Color.valueOf("4caf50"));
		} else {
			label.setTextFill(Color.valueOf("c70000"));
		}
	}
	
	private void afficherConteneurApresRecherche() {
		if (data != null) {
			statutResultat.setText(data.getStatut_examen());
			conteneurApresRecherche.setVisible(true);
		}
	}
}
