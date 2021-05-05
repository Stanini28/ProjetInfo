/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

/**
 *
 * @author Portable
 */
public class Reaction_Rx {
    private int idRx;
    private double Rx;
    private NoeudAppui nA;
    
    public Reaction_Rx(AppuiSimple noeud){
        this.nA =noeud;
    }
    
    public Reaction_Rx(AppuiDouble noeud){
        this.nA =noeud;
    }
    
    public String toString(){
        String res ="";
        res = "Réaction sur "+this.nA+": Rx "+this.idRx;
        return res;
    }

    public int getIdRx() {
        return idRx;
    }

    public double getRx() {
        return Rx;
    }

    public NoeudAppui getnA() {
        return nA;
    }

    public void setIdRx(int idRx) {
        this.idRx = idRx;
    }

    public void setRx(double Rx) {
        this.Rx = Rx;
    }
    
    
}
