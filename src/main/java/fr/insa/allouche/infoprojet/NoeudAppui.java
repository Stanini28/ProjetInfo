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
    private Point coord;
    
    public SegmentTerrain getSegmentTerrain(){
        return SegmentTerrain;
    }
    
    void setSegmentTerrain (SegmentTerrain segTerrain) {
        this.SegmentTerrain = segTerrain;
    }
    
    public NoeudAppui(Point coord) {
        super(coord);
    }
    
    public void positionP() {

    }
}
