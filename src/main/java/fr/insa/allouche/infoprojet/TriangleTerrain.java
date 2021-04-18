/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;



public class TriangleTerrain {
    
    private int id;
    private SegmentTerrain SegTerrain1;
    private SegmentTerrain SegTerrain2;
    private SegmentTerrain SegTerrain3;
    
    //reprendre
    public TriangleTerrain(SegmentTerrain SegTerrain1, SegmentTerrain SegTerrain2,
            SegmentTerrain SegTerrain3 ) {
        
        //pas sur que la classe identificateur attribut le bon identifiant
        this.id= getId();
        this.SegTerrain1 = SegTerrain1;
        this.SegTerrain2 = SegTerrain2;
        this.SegTerrain3 = SegTerrain3;
        
        //defini à quel Triangle terrain appartient les segment terrain
        SegTerrain1.setFaitPartieDe(this);
        SegTerrain2.setFaitPartieDe(this);
        SegTerrain2.setFaitPartieDe(this);
    }

    public int getId() {
        return id;
    }

    public SegmentTerrain getSegTerrain1() {
        return SegTerrain1;
    }

    public SegmentTerrain getSegTerrain2() {
        return SegTerrain2;
    }

    public SegmentTerrain getSegTerrain3() {
        return SegTerrain3;
    }
    
    
    public void angle() {

    }
}
