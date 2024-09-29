package com.example.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

import com.example.model.Data;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class BaseController {

    // Détails de la connexion à la base de données
    private String uri = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0072493";
    private String user = "user0072493";
    private String password = "Yf3IgyBsOPa34WR";

    // Variables pour stocker le taux de réussite et le format décimal
    protected Double rate = 0.0;
    protected DecimalFormat df = new DecimalFormat("#.##");

    // Objets Alert et Data
    protected Alert alert;
    protected Data data;
    protected AnchorPane recentPage;
    protected Boolean noDataFoundError = false;
    protected Boolean sqlError = false;

    // Méthode pour établir une connexion à la base de données
    protected Connection connection() {
        Connection connexion = null;

        try {
            connexion = DriverManager.getConnection(uri, user, password);
            // System.out.println("Connexion établie.");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la connexion. Contenu: " + e.getMessage());
        }

        return connexion;
    }

    // Méthode pour récupérer les données de la base de données et calculer le taux de réussite
    protected void fetch() {
        Double total = 0.0;
        int totalSuccess = 0;
        Connection connection = connection();

        if (connection != null) {
            String query = "SELECT * FROM resultat";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Boolean statut = rs.getBoolean("statut");

                    total++;
                    if (statut) totalSuccess++;
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }
        }

        rate = (totalSuccess / total) * 100;
    }

    // Méthode pour récupérer les données de résultat de l'étudiant en fonction du numéro de matricule
    protected Data getResult(String matricule) {
        try {
        	Integer.parseInt(matricule);
        	
        	Connection connection = connection();

            if (connection != null) {
                String query = "SELECT * FROM resultat INNER JOIN etudiant "
                        + "WHERE resultat.matricule_etudiant = etudiant.matricule "
                        + "AND resultat.matricule_etudiant = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, matricule);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        data = new Data();
                        data.setNom(rs.getString("nom"));
                        data.setPnom(rs.getString("prenoms"));
                        data.setMatricule(rs.getString("matricule"));
                        data.setDate_naissance(rs.getString("date_naissance"));
                        data.setEcole(rs.getString("ecole"));
                        data.setMoyenne(rs.getDouble("moyenne"));
                        data.setStatut_examen(rs.getBoolean("statut"));
                    } else {
                    	noDataFoundError = true;
                    }
                } catch (SQLException e) {
                	sqlError = true;
                	System.err.println(e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
        	// do nothing
        }

        return data;
    }

    // Méthode pour naviguer entre différents écrans de l'application
    protected void navigate(AnchorPane ecran, List<AnchorPane> listEc) {
        for (AnchorPane item : listEc) {
            item.setVisible(false);
        }

        ecran.setVisible(true);
    }
}
