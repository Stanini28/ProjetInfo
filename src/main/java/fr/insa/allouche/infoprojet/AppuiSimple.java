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
    
    public AppuiSimple(SegmentTerrain segT){
        super(0, segT);
    }

    private double dTangentielle;
    
    public AppuiSimple demandeAppuiSimpl(){
        AppuiSimple na = new AppuiSimple(demandeNoeud().getPosition(), SegmentTerrain.demandeSegmentTerain());
        return na;
    }
    public String toString() {
        String res = "NoeudAppuiSimple " + this.getId() + " [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]\nAlpha = "+this.getAlpha();
        return res;
    }

}
