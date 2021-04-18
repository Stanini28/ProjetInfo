/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.ArrayList;
import java.util.List;



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
    
    
    
    public void add(NoeudAppui nA) {
        if (nA.getSegmentTerrain() != this) {
            if (nA.getSegmentTerrain() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.appartient.add(nA);
            nA.setSegmentTerrain(this);
        }
    }
    
    public void remove(NoeudAppui nA) {
        if (nA.getSegmentTerrain() != this) {
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
    
    public static void pCaract(Point P, Point Q, Point R) {
        double angle = Math.atan2(P.getPX() - Q.getPY(), P.getPX() - Q.getPX()) - Math.atan2(P.getPY() - R.getPY(), P.getPX() - R.getPX());

        angle = angle * Math.PI / 180;

        if (angle == 0) {
            System.out.println("Colinéaire");
        } else {
            if (0 < angle && angle < Math.PI) {
                System.out.println("Positif");
            } else {
                System.out.println("Négatif");

            }

        }

    }

}
