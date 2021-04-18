/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import fr.insa.allouche.infoprojet.outils.Lire;
//import java.util.Set;


public class Noeud {

    private int id;
    private Point position;
    private Poids P;
    private Treillis contient;

    //public Set<Barre> extremites;
    
    public Noeud (Point position){
        this.position = new Point(position.getPX(),position.getPY());
    }
    
    public Noeud() {
        this.position= new Point();
    }

    
    public Point getPosition() {
        return position;
    }

    public Treillis getContient() {
        return contient;
    }
    
    void setPosition(Point position) {
        this.position = position;
    }
    
    

    public void sommeX() {

    }

    public void sommeY() {

    }

    public void solUnique() {

    }

    

    void setContient(Treillis contient) {
        this.contient = contient;
    }
    
    public Noeud demandeNoeud(){
        Point noeud = new Point();
        System.out.println("abscisse : ");
        noeud.setPX(Lire.d());
        System.out.println("ordonnée : ");
        noeud.setPY(Lire.d());
        return new Noeud(noeud);
    }
    
}

