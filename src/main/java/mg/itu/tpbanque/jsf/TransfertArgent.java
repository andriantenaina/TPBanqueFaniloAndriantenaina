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
    
    public void setIdCompteSource(long idCompteSource){
        this.idCompteSource = idCompteSource;
    }
    public void setIdCompteDestination(long idCompteDestination){
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
        this.compteBancaireSource = gestionnaireCompte.findById(idCompteSource);
        this.compteBancaireDestination = gestionnaireCompte.findById(idCompteDestination);
        gestionnaireCompte.transferer(compteBancaireSource, compteBancaireDestination, montant);
        return "listeComptes";
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public TransfertArgent() {
    }

}
