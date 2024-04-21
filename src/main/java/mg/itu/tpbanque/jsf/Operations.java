/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanque.entity.CompteBancaire;
import mg.itu.tpbanque.service.GestionnaireCompte;

/**
 * Backing bean de la page des Operations
 *
 * @author Fanilo
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private long idCompte;
    private CompteBancaire compteBancaire;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void loadCompteBancaire() {
        this.compteBancaire = gestionnaireCompte.findById(idCompte);
    }

    /**
     * Creates a new instance of DetailsCompte
     */
    public Operations() {
        //if(compteBancaire == null){
        //    compteBancaire = new CompteBancaire();
        //}
    }

}
