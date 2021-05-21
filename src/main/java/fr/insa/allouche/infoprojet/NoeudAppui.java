/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;

/**
 *
 * @author stanislasallouche
 */
public abstract class NoeudAppui extends Noeud {

    private double alpha;
    private SegmentTerrain appartient;
    private Point coord;
    private int k;
    private int j;

    public SegmentTerrain getappartient() {
        return appartient;
    }

    public double getAlpha() {
        return alpha;
    }

    void setSegmentTerrain(SegmentTerrain appartient) {
        this.appartient = appartient;
    }

    public NoeudAppui(Point position, SegmentTerrain appartient, Color color) {
        super(position, color);
        this.appartient = appartient;
        this.calculAlpha();
    }

    public NoeudAppui(double alpha, SegmentTerrain segT, Color color) {
        super(calculP(alpha, segT), color);
        System.out.println("calculP(alpha, segT" + calculP(alpha, segT));
        this.appartient = segT;
        System.out.println("segt = " + segT);
        //this.appartient.add(this);
//        for (int i = 0; i < segT.getAppartient().size(); i++) {
//            System.out.println("liste noeud segT = "+segT.getAppartient().get(i));
//        }

        this.calculAlpha();
    }

    public NoeudAppui(double alpha, SegmentTerrain segT, Color color, TriangleTerrain tt) {
        super(calculP(alpha, segT), color);
        System.out.println("calculP(alpha, segT" + calculP(alpha, segT));
        this.appartient = segT;
        System.out.println("segt = " + segT);
        //this.appartient.add(this);
//        for (int i = 0; i < segT.getAppartient().size(); i++) {
//            System.out.println("liste noeud segT = "+segT.getAppartient().get(i));
//        }
        this.calculAlpha();
        if (segT == tt.getSegTerrain1()) {
            this.j = 0;
        } else if (segT == tt.getSegTerrain2()) {
            this.j = 1;
        } else if (segT == tt.getSegTerrain3()) {
            this.j = 2;
        } else {
            System.out.println("le segment terrain n'a pas trouver quel segment il est");
        }
        this.k = (j + 1) % 3;
        System.out.println("j =" + this.j);
        System.out.println("k = " + this.k);
    }

    
//    public NoeudAppui(SegmentTerrain segT, Color color){
//        new AppuiSimple(0, segT, color);
//    }
//    

    public void calculAlpha() {
        double px = this.getPosition().getPX();
        double py = this.getPosition().getPY();
        double px1 = this.getappartient().getDebut().getPX();
        double py1 = this.getappartient().getDebut().getPY();
        double px2 = this.getappartient().getFin().getPX();
        double py2 = this.getappartient().getFin().getPY();
        this.alpha = Math.sqrt(Math.pow(px - px1, 2) + Math.pow(py - py1, 2))
                / Math.sqrt(Math.pow(px2 - px1, 2) + Math.pow(py2 - py1, 2));
        System.out.println("alpha = " + this.alpha);
    }

    public static Point calculP(double alpha, SegmentTerrain segT) {

        Point p = new Point(alpha * segT.getFin().getPX() + (1 - alpha) * segT.getDebut().getPX(),
                alpha * segT.getFin().getPY() + (1 - alpha) * segT.getDebut().getPY());
        return p;
    }

    public int getJ() {
        return j;
    }

}
