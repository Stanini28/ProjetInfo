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
public abstract class NoeudAppui extends Noeud {
    
    private double alpha; 
    private SegmentTerrain appartient;
    private Point coord;
    
    public SegmentTerrain getappartient(){
        return appartient;
    }

    public double getAlpha() {
        return alpha;
    }
    
    void setSegmentTerrain (SegmentTerrain appartient) {
        this.appartient = appartient;
    }
    
    public NoeudAppui(Point position, SegmentTerrain appartient) {
        super(position);
        this.appartient = appartient;
        this.appartient.add(this);
        this.calculAlpha();
    }
    public NoeudAppui(double alpha, SegmentTerrain segT) {
        super(calculP(alpha, segT));
        System.out.println("calculP(alpha, segT"+calculP(alpha, segT));
        this.appartient = segT;
        this.appartient.add(this);
        this.calculAlpha();
    }
    
    public NoeudAppui(SegmentTerrain segT){
        new AppuiSimple(0, segT);
    }
    
    
    public void calculAlpha(){
        double px = this.getPosition().getPX();
        double py = this.getPosition().getPY();
        double px1 = this.getappartient().getDebut().getPX();
        double py1 = this.getappartient().getDebut().getPY();
        double px2 = this.getappartient().getFin().getPX();
        double py2 = this.getappartient().getFin().getPY();
        this.alpha = Math.sqrt(Math.pow(px-px1, 2) + Math.pow(py-py1, 2))/
                Math.sqrt(Math.pow(px2-px1, 2)+Math.pow(py2-py1, 2));
        System.out.println("alpha = "+this.alpha);
    }
    public static Point calculP(double alpha, SegmentTerrain segT){
        
        Point p = new Point(alpha*segT.getFin().getPX() + (1-alpha) * segT.getDebut().getPX(),
                alpha*segT.getFin().getPY() + (1-alpha) * segT.getDebut().getPY());
    return p;
    }
    
}
