/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.Set;

/**
 *
 * @author stanislasallouche
 */

public class Barre {
    
    private int id;
    private Noeud debut;
    private Noeud fin;
    private Treillis compose;
    
    public Barre(Noeud debut , Noeud fin) {
        this.debut= debut;
        this.fin=fin;
        //this.id = getOrCreateId (this);
    }
    
    public Barre() {
        this.debut= new Noeud();
        this.fin= new Noeud();
        //this.id = getOrCreateId (this);
    }

    public void setDebut(Noeud debut) {
        this.debut = debut;
    }

    public void setFin(Noeud fin) {
        this.fin = fin;
    }
    
    public Noeud getDebut() {
        return this.debut;
    }

    public Noeud getFin() {
        return this.fin;
    }

    public void setCompose(Treillis compose) {
        this.compose = compose;
    }

    public Treillis getCompose() {
        return compose;
    }
    
    public Barre demandeBarre(){
        Noeud noeudD = new Noeud();
        Noeud noeudF = new Noeud();
        System.out.println("noeud de début : ");
        noeudD =noeudD.demandeNoeud();
        System.out.println("point fin : ");
        noeudF = noeudF.demandeNoeud();
        return new Barre(noeudD, noeudF);
    }
    
}
