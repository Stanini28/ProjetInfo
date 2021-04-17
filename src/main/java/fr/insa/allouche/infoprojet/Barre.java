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
    
    
    public Barre(Noeud debut , Noeud fin) {
        this.debut= debut;
        this.fin=fin;
    }
    
    public Noeud getDebut() {
        return this.debut;
    }

    public Noeud getFin() {
        return this.fin;
    }
    
    
}
