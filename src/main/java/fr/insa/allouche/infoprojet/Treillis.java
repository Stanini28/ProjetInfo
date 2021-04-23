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

    public void removeBarre(Barre barre) {
        if (barre.getCompose() != this) {
            throw new Error("La barre n'appartient pas au treillis");
        }
        this.compose.remove(barre);
        barre.setCompose(null);
    }

    public void removeAllBarre() {

        for (int i = 0; i < this.compose.size(); i++) {
            this.compose.get(i).setCompose(null);
        }
        this.compose.clear();
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

    public void removeNoeud(Noeud noeud) {
        if (noeud.getContient() != this) {
            throw new Error("Le Noeud n'appartient pas au treillis");
        }
        this.contient.remove(noeud);
        noeud.setContient(null);
    }
    
    public void removeAllNoeud() {
        
        for (int i = 0; i < this.contient.size(); i++) {
            this.contient.get(i).setContient(null);
        }
            this.contient.clear();
    }

    public void addTerrain(Terrain t) {
        if (t.getBase() != this) {
            if (t.getBase() != null) {
                throw new Error("Le Terrain appartient déjà à un autre Treillis");
            }
            this.base = t;
            t.setBase(this);
            for (int i = 0; i < t.getConstitue().size(); i++) {
                t.getConstitue().get(i).setId(this.identite.getOrCreateId(base.getConstitue().get(i)));
            }
        }
    }

    public void removeTerrain(Terrain t) {
        if (t.getBase() != this) {
            throw new Error("Le Terrain n'appartient pas au treillis");
        }
        this.base = null;
        t.setBase(null);
    }

    public String toString() {
        String res = "Treillis {\n";
        for (int i = 0; i < this.compose.size(); i++) {
            res = res + this.compose.get(i).toString() + "\n";
        }
        for (int i = 0; i < this.contient.size(); i++) {
            res = res + this.contient.get(i).toString() + "\n";
        }
        res = res + this.base.toString();
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
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos1, pos2);
        SegmentTerrain seg2 = new SegmentTerrain(pos2, pos3);
        SegmentTerrain seg3 = new SegmentTerrain(pos3, pos4);
        SegmentTerrain seg4 = new SegmentTerrain(pos4, pos2);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        TriangleTerrain tT2 = new TriangleTerrain(seg4, seg2, seg3);
        t1.addTriangleTerrain(tT1);
        t1.addTriangleTerrain(tT2);
        res.addBarre(b1);
        res.addBarre(b2);
        res.addBarre(b3);
        res.addNoeud(n1);
        res.addNoeud(n2);
        res.addNoeud(n3);
        res.addTerrain(t1);
        return res;
    }

    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher tout");//ok
            System.out.println("2) afficher tous les noeuds");//ok
            System.out.println("3) afficher tous les noeuds appui");
            System.out.println("4) afficher tous les noeuds appui simple");
            System.out.println("5) afficher tous les noeuds appui double");
            System.out.println("6) afficher tous les noeuds simples");
            System.out.println("7) afficher toutes les barres");//ok
            System.out.println("8) afficher le terrain");//ok
            System.out.println("9) afficher tous les triangles terrains"); //ok
            System.out.println("10) afficher tous les segments terrains");
            System.out.println("11) afficher tous les types de barres");
            System.out.println("12) ajouter une barre");//ok
            System.out.println("13) ajouter une noeud");//ok
            System.out.println("14) ajouter un segment terrain");
            System.out.println("15) ajouter un triangle terrain");
            System.out.println("16) ajouter un type de barre");
            System.out.println("17) ajouter un terrain");
            System.out.println("18) supprimer un noeud");//ok
            System.out.println("19) supprimer tous les noeuds appui appartenant"
                    + " à un segment terrain");
            System.out.println("20) supprimer tous les noeuds appartenant"
                    + " à une barre");//ok
            System.out.println("21) supprimer un noeud et toutes les"
                    + " relié à ce dernier");
            System.out.println("22) suprimer tous les noeud du treilli");//ok
            System.out.println("23) suprimer une barre");//ok
            System.out.println("24) suprimer toutes les barres du treillis");//ok
            System.out.println("25) suprimer tous les triangles terrain");//ok
            System.out.println("26) suprimer un triangle terrain");//ok
            System.out.println("27) suprimer un type de barre");
            System.out.println("28) suprimer tous les types de barres");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 12) {
                Barre barre = Barre.demandeBarre();
                this.addBarre(barre);
            } else if (rep == 2 || rep == 18){
                System.out.println("Les noeud contenu dans le treillis sont : \n");
                for (int i = 0; i < this.contient.size(); i++) {
                    System.out.println(this.contient.get(i)+"\n");
                }
                if (rep == 18){
                    System.out.println("Quel noeud voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.contient.size(); i++) {
                        if (this.contient.get(i).getId()== id){
                        removeNoeud(this.contient.get(i));  
                        }
                    }
                }
            } else if (rep == 7 || rep == 23){
                System.out.println("Les barres contenu dans le treillis sont : \n");
                for (int i = 0; i < this.compose.size(); i++) {
                    System.out.println(this.compose.get(i)+"\n");
                }
                if (rep == 23){
                    System.out.println("Quel barre voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.compose.size(); i++) {
                        if (this.compose.get(i).getId()== id){
                        removeBarre(this.compose.get(i));  
                        }
                    }
                }
                //marche pas
            } else if (rep ==22){
                removeAllNoeud();
                //marche pas
            } else if (rep ==24){
                removeAllBarre();
            } else if (rep == 8){
                System.out.println(this.base.toString());
            } else if (rep == 9 || rep == 26){
                
                for (int i = 0; i < this.base.getConstitue().size(); i++) {
                System.out.println(this.base.getConstitue().get(i));
                }
                if (rep == 26){
                    System.out.println("Quel triangle terrain voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.base.getConstitue().size(); i++) {
                        if (this.base.getConstitue().get(i).getId()== id){
                        this.base.removeTriangleTerrain(this.base.getConstitue().get(i));  
                        }
                    }
                }
            } else if (rep == 25){
                this.base.removeAllTriangleTerrain();
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
