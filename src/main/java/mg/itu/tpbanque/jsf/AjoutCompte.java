/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanque.entity.CompteBancaire;
import mg.itu.tpbanque.jsf.util.Util;
import mg.itu.tpbanque.service.GestionnaireCompte;

/**
 * Backing bean de la page Transfert d'Argent
 *
 * @author Fanilo
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private CompteBancaire compteBancaire;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public String action() {
        boolean erreur = false;
        if (compteBancaire.getSolde() < 0) {
            Util.messageErreur("Solde Doit etre positf !", "Solde Doit etre positf !", "form:");
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.creerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de "+ compteBancaire.getNom() +" cree avec succe");
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public AjoutCompte() {
        if(compteBancaire == null){
            compteBancaire = new CompteBancaire();
        }
    }

}
