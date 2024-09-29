package com.example.tests;

import org.junit.jupiter.api.Test;

import com.example.controllers.BaseController;
import com.example.model.Data;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


public class ExamAppSpec extends BaseController {

	// JUnit
	// Vérifie la méthode 'getResult' pour un matricule correct
    @Test
    public void testGetEtudiantByMatricule_ValidMatricule() {
        Data etudiant = getResult("0072493");

        assertNotNull(etudiant, "L'étudiant doit exister dans la base de données.");
        assertEquals("YEO", etudiant.getNom(), "Le nom doit être YEO.");
    }


    // Vérifie la méthode 'getResult' pour un matricule incorrect
    @Test
    public void testGetEtudiantByMatricule_InvalidMatricule() {
    	Data etudiant = getResult("99999999");

        assertNull(etudiant, "L'étudiant avec un matricule inexistant doit retourner null.");
    }


    // Vérifie si la méthode 'getResult' retourne la moyenne du candidat
    @Test
    public void testGetEtudiantByMatricule_Moyenne() {
        Data etudiant = getResult("0072493");

        assertEquals(14, etudiant.getMoyenne(), "La moyenne doit être 14.");
    }


    // Vérifie si la méthode 'getResult' retourne la date de naissance du candidat
    @Test
    public void testGetEtudiantByMatricule_DateNaissance() {
    	Data etudiant = getResult("0072902");

        assertEquals("2003-05-09", etudiant.getDate_naissance(), "La date de naissance doit être 09/05/2003.");
    }


    // Vérifie si la méthode 'getResult' retourne l'école du candidat
    @Test
    public void testGetEtudiantByMatricule_NomEcole() {
        Data etudiant = getResult("0071114");

        assertEquals("AGITEL", etudiant.getEcole(), "L'école doit être AGITEL.");
    }


    // Tests - AssertJ
    @Test
    public void testGetEtudiantByMatricule_ValidMatricule_AssertJ() {
    	Data etudiant = getResult("0072493");

        assertThat(etudiant).isNotNull().as("L'étudiant doit exister dans la base de données.");
        assertThat(etudiant.getNom()).isEqualTo("YEO").as("Le nom doit être YEO.");
    }

    @Test
    public void testGetEtudiantByMatricule_InvalidMatricule_AssertJ() {
    	Data etudiant = getResult("99999999");
        assertThat(etudiant).isEqualTo(null).as("L'étudiant avec un matricule inexistant doit retourner null.");
    }

    @Test
    public void testGetEtudiantByMatricule_Moyenne_AssertJ() {
    	Data etudiant = getResult("0072493");
        assertThat(etudiant.getMoyenne()).isEqualTo(14).as("La moyenne doit être 14.");
    }

    @Test
    public void testGetEtudiantByMatricule_DateNaissance_AssertJ() {
    	Data etudiant = getResult("0072493");
        assertThat(etudiant.getDate_naissance().toString()).isEqualTo("2003-05-23").as("La date de naissance doit être 23/05/2003.");
    }

    @Test
    public void testGetEtudiantByMatricule_NomEcole_AssertJ() {
    	Data etudiant = getResult("0072493");
        assertThat(etudiant.getEcole()).isEqualTo("PIGIER CI").as("L'école doit être PIGIER CI.");
    }
}
