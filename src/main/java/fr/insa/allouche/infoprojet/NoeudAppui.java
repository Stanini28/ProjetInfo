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
public class NoeudAppui extends Noeud {
    
    private double alpha; 
    private SegmentTerrain appartient;
    private Point coord;
    
    public SegmentTerrain getappartient(){
        return appartient;
    }
    
    void setSegmentTerrain (SegmentTerrain appartient) {
        this.appartient = appartient;
    }
    
    public NoeudAppui(Noeud noeud) {
        super(noeud.getPosition());
    }
    
    public void positionP() {

    }
    
    public NoeudAppui demandeNoeudAppui(){
        NoeudAppui na = new NoeudAppui(demandeNoeud());
        return na;
    }
}
