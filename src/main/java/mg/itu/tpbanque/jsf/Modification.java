/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanque.jsf;

import mg.itu.tpbanque.service.GestionnaireCompte;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanque.entity.CompteBancaire;
import mg.itu.tpbanque.jsf.util.Util;

/**
 * Backing bean de la page Transfert d'Argent
 *
 * @author Fanilo
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String ancienNom;

    @Inject
    private GestionnaireCompte gestionnaireCompte;


    public String getAncienNom() {
        return ancienNom;
    }

    public void setAncienNom(String ancienNom) {
        this.ancienNom = ancienNom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        ancienNom = compte.getNom();
    }

    public String enregistrerModification() {
        if (ancienNom != null && compte.getNom().compareTo(ancienNom) != 0) {
            compte = gestionnaireCompte.update(compte);
            Util.addFlashInfoMessage("Nom " + ancienNom +" changé en " + compte.getNom());
            return "listeComptes?faces-redirect=true";
        }else {
            Util.messageErreur("le Nom doit etre modifier !", "le Nom doit etre modifier !", "form:nom");
            return null;
        }
    }

}
