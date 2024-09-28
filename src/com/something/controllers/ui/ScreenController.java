package com.something.controllers.ui;

import java.net.URL;
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

    // Champs annotés FXML qui correspondent aux éléments de l'interface utilisateur dans le fichier FXML
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

    // Liste observable d'AnchorPane pour l'écran d'accueil, l'écran de recherche et l'écran de détails
    ObservableList<AnchorPane> listEcrans;

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Recherche" dans l'écran d'accueil
    @FXML
    private void afficherPageRecherche() {
        navigate(ecranRecherche, listEcrans);
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Accueil" dans l'écran de recherche
    @FXML
    private void afficherPageAccueil() {
        navigate(ecranAccueil, listEcrans);
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Détails" dans l'écran de recherche
    @FXML
    private void afficherPageDetails() {
        // Définit le texte des étiquettes dans l'écran de détails pour afficher les données de l'étudiant
        etudiantNomPrenoms.setText(String.format("%s %s", data.getNom(), data.getPnom()));
        etudiantDateNaissance.setText(data.getDate_naissance());
        etudiantMatricule.setText(data.getMatricule());
        etudiantEcole.setText(data.getEcole());
        moyenneEt.setText(String.valueOf(data.getMoyenne()));

        // Navigue vers l'écran de détails
        navigate(ecranDetails, listEcrans);
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Rechercher" dans l'écran de recherche
    @FXML
    private void rechercherResultat() {
        // Vérifie si le numéro de matricule entré par l'utilisateur est valide
        if (champMatricule.getText() == null || champMatricule.getText().isEmpty()) {
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setContentText("Veuillez renseigner votre matricule");
        } else {
            try {
                Integer.valueOf(champMatricule.getText());
                String matricule = champMatricule.getText();

                // Récupère les données de résultat de l'étudiant en utilisant la méthode getResult()
                if (getResult(matricule)) {
                    // Définit la couleur du texte des étiquettes en fonction de la moyenne de l'étudiant et de son statut d'examen
                    setTextColor(moyenneEt, data.getMoyenne(), true);
                    setTextColor(statutResultat, data.getStatut_examen(), false);

                    // Affiche les données de l'étudiant dans l'écran de détails
                    afficherConteneurApresRecherche();
                }
            } catch (NumberFormatException e) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez renseigner que des nombres");
            }
        }

        // Affiche l'alerte s'il y a une erreur ou un avertissement
        if (alert != null) {
            alert.showAndWait();
        }
    }

    // Méthode appelée après que le fichier FXML a été chargé
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Récupère les données nécessaires à l'application
        fetch();

        // Initialise la liste observable d'AnchorPane
        listEcrans = FXCollections.observableArrayList(
                ecranAccueil, ecranRecherche, ecranDetails
        );

        // Définit le texte de l'étiquette labelPct au taux de réussite, formaté en pourcentage
        labelPct.setText(df.format(rate) + " %");

        // Définit la couleur du texte de l'étiquette labelPct en fonction du taux de réussite
        setTextColor(labelPct, rate, false);
    }

    // Méthode générique qui définit la couleur du texte d'une étiquette en fonction de la valeur d'un objet donné
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

    // Méthode qui définit la visibilité de l'AnchorPane conteneurApresRecherche à true
    // et définit le texte de l'étiquette statutResultat au statut d'admission de l'étudiant
    private void afficherConteneurApresRecherche() {
        if (data != null) {
            statutResultat.setText(data.getStatut_examen() ? "Admis(e)" : "Refusé(e)");
            conteneurApresRecherche.setVisible(true);
        }
    }
}
