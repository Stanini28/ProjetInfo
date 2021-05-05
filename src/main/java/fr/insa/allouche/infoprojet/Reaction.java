/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

public class Reaction {

    private int idRx;
    private int idRy;
    private double Rx;
    private double Ry;
//    private AppuiSimple as;
//    private AppuiDouble ad;
    private NoeudAppui nA;

    public Reaction(AppuiSimple noeud){
        this.nA =noeud;
    }
    
    public Reaction(AppuiDouble noeud){
        this.nA =noeud;
    }
    
    public int getIdRx() {
        return idRx;
    }

    public int getIdRy() {
        return idRy;
    }

    public double getRx() {
        return Rx;
    }

    public double getRy() {
        return Ry;
    }

    public void setIdRx(int idRx) {
        this.idRx = idRx;
    }

    public void setIdRy(int idRy) {
        this.idRy = idRy;
    }

    public void setRx(double Rx) {
        this.Rx = Rx;
    }

    public void setRy(double Ry) {
        this.Ry = Ry;
    }
    
    public String toString(){
        String res ="";
        res = "Réaction sur "+this.nA+": Rx "+this.idRx+"et Ry "+this.idRy;
        return res;
    }
    
}
