/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author stanislasallouche
 */

public class AppuiDouble extends NoeudAppui {

    public static double RAYON_NOEUD = 5;
    
    private double Tangent;
    private double Normal;
    private Reaction_Rx Rx;
    private Reaction_Ry Ry;
    

    public AppuiDouble(Point noeud, SegmentTerrain segT) {
        super(noeud, segT, Color.CORAL);
        segT.getAppartient().add(this);
    }
    
    public AppuiDouble(double alpha, SegmentTerrain segT) {
        super(alpha, segT, Color.CORAL);
        //segT.add(this);
         segT.getAppartient().add(this);
    }
    public AppuiDouble(double alpha, SegmentTerrain segT, TriangleTerrain tt) {
        super(alpha, segT, Color.CORAL, tt);
        //segT.add(this);
         segT.getAppartient().add(this);
    }
    
    public AppuiDouble(Point noeud, SegmentTerrain segT, Color color) {
        super(noeud, segT, color);
        //segT.add(this);
         segT.getAppartient().add(this);
    }
    
    public AppuiDouble(double alpha, SegmentTerrain segT, Color color) {
        super(alpha, segT, color);
        //segT.add(this);
         segT.getAppartient().add(this);
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
    
    public void dessine(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillOval(this.position.getPX() - RAYON_NOEUD, this.position.getPY()- RAYON_NOEUD, 2*RAYON_NOEUD, 2*RAYON_NOEUD);
    }
    public void dessineSelect(GraphicsContext context) {
        context.setFill(this.getColorSelect());
        context.fillOval(this.position.getPX() - RAYON_NOEUD, this.position.getPY()- RAYON_NOEUD, 2*RAYON_NOEUD, 2*RAYON_NOEUD);
    }

    public Reaction_Rx getRx() {
        return Rx;
    }

    public Reaction_Ry getRy() {
        return Ry;
    }
    
    public void save(Writer w, Identificateur num) throws IOException {
        if (!num.objExist(this)) {
            w.append("AppuiDouble;" + this.getId()+";"+ this.getappartient().getFaitPartieDe().getId()+ ";"+ this.getJ()+";"+ this.getAlpha()+";" + this.forceY 
                     +"\n");
        }
    }
}
