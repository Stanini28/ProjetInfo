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
public class NoeudSimple extends Noeud {
    
    public NoeudSimple (Point position){
        super(position);
    }
    
    public NoeudSimple (){
        super(new Point());
    }
    
    public void sommeX() {

    }
    

    public void sommeY() {
    }
    
    public String toString() {
        String res = "NoeudSimple " + this.getId() + " [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]";
        return res;
    }

}

