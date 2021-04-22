/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import fr.insa.allouche.infoprojet.outils.Lire;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

public class Noeud {

    private int id;
    private Point position;
    private Poids P;
    private Treillis contient;
    private List<Barre> liee;

    //public Set<Barre> extremites;
    public Noeud(Point position) {
        this.position = new Point(position.getPX(), position.getPY());
        this.liee = new ArrayList();
    }
    
    public Noeud() {
        this.position = new Point();
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

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public List<Barre> getLiee() {
        return liee;
    }
    
    
    public String toString() {
        String res = "Noeud "+this.id+" [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]";
        return res;

    }

    public void sommeX() {

    }

    public void sommeY() {

    }

    public void solUnique() {

    }
    
    public void addBarre(Barre barre){
        this.liee.add(barre);
    }
    
    void setContient(Treillis contient) {
        this.contient = contient;
    }

    public static Noeud demandeNoeud() {
        System.out.println("voulez vous ajouter un noeud simple ou double ? écrire s ou d");
        if (Lire.S().equals("s")) {

        }
        double abs = 0;
        System.out.println("abscisse : ");
        abs = Lire.d();

        double ord = 0;
        System.out.println("ordonnée : ");
        ord = Lire.d();

        Point noeud = new Point(abs, ord);
        return new Noeud(noeud);
    }

}
