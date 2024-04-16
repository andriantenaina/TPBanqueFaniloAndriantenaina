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
 * Backing bean de la page ListeComptes
 *
 * @author Fanilo
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteBancaireList;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Retourne la liste des clients pour affichage dans une DataTable.
     */
    public List<CompteBancaire> getAllComptes() {
        if (compteBancaireList == null) {
            compteBancaireList = gestionnaireCompte.getAllComptes();
        }
        return compteBancaireList;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

}
