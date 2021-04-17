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
    private SegmentTerrain SegmentTerrain;
    
    public SegmentTerrain getSegmentTerrain(){
        return SegmentTerrain;
    }
    
    void setSegmentTerrain (SegmentTerrain segGrp) {
        this.SegmentTerrain = segGrp;
    }
    
    public NoeudAppui() {
    }
    
    public void positionP() {

    }
}
