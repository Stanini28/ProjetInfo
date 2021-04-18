/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

public class Point {
    
    private double PX;
    private double PY;
    
    public Point (double PX,double PY) {
       this.PX = PX;
       this.PY = PY;
    }
    public Point() {
        this(0, 0);
    }
    

    /**
     * @return the PX
     */
    public double getPX() {
        return PX;
    }

    /**
     * @return the PY
     */
    public double getPY() {
        return PY;
    }
    
    public void setPX(double Px) {
        this.PX=Px;
    }
    
    public void setPY(double Px) {
        this.PX=Px;
    }
}
