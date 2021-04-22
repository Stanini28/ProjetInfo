/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.ArrayList;
import java.util.List;


public class Terrain {

    private Point min;
    private Point max;
    private List<TriangleTerrain> constitue;
    private Treillis base;
    
    public Terrain(double xmin, double xmax) {
        this.min.setPX(min.getPX());
        this.min.setPY(min.getPY());
        this.max.setPX(max.getPX());
        this.max.setPY(max.getPY());
        this.constitue = new ArrayList();
    }

    public Treillis getBase() {
        return base;
    }

    public void setBase(Treillis base) {
        this.base = base;
    }
    
    
    
    public void add(TriangleTerrain tT) {
        if (tT.getConstitue() != this) {
            if (tT.getConstitue() != null) {
                throw new Error("Le Triangle Terrain appartient déjà à un autre SegmentTerrain");
            }
            this.constitue.add(tT);
            tT.setConstitue(this);
        }
    }
    
    public void remove(TriangleTerrain tT) {
        if (tT.getConstitue() != this) {
            throw new Error("Le Triangle Terrain n'appartient pas au terrain");
        }
        this.constitue.remove(tT);
        tT.setConstitue(null);
    }
    
    public void removeAll(List<TriangleTerrain> lTT) {
        //pas sur d'avoir compris comment fonctionne le for
        for(TriangleTerrain tT : lTT) {
            this.remove(tT);
        }
    }
    
    public String toString(){
        String res="";
        res = res + ("Terrain : xmin = "+this.min.getPY()+"xmax = "+this.min.getPY()+"ymin = "+this.min.getPY()+"ymax = ");
        
        return res;
    }
    
}
