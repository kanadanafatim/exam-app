Prototype de l’application
*Page d’accueil 
L’utilisateur voit le pourcentage des admis puis il clique sur le bouton "Voir mon résultat". 

*Page de recherche 
Il entre son matricule et il clique sur le bouton "Rechercher".

*Page de résultat
En fonction du matricule entré, l’application affiche ADMIS(E) si la moyenne est supérieure ou égale à 10 ou bien REFUSE(E) si elle est inférieure à 10 
puis il clique sur le bouton "Afficher les détails" pour voir plus d’informations.

Page de détails 
Lorsqu’il clique sur "Afficher les détails" l’application affiche : 
-	Le matricule entré
-	Le nom et les prénoms correspondant
-	La date de naissance 
-	Le nom de l’école 
-	La moyenne obtenue sur 20
-	Un bouton retour (Page de résultat)

  
Prototype commenté de la base de données
Nom de la base de données : db0072493
TABLES :
•	Etudiant 
-	matricule : stocke le matricule de l’étudiant.
-	nom : stocke le nom de l’étudiant.
-	prenoms : stocke les prenoms de l’étudiant.
-	date_naissance : stocke la date de naissance de l’étudiant.
-	ecole : stocke le nom de l’école de l’étudiant.

•	resultat 
-	Idresultat : identifiant unique des résultats
-	Matricule_etudiant : pour faire reférence a la table Etudiant.
-	moyenne : stocke la moyenne obtenue à l’examen sur 20. 
-	statut : stocke le statut de l’étudiant, il indique s’il a réussi ou échoué à l’examen. 
