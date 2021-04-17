/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

public class Poids {

    private double id;
    private double Fx;
    private double Fy;

    public Poids(double ApplX, double ApplY) {
        this.Fx = ApplX;
        this.Fy = ApplY;

    }

    public double getId() {
        return id;
    }

    public double getFx() {
        return Fx;
    }

    public double getFy() {
        return Fy;
    }

    public void setFx(double Fx) {
        this.Fx = Fx;
    }

    public void setFy(double Fy) {
        this.Fy = Fy;
    }
    
    public double Norme(){
        double Norme;
        Norme=Math.sqrt(this.Fx*this.Fx+this.Fy*this.Fy);
        return Norme;
    }

    
    
}
