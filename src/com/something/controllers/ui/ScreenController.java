package com.something.controllers.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.something.controllers.BaseController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TextField champMatricule;

	@FXML
	private Label statutResultat;
	
	@FXML
	private Label etudiantNomPrenoms;
	
	@FXML
	private Label etudiantMatricule;
	
	@FXML
	private Label etudiantDateNaissance;
	
	@FXML
	private Label etudiantEcole;

	@FXML
	private Label moyenneEt;
	
	@FXML
	private AnchorPane ecranAccueil;
	
	@FXML
	private AnchorPane ecranRecherche;
	
	@FXML
	private AnchorPane conteneurApresRecherche;
	
	@FXML
	private AnchorPane ecranDetails;

	
	ObservableList<AnchorPane> listEcrans;

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
		etudiantNomPrenoms.setText(String.format("%s %s", data.getNom(), data.getPnom()));
		etudiantDateNaissance.setText(data.getDate_naissance());
		etudiantMatricule.setText(data.getMatricule());
		etudiantEcole.setText(data.getEcole());
		moyenneEt.setText(String.valueOf(data.getMoyenne()));
		
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
					setTextColor(moyenneEt, data.getMoyenne(), true);
					setTextColor(statutResultat, data.getStatut_examen(), false);

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
		
		listEcrans = FXCollections.observableArrayList(
			ecranAccueil, ecranRecherche, ecranDetails
		);

		labelPct.setText(df.format(rate) + " %");
		
		setTextColor(labelPct, rate, false);
	}

	private <T> void setTextColor(Label label, T value, Boolean isAvg) {
		if (value instanceof Double && !isAvg) {
			if ((Double) value >= 50) {
				label.setTextFill(Color.valueOf("#4caf50"));
			} else {
				label.setTextFill(Color.valueOf("#c70000"));
			}
		} else if (value instanceof Double && isAvg) {
			if ((Double) value >= 10) {
				label.setTextFill(Color.valueOf("#4caf50"));
			} else {
				label.setTextFill(Color.valueOf("#c70000"));
			}
		} else if (value instanceof Boolean) {
			if ((Boolean) value == true) {
				label.setTextFill(Color.valueOf("#4caf50"));
			} else {
				label.setTextFill(Color.valueOf("#c70000"));
			}
		}
	}

	private void afficherConteneurApresRecherche() {
		if (data != null) {
			statutResultat.setText(data.getStatut_examen() ? "Admis(e)" : "Refusé(e)");
			conteneurApresRecherche.setVisible(true);
		}
	}
}
