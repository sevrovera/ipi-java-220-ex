package com.ipiecoles.java.java220;

import java.util.Objects;

import org.joda.time.LocalDate;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe {
	
	public Employe() {
	}

	private String nom;
	private String prenom;
	protected String matricule;
	private LocalDate dateEmbauche;
	protected Double salaire;

	
	public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}
	
	public void setDateEmbauche(LocalDate dateEmbauche) {
		if (dateEmbauche == null) {
			System.out.println("La date d'embauche ne peut pas être nulle");
		}
		else if (!LocalDate.now().isBefore(dateEmbauche)) {
			this.dateEmbauche = dateEmbauche;
		} else {
			System.out.println("La date d'embauche ne peut être postérieure à la date courante");
		}
		/* Idéalement il faudrait lancer une exception à ce stade */
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}
	
	public final Integer getNombreAnneeAnciennete() { /* final signifie que la méthode ... */
		Integer anneeEnCours = LocalDate.now().getYear();
		if (dateEmbauche != null) {
			Integer anciennete = anneeEnCours - this.dateEmbauche.getYear();
			return anciennete;
		} else {
			return 0;
		}
	}

	public Integer getNbConges() {
		Entreprise e = new Entreprise();
		return e.getNbCongesBase();
		/* Bcp plus simple : return Entreprise.NB_CONGES.BASE */
		/* On aurait pu déclarer la méthode comme static car elle ne dépend pas des attributs de la classe */ 
	}
	

	@Override
	public String toString() {
		return "Employe{nom='" + nom + "', prenom='" + prenom + "', matricule='" + matricule + "', dateEmbauche="
				+ dateEmbauche + ", salaire=" + salaire + "}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire);
	}

	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
		
		/*
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employe other = (Employe) obj;
		return Objects.equals(dateEmbauche, other.dateEmbauche) && Objects.equals(matricule, other.matricule)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(salaire, other.salaire); */
	}

	public void augmenterSalaire(Double p_aug) {
		this.salaire = this.salaire * (1+ p_aug);
		/* Autre option, pour l'instant équivalente car pas d'héritage en place :
		 setSalaire(this.salaire * (1+ p_aug)) 
		 ou
		 setSalaire(getSalaire() * (1+ p_aug))
		 */
	}
	
	public abstract Double getPrimeAnnuelle();

	
}

