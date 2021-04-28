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

    public AppuiDouble(Point noeud, SegmentTerrain st) {
        super(noeud, st);
    }
    
    public AppuiDouble(double alpha, SegmentTerrain segT) {
        super(alpha, segT);
    }

    public AppuiDouble demandeNoeudAppui() {
        AppuiDouble na = new AppuiDouble(demandeNoeud().getPosition(), SegmentTerrain.demandeSegmentTerain());
        return na;
    }
    public String toString() {
        String res = "NoeudAppuiDouble " + this.getId() + " [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]\nAlpha = "+this.getAlpha();
        return res;
    }
}
