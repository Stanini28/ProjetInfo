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
public class AppuiSimple extends NoeudAppui {
    
    public static double RAYON_NOEUD = 5;
    private Reaction_Rx Rx;
    
    public AppuiSimple(Point noeud, SegmentTerrain st) {
        super(noeud, st, Color.CHOCOLATE);
        st.getAppartient().add(this);
    }
    
    public AppuiSimple(double alpha, SegmentTerrain segT) {
        super(alpha, segT, Color.CHOCOLATE);
        //segT.add(this);
        segT.getAppartient().add(this);
    }
    public AppuiSimple(double alpha, SegmentTerrain segT, TriangleTerrain tt) {
        super(alpha, segT, Color.CHOCOLATE, tt);
        //segT.add(this);
        segT.getAppartient().add(this);
    }
    
    public AppuiSimple(SegmentTerrain segT){
        super(0, segT, Color.CHOCOLATE);
        //segT.add(this);
        segT.getAppartient().add(this);
    }
    
    public AppuiSimple(Point noeud, SegmentTerrain st, Color color) {
        super(noeud, st, color);
        //st.add(this);
        st.getAppartient().add(this);
    }
    
    public AppuiSimple(double alpha, SegmentTerrain segT, Color color) {
        super(alpha, segT, color);
        //segT.add(this);
         segT.getAppartient().add(this);
    }
    
    public AppuiSimple(SegmentTerrain segT, Color color){
        super(0, segT, color);
        //segT.add(this);
        segT.getAppartient().add(this);
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

    public void save(Writer w, Identificateur num) throws IOException {
        if (!num.objExist(this)) {
            w.append("AppuiSimple;" + this.getId()+";" + this.getappartient().getFaitPartieDe().getId()+ ";"+ this.getJ()+";"+ this.getAlpha()+";" + this.forceY 
                     +"\n");
        }
    }
    
}
