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
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private long idCompteSource;
    private CompteBancaire compteBancaireSource;
    private long idCompteDestination;
    private CompteBancaire compteBancaireDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public void setIdCompteSource(long idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public void setIdCompteDestination(long idCompteDestination) {
        this.idCompteDestination = idCompteDestination;
    }

    public long getIdCompteSource() {
        return idCompteSource;
    }

    public long getIdCompteDestination() {
        return idCompteDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {
        boolean erreur = false;
        this.compteBancaireSource = gestionnaireCompte.findById(idCompteSource);
        if (compteBancaireSource == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:idCompteSource");
            erreur = true;
        } else {
            if (compteBancaireSource.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Solde du compte source est insufisant !", "Solde du compte source est insufisant !", "form:idCompteSource");
                erreur = true;
            }
        }
        this.compteBancaireDestination = gestionnaireCompte.findById(idCompteDestination);
        if (compteBancaireDestination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:idCompteDestination");
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.transferer(compteBancaireSource, compteBancaireDestination, montant);
        Util.addFlashInfoMessage("Transfert de " + montant + " du compte de " + compteBancaireSource.getNom() + " vers le compte de " + compteBancaireDestination.getNom());
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

}
