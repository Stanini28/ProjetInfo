/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.List;
import fr.insa.allouche.infoprojet.outils.Lire;
import java.util.ArrayList;
import fr.insa.allouche.infoprojet.Barre;

public class Treillis {

    private Terrain base;
    private List<Noeud> contient;
    private List<Barre> compose;

    public Treillis() {
        
        this.base = new Terrain();
        this.compose = new ArrayList();
        this.contient = new ArrayList();
    }
    
    public void addBarre(Barre barre) {
        if (barre.getCompose() != this) {
            if (barre.getCompose() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.compose.add(barre);
            barre.setCompose(this);
        }
    }
    
    public void addNoeud(Noeud noeud) {
        if (noeud.getContient() != this) {
            if (noeud.getContient() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.contient.add(noeud);
            noeud.setContient(this);
        }
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
            if (rep == 1){
                System.out.println("this");
            } else if (rep == 2) {
                Barre barre = new Barre();
                barre.setDebut(barre.demandeBarre().getDebut());
                barre.setFin(barre.demandeBarre().getFin());
                this.addBarre(barre);
            }
        }
    }

}
