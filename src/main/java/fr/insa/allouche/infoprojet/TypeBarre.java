/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.ArrayList;
import java.util.List;





public class TypeBarre {
    
    private int id;
    private double coutAuMetre;
    private double lMin;
    private double lMax;
    private double rTraction;
    private double rComp;
    private List<Barre> type;
    private Treillis catalogueDeBarre;
    
    public TypeBarre(double coutAuMetre, double lMin, 
            double lMax, double rTraction, double rComp) {
       
       this.coutAuMetre = coutAuMetre;
       this.lMax = lMax;
       this.lMin =lMin;
       this.rComp = rComp;
       this.rTraction = rTraction;
       this.type = new ArrayList<Barre>();
    }
        
    public void add(Barre b) {
        if (b.getType() != this) {
            if (b.getType() != null) {
                throw new Error("La barre appartient déjà à un autre Type de barre");
            }
            this.type.add(b);
            b.setType(this);
        }
    }
    
    public void remove(Barre b) {
        if (b.getType() != this) {
            throw new Error("La barre n'appartient pas à ce Type de barre");
        }
        this.type.remove(b);
        b.setType(null);
    }
    
    public void removeAll(List<Barre> lB) {
        //pas sur d'avoir compris comment fonctionne le for
        for(Barre b : lB) {
            this.type.remove(b);
        }
    }
    
}
