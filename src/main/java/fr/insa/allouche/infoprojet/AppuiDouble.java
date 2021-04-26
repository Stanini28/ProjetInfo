/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

/**
 *
 * @author stanislasallouche
 */
public class AppuiDouble extends NoeudAppui {

    private double Tangent;
    private double Normal;

    public AppuiDouble(Noeud noeud) {
        super(noeud);
    }

    public NoeudAppui demandeNoeudAppui() {
        NoeudAppui na = new NoeudAppui(demandeNoeud());
        return na;
    }
}
