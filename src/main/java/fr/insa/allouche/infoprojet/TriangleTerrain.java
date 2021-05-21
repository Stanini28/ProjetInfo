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

public class TriangleTerrain {

    private int id;
    private SegmentTerrain SegTerrain1;
    private SegmentTerrain SegTerrain2;
    private SegmentTerrain SegTerrain3;
    private Terrain constitue;
    private Color color;

    //reprendre
    public TriangleTerrain(SegmentTerrain SegTerrain1, SegmentTerrain SegTerrain2,
            SegmentTerrain SegTerrain3) {

//        pas sur que la classe identificateur attribut le bon identifiant
//        this.id= getId();
        this.SegTerrain1 = SegTerrain1;
        this.SegTerrain2 = SegTerrain2;
        this.SegTerrain3 = SegTerrain3;

        //defini à quel Triangle terrain appartient les segment terrain
        SegTerrain1.setFaitPartieDe(this);
        SegTerrain2.setFaitPartieDe(this);
        SegTerrain3.setFaitPartieDe(this);
    }

    public TriangleTerrain(Point pt1, Point pt2, Point pt3) {

        //pas sur que la classe identificateur attribut le bon identifiant
        //this.id = getId();
        this.pointToSeg(pt1, pt2, pt3);
        //defini à quel Triangle terrain appartient les segment terrain
        SegTerrain1.setFaitPartieDe(this);
        SegTerrain2.setFaitPartieDe(this);
        SegTerrain3.setFaitPartieDe(this);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    void setSegTerrain1(SegmentTerrain SegTerrain1) {
        this.SegTerrain1 = SegTerrain1;
    }

    void setSegTerrain2(SegmentTerrain SegTerrain2) {
        this.SegTerrain2 = SegTerrain2;
    }

    void setSegTerrain3(SegmentTerrain SegTerrain3) {
        this.SegTerrain3 = SegTerrain3;
    }

    void setConstitue(Terrain constitue) {
        this.constitue = constitue;
    }

    public Terrain getConstitue() {
        return constitue;
    }

    public String toString() {
        String res = "";
        res = "Triangle terrain " + this.id + " : \n"
                + "Segment terrrain 1 :" + this.SegTerrain1.toString()
                + "\nSegment terrrain 2 :" + this.SegTerrain2.toString()
                + "\nSegment terrrain 3 :" + this.SegTerrain3.toString();
        return res;
    }

    public void pointToSeg(Point pt1, Point pt2, Point pt3) {
//        this.setSegTerrain1(new SegmentTerrain(pt1, pt2));
//        this.setSegTerrain2(new SegmentTerrain(pt2, pt3));
//        this.setSegTerrain3(new SegmentTerrain(pt3, pt1));
        this.SegTerrain1 = new SegmentTerrain(pt1, pt2);
        this.SegTerrain2 = new SegmentTerrain(pt2, pt3);
        this.SegTerrain3 = new SegmentTerrain(pt3, pt1);
        //this.id=9;
    }

    public void angle() {

    }

    public static TriangleTerrain demandeTriangleTerrain() {
        System.out.println("Segment terrain 1 :");
        SegmentTerrain seg1 = SegmentTerrain.demandeSegmentTerain();
        System.out.println("Segment terrain 2 :");
        SegmentTerrain seg2 = SegmentTerrain.demandeSegmentTerain();
        System.out.println("Segment terrain 3 :");
        SegmentTerrain seg3 = SegmentTerrain.demandeSegmentTerain();
        return new TriangleTerrain(seg1, seg2, seg3);
    }

    public void dessine(GraphicsContext context) {
        //context.setStroke(this.color);
        this.SegTerrain1.dessine(context);
        this.SegTerrain2.dessine(context);
        this.SegTerrain3.dessine(context);  
    }
    
    public void dessineSelect(GraphicsContext context) {
        //context.setStroke(this.color);
        this.SegTerrain1.dessineSelect(context);
        this.SegTerrain2.dessineSelect(context);
        this.SegTerrain3.dessineSelect(context);  
    }
    
    public void save(Writer w, Identificateur num) throws IOException {
//        if (num.objExist(this)) {
//            this.SegTerrain1.save(w, num);
//            this.SegTerrain2.save(w, num);
//            this.SegTerrain3.save(w, num);
            w.append("Triangle;" + this.id + ";(" +
                    (this.SegTerrain1.getDebut().getPX()+","+this.SegTerrain1.getDebut().getPY()) + ");(" + (this.SegTerrain2.getDebut().getPX()+","+this.SegTerrain2.getDebut().getPY()) +
                    ");(" + (this.SegTerrain3.getDebut().getPX()+","+this.SegTerrain3.getDebut().getPY())+")\n");
//        }
    }
}
