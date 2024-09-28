package com.something.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

import javafx.scene.layout.AnchorPane;

public class BaseController {
	
	private String uri = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0072493";
	private String user = "user0072493";
	private String password = "Yf3IgyBsOPa34WR";
	protected Double rate = 0.0;
	protected DecimalFormat df = new DecimalFormat("#.##");

	
	protected Connection connection() {
		Connection connexion = null;

        try {
            connexion = DriverManager.getConnection(uri, user, password);
            System.out.println("Connexion établie.");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la connexion. Contenu: " + e.getMessage());
        }

        return connexion;
	}

	protected void fetch() {
		Double total = 0.0;
		int totalSuccess = 0;
        Connection connection = connection();

        if (connection != null) {
            String query = "SELECT * FROM resultat";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Boolean statut = rs.getBoolean("statut");

                    total ++;
                    if (statut) totalSuccess ++;
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }
        }
        
        rate = (total / totalSuccess) * 100;
    }
	
	protected void navigate(AnchorPane ecran, List<AnchorPane> listEc) {
		for (AnchorPane item : listEc) {
			item.setVisible(false);
		}
		
		ecran.setVisible(true);
	}
}
