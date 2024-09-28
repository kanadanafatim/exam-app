package com.something.controllers.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.something.controllers.BaseController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class ScreenController extends BaseController implements Initializable {

	@FXML
	private Label labelPct;
	
	@FXML
	private Label moyenneEt;
	
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
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fetch();

		labelPct.setText(df.format(rate) + " %");
		
		setTextColor(labelPct);
	}

	private void setTextColor(Label label) {
		if (Double.parseDouble(label.getText()) >= 50) {
			label.setTextFill(Color.valueOf("4caf50"));
		} else {
			label.setTextFill(Color.valueOf("c70000"));
		}
	}
}
