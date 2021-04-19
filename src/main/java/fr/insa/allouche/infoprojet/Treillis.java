/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.List;
import fr.insa.allouche.infoprojet.outils.Lire;
import java.util.ArrayList;
import java.util.HashMap;

public class Treillis {

    private Terrain base;
    private List<Noeud> contient;
    private List<Barre> compose;
    private Identificateur identite;

    public Treillis() {

        this.base = new Terrain();
        this.compose = new ArrayList();
        this.contient = new ArrayList();
        this.identite = new Identificateur();
    }

    public void addBarre(Barre barre) {
        if (barre.getCompose() != this) {
            if (barre.getCompose() != null) {
                throw new Error("La Barre appartient déjà au treillis");
            }
            this.compose.add(barre);
            barre.setCompose(this);
            barre.setId(this.identite.getOrCreateId(compose));
        }
    }

    public void addNoeud(Noeud noeud) {
        if (noeud.getContient() != this) {
            if (noeud.getContient() != null) {
                throw new Error("Le Noeud appartient déjà au treillis");
            }
            this.contient.add(noeud);
            noeud.setContient(this);
            noeud.setId(this.identite.getOrCreateId(contient));
        }
    }

    public String toString() {
        String res = "Treillis {\n";
        for (int i = 0; i < this.compose.size(); i++) {
            res = res + this.compose.get(i).toString() +"\n";
        }
        for (int i = 0; i < this.contient.size(); i++) {
            res = res +this.contient.get(i).toString() + "\n";
        }
        return res + "}";
    }

    public static Treillis treillisTest() {
        Point pos1 = new Point();
        Point pos2 = new Point(2, 3);
        Point pos3 = new Point(4, 5);
        Point pos4 = new Point(-1, -4);
        Noeud n1 = new Noeud(pos1);
        Noeud n2 = new Noeud(pos2);
        Noeud n3 = new Noeud(pos3);
        Noeud n4 = new Noeud(pos4);
        Barre b1 = new Barre(n1, n2);
        Barre b2 = new Barre(n3, n4);
        Barre b3 = new Barre(n4, n1);
        Treillis res = new Treillis();
        res.addBarre(b1);
        res.addBarre(b2);
        res.addBarre(b3);
        res.addNoeud(n1);
        res.addNoeud(n2);
        res.addNoeud(n3);
        return res;
    }

    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher tout");
            System.out.println("2) ajouter une barre");
            System.out.println("3) ajouter une noeud appui");
            System.out.println("4) ajouter une un segment terrain à partir "
                    + "ne noeud appui déjà existant");
            System.out.println("5) ajouter un segment terrain");
            System.out.println("6) supprimer un noeud appui appartenant"
                    + " à un segment terrain");
            System.out.println("7) supprimer tous les noeuds appui appartenant"
                    + " à un segment terrain");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 2) {
                Barre barre = Barre.demandeBarre();
                this.addBarre(barre);
            }
        }
    }

    public static void testMenu() {
        Treillis t = treillisTest();
        t.menuTexte();
    }

    public static void main(String[] args) {
        testMenu();
    }

}
