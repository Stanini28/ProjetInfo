/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;



public class SegmentTerrain {
    
    private Point debut;
    private Point fin;
    private List<NoeudAppui> appartient;
    //Est defini dans la classe TriangleTerrain, lorqu'un Triangle terrain est créé
    private TriangleTerrain faitPartieDe;

    public SegmentTerrain(Point posDbt, Point posFin) {
        this.debut= new Point (posDbt.getPX(), posDbt.getPY());
        this.fin= new Point (posFin.getPX(), posFin.getPY());
        this.appartient = new ArrayList<NoeudAppui>();
    }

    public Point getDebut() {
        return debut;
    }

    public Point getFin() {
        return fin;
    }

    public List<NoeudAppui> getAppartient() {
        return appartient;
    }

    public TriangleTerrain getFaitPartieDe() {
        return faitPartieDe;
    }

    void setFaitPartieDe(TriangleTerrain faitPartieDe) {
        this.faitPartieDe = faitPartieDe;
    }

    void setAppartient(List<NoeudAppui> appartient) {
        this.appartient = appartient;
    }
    
    
    
    public void add(NoeudAppui nA) {
        if (nA.getappartient() != this) {
            if (nA.getappartient() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.appartient.add(nA);
            nA.setSegmentTerrain(this);
            nA.calculAlpha();
        }
    }
    
    public void remove(NoeudAppui nA) {
        if (nA.getappartient() != this) {
            throw new Error("Le Noeud appui n'appartient pas à SegmentTerrain");
        }
        this.appartient.remove(nA);
        nA.setSegmentTerrain(null);
    }
    
    public void removeAll(List<NoeudAppui> lNA) {
        //pas sur d'avoir compris comment fonctionne le for
        for(NoeudAppui nA : lNA) {
            this.remove(nA);
        }
    }
    
    // Manque size et clear par rapport à la classe groupe du prof
    
    public String toString(){
        String res="";
        res = "{"+ this.debut.toString()+" ,"+this.fin.toString()+ "}";
        return res;
    }
    
    
    
    public static SegmentTerrain demandeSegmentTerain(){
        System.out.println("Coordonée du premier noeud appui : ");
        Point p1 = Point.demandePoint();
        System.out.println("Coordonée du deuxième noeud appui : ");
        Point p2 = Point.demandePoint();
        SegmentTerrain seg = new SegmentTerrain(p1, p2);
        return seg;
    }
    
    public void dessine(GraphicsContext context) {
        //context.setStroke(this.color);
        context.setLineWidth(1);
        context.strokeLine(this.debut.getPX(), this.debut.getPY(),
                this.fin.getPX(), this.fin.getPY());
    }

}
