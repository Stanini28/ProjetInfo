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
public class AppuiSimple extends NoeudAppui {
    
    public AppuiSimple(Point noeud, SegmentTerrain st) {
        super(noeud, st);
    }
    
    public AppuiSimple(double alpha, SegmentTerrain segT) {
        super(alpha, segT);
    }

    private double dTangentielle;
    
    public NoeudAppui demandeNoeudAppui(){
        NoeudAppui na = new NoeudAppui(demandeNoeud().getPosition(), SegmentTerrain.demandeSegmentTerain());
        return na;
    }
    public String toString() {
        String res = "NoeudAppuiSimple " + this.getId() + " [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]\nAlpha = "+this.getAlpha();
        return res;
    }
}
