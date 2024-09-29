package com.example.model;


public class Data {
    private String nom;
    private String pnom;
    private String matricule;
    private String date_naissance;
    private String ecole;
    private String statut_examen;
    private Double moyenne;

    public Data() {
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPnom() {
        return pnom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getEcole() {
        return ecole;
    }

    public Boolean getStatut_examen() {
        return statut_examen.equals("Admis(e)");
    }

    public Double getMoyenne() {
        return moyenne;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPnom(String pnom) {
        this.pnom = pnom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public void setStatut_examen(Boolean statut_examen) {
        this.statut_examen = statut_examen ? "Admis(e)" : "Refusé(e)";
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }
}
