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
public class Reaction_Ry {
    private int idRy;
    private double Ry;
    private NoeudAppui nA;
    
    public Reaction_Ry(AppuiSimple noeud){
        this.nA =noeud;
    }
    
    public Reaction_Ry(AppuiDouble noeud){
        this.nA =noeud;
    }
    
    public String toString(){
        String res ="";
        res = "Réaction sur "+this.nA+": Ry "+this.idRy;
        return res;
    }

    public int getIdRy() {
        return idRy;
    }

    public double getRy() {
        return Ry;
    }

    public NoeudAppui getnA() {
        return nA;
    }

    public void setIdRy(int idRy) {
        this.idRy = idRy;
    }

    public void setRy(double Ry) {
        this.Ry = Ry;
    }
    
}
