/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.List;
import fr.insa.allouche.infoprojet.outils.Lire;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Treillis {

    private Terrain base;
    private List<Noeud> contient;
    private List<Barre> compose;
    private Identificateur identite;
    private List<TypeBarre> catalogueBarre;
    private double segPlusProche;
    private double noeudPlusProche;
    private double barrePlusProche;
    private List<NoeudSimple> Simp;
    private List<AppuiDouble> Adoub;
    private List<AppuiSimple> Asimp;

    private List<Reaction_Rx> rx;
    private List<Reaction_Ry> ry;

    public Treillis() {

        this.compose = new ArrayList();
        this.contient = new ArrayList();
        this.identite = new Identificateur();
        this.catalogueBarre = new ArrayList();
        this.Adoub = new ArrayList();
        this.Asimp = new ArrayList();
        this.Simp = new ArrayList();
        this.rx = new ArrayList();
        this.ry = new ArrayList();
    }

    void setBase(Terrain base) {
        this.base = base;
    }

    public Identificateur getIdentite() {
        return identite;
    }

    public void idTT(TriangleTerrain tt) {
        tt.setId(this.identite.getOrCreateId(tt));
    }

    public void addBarre(Barre barre) {
        System.out.println("ajout barre au trellis");
        if (barre.getCompose() != this) {
            if (barre.getCompose() != null) {
                throw new Error("La Barre appartient déjà au treillis");
            }
            this.compose.add(barre);
            barre.setCompose(this);
            barre.setId(this.identite.getOrCreateId(compose));
            int p = this.compose.size();
            // pk -1 ?
            if (this.compose.get(p - 1).getType() == null) {
                System.out.println("ici");
                TypeBarre tb = new TypeBarre();
                tb.DemandeTypeBarre();
                this.addTypeBarre(tb);
                this.compose.get(p - 1).setType(tb);
//            } else {
//                int n = 0;
//                for (int i = 0; i < this.catalogueBarre.size(); i++) {
//                    if (barre.getType().getCoutAuMetre() == this.catalogueBarre.get(i).getCoutAuMetre()
//                            && barre.getType().getlMax() == this.catalogueBarre.get(i).getlMax()
//                            && barre.getType().getlMin() == this.catalogueBarre.get(i).getlMin()
//                            && barre.getType().getrComp() == this.catalogueBarre.get(i).getrComp()
//                            && barre.getType().getrTraction() == this.catalogueBarre.get(i).getrTraction()) {
//                        n++;
//                    }
//                }
//                if (n == 0) {
//                    this.addTypeBarre(this.compose.get(p-1).getType());
//                }
            }

            System.out.println(" barre ajouté au trellis");
        }
    }

    public void addBarre(Barre barre, double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp, Color color) {
        if (barre.getCompose() != this) {
            if (barre.getCompose() != null) {
                throw new Error("La Barre appartient déjà au treillis");
            }
            this.compose.add(barre);
            barre.setCompose(this);
            barre.setId(this.identite.getOrCreateId(compose));
            // pk -1 ?
            int n = 0;
            for (int i = 0; i < this.catalogueBarre.size(); i++) {
                if (coutAuMetre == this.catalogueBarre.get(i).getCoutAuMetre()
                        && lMax == this.catalogueBarre.get(i).getlMax()
                        && lMin == this.catalogueBarre.get(i).getlMin()
                        && rComp == this.catalogueBarre.get(i).getrComp()
                        && rTraction == this.catalogueBarre.get(i).getrTraction()) {
                    n++;
                    barre.setType(this.catalogueBarre.get(i));
                    this.catalogueBarre.get(i).addTBarre(barre);
                }
            }
            if (n == 0) {
                barre.setType(new TypeBarre(coutAuMetre, lMin, lMax, rTraction, rComp));
                this.addTypeBarre(barre.getType());
            }

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

    public void addAppuiSimple(AppuiSimple noeud) {
        if (noeud.getContient() != this) {
            if (noeud.getContient() != null) {
                throw new Error("Le Noeud appartient déjà au treillis");
            }
            this.contient.add(noeud);
            noeud.setContient(this);
            noeud.setId(this.identite.getOrCreateId(contient));
            this.Asimp.add(noeud);
            Reaction_Rx rx = new Reaction_Rx(noeud);
            this.rx.add(rx);
            rx.setIdRx(this.identite.getOrCreateId(rx));
        }
    }

    public void addAppuiDouble(AppuiDouble noeud) {
        if (noeud.getContient() != this) {
            if (noeud.getContient() != null) {
                throw new Error("Le Noeud appartient déjà au treillis");
            }
            this.contient.add(noeud);
            noeud.setContient(this);
            noeud.setId(this.identite.getOrCreateId(contient));
            this.Adoub.add(noeud);
            Reaction_Rx rx = new Reaction_Rx(noeud);
            this.rx.add(rx);
            rx.setIdRx(this.identite.getOrCreateId(rx));
            Reaction_Ry ry = new Reaction_Ry(noeud);
            this.ry.add(ry);
            ry.setIdRy(this.identite.getOrCreateId(ry));
        }
    }

    public void addNoeudSimple(NoeudSimple noeud) {
        if (noeud.getContient() != this) {
            if (noeud.getContient() != null) {
                throw new Error("Le Noeud appartient déjà au treillis");
            }
            this.contient.add(noeud);
            noeud.setContient(this);
            noeud.setId(this.identite.getOrCreateId(contient));
            this.Simp.add(noeud);
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

    public void addTypeBarre(TypeBarre Tbarre) {
        if (Tbarre.getCatalogueDeBarre() != this) {
            if (Tbarre.getCatalogueDeBarre() != null) {
                throw new Error("Le type de barre appartient déjà au treillis");
            }
            this.catalogueBarre.add(Tbarre);
            Tbarre.setCatalogueDeBarre(this);
            //System.out.println(Tbarre.getCatalogueDeBarre());
            Tbarre.setId(this.identite.getOrCreateId(catalogueBarre));
        }
    }

    public void removeTypeBarre(TypeBarre Tbarre) {
        if (Tbarre.getCatalogueDeBarre() != this) {
            throw new Error("Le type de barre n'appartient pas au treillis");
        }
        this.catalogueBarre.remove(Tbarre);
        Tbarre.setCatalogueDeBarre(this);
    }

    public void removeAllTypeBarre() {

        for (int i = 0; i < this.catalogueBarre.size(); i++) {
            this.catalogueBarre.get(i).setCatalogueDeBarre(null);
        }
        this.catalogueBarre.clear();
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
                t.getConstitue().get(i).getSegTerrain1().setId(this.identite.getOrCreateId(base.getConstitue().get(i).getSegTerrain1()));
                t.getConstitue().get(i).getSegTerrain2().setId(this.identite.getOrCreateId(base.getConstitue().get(i).getSegTerrain2()));
                t.getConstitue().get(i).getSegTerrain3().setId(this.identite.getOrCreateId(base.getConstitue().get(i).getSegTerrain3()));
            }
        }
    }

    public void addTriangleTerrain(TriangleTerrain tt) {
        if (tt.getConstitue() != this.getBase()) {
            if (tt.getConstitue() != null) {
                throw new Error("Le Terrain appartient déjà à un autre Treillis");
            }
            this.base.addTriangleTerrain(tt);
            this.addTerrain(this.base);
            tt.setId(this.identite.getOrCreateId(tt));
            tt.getSegTerrain1().setId(this.identite.getOrCreateId(tt.getSegTerrain1()));
            tt.getSegTerrain2().setId(this.identite.getOrCreateId(tt.getSegTerrain2()));
            tt.getSegTerrain3().setId(this.identite.getOrCreateId(tt.getSegTerrain3()));
            System.out.println("id s1= " + tt.getId());
        }
    }

    public void removeTerrain() {
        if (this.base.getBase() != this) {
            throw new Error("Le Terrain n'appartient pas au treillis");
        }
        this.base.setBase(null);
        this.base = null;
    }

    public void removeTriangleTerrain(TriangleTerrain tT) {
        if (tT.getConstitue().getBase() != this) {
            throw new Error("Le TriangleTerrain n'appartient pas au treillis");
        }
        this.base.removeTriangleTerrain(tT);
    }

    public String toString() {
        String res = "Treillis {\n";
        for (int i = 0; i < this.compose.size(); i++) {
            res = res + this.compose.get(i).toString() + "\n";
        }
        res = res + "\n";
        for (int i = 0; i < this.contient.size(); i++) {
            res = res + this.contient.get(i).toString() + "\n";
        }
        res = res + "\n";
        for (int i = 0; i < this.catalogueBarre.size(); i++) {
            res = res + this.catalogueBarre.get(i).toString() + "\n";
        }
        res = res + this.base.toString();
        for (int i = 0; i < this.rx.size(); i++) {
            res = res + this.rx.get(i).toString() + "\n";
        }
        for (int i = 0; i < this.ry.size(); i++) {
            res = res + this.ry.get(i).toString() + "\n";
        }
        return res + "}";
    }

    public static Treillis TTest() {
        Point p1 = new Point(500, 700);
        Point p2 = new Point(100, 600);
        Point p3 = new Point(300, 500);
        Point p4 = new Point(90, 100);
        Point p5 = new Point(50, 50);
        double xmin = 0;
        double ymin = 0;
        double xmax = 500;
        double ymax = 700;
        SegmentTerrain seg1 = new SegmentTerrain(p1, p2);
        SegmentTerrain seg2 = new SegmentTerrain(p2, p3);
        SegmentTerrain seg3 = new SegmentTerrain(p3, p1);
        TriangleTerrain tt1 = new TriangleTerrain(seg1, seg2, seg3);
        Terrain t = new Terrain(xmin, xmax, ymin, ymax);
        t.addTriangleTerrain(tt1);
        NoeudSimple nS1 = new NoeudSimple(p1);
        NoeudSimple nS2 = new NoeudSimple(p2);
        NoeudSimple nAS = new NoeudSimple(p3);
        Barre s1 = new Barre(nS1, nS2, 34, 56, 78, 890, 789);
        Treillis res = new Treillis();
        res.compose.add(s1);
        res.contient.add(nS2);
        res.contient.add(nS1);
        res.contient.add(nAS);
        res.addTerrain(t);
        res.base.addTriangleTerrain(tt1);
        return res;
    }

    public static Treillis treillisTest() {
        Point pos1 = new Point();
        Point pos2 = new Point(20, 30);
        Point pos3 = new Point(40, 50);
        Point pos4 = new Point(10, 40);
        Point pos5 = new Point(60, 50);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos4);
        NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);
        Barre b1 = new Barre(nS1, nS2);
        Barre b2 = new Barre(nS4, nS5);
        Barre b3 = new Barre(nS4, nS1, 5, 25, 56, 800, 1000);
        Treillis res = new Treillis();
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos1, pos2);
        SegmentTerrain seg2 = new SegmentTerrain(pos2, pos3);
        SegmentTerrain seg3 = new SegmentTerrain(pos3, pos1);
        SegmentTerrain seg4 = new SegmentTerrain(pos2, pos4);
        SegmentTerrain seg5 = new SegmentTerrain(pos4, pos1);
        AppuiDouble nAD2 = new AppuiDouble(pos2, seg1);
        AppuiSimple nAS3 = new AppuiSimple(pos3, seg2);
        AppuiSimple nAS1 = new AppuiSimple(0.9, seg3);
        Barre bAd = new Barre(nAD2, nAS3, 6, 7, 78, 567, 789);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        TriangleTerrain tT2 = new TriangleTerrain(pos2, pos4, pos1);
        TypeBarre tB1 = new TypeBarre(2, 30, 55, 550, 500);
        TypeBarre tB2 = new TypeBarre();
        t1.addTriangleTerrain(tT1);
        t1.addTriangleTerrain(tT2);
//        res.addBarre(b1);
//        res.addBarre(b2);
        res.addTerrain(t1);
        res.addBarre(b3);
        res.addBarre(bAd);
        res.addNoeudSimple(nS1);
        res.addNoeudSimple(nS2);
        res.addNoeudSimple(nS4);
        res.addNoeudSimple(nS5);
        res.addAppuiDouble(nAD2);
        res.addAppuiSimple(nAS3);
        res.addTypeBarre(tB1);
        res.addTypeBarre(tB2);
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
            System.out.println("11) afficher tous les types de barres");//ok
            System.out.println("12) ajouter une barre");//ok
            System.out.println("13) ajouter une noeud");//ok
            System.out.println("29) ajouter une noeud Appui sur un segment terrain");//ok
            System.out.println("14) ajouter un segment terrain");//ok
            System.out.println("15) ajouter un triangle terrain");//ok
            System.out.println("16) ajouter un type de barre");//ok
            System.out.println("17) ajouter un terrain");//ok
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
            System.out.println("27) suprimer un type de barre");//ok
            System.out.println("28) suprimer tous les types de barres");
            System.out.println("30) Force execer sur un noeud ");//ok
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 12) {
                System.out.println("Voulez vous selectionner un type de barre déjà existant (1) ou"
                        + "  créer un nouveux type de barre (2) ? repondre 1 ou 2");
                int res = Lire.i() * 1000;
                if (res == 1000) {
                    String s = "";
                    for (int i = 0; i < this.catalogueBarre.size(); i++) {
                        s = s + this.catalogueBarre.get(i).toString() + "\n";
                    }
                    System.out.println(s);
                    System.out.println("Veuillez-indiquer le numero du type de barre que vous-voulez, si aucun ne vous convient tapez 2000");
                    int v = Lire.i();
                    if (v == 2000) {
                        res = 2000;
                    } else {
                        for (int i = 0; i < this.catalogueBarre.size(); i++) {
                            if (this.catalogueBarre.get(i).getId() == v) {
                                Barre barre = Barre.demandeBarre(this.catalogueBarre.get(i));

                                this.addBarre(barre);
                            }
                        }
                    }
                }
                if (res == 2000) {

                    Barre barre = Barre.demandeBarreNewTB();
                    this.addBarre(barre);
                }
            } else if (rep == 13) {
                Noeud.demandeNoeud();
            } else if (rep == 2 || rep == 18) {
                System.out.println("Les noeud contenu dans le treillis sont : \n");
                for (int i = 0; i < this.contient.size(); i++) {
                    System.out.println(this.contient.get(i) + "\n");
                }
                if (rep == 18) {
                    System.out.println("Quel noeud voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.contient.size(); i++) {
                        if (this.contient.get(i).getId() == id) {
                            removeNoeud(this.contient.get(i));
                        }
                    }
                }
            } else if (rep == 7 || rep == 23) {
                System.out.println("Les barres contenu dans le treillis sont : \n");
                for (int i = 0; i < this.compose.size(); i++) {
                    System.out.println(this.compose.get(i) + "\n");
                }
                if (rep == 23) {
                    System.out.println("Quel barre voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.compose.size(); i++) {
                        if (this.compose.get(i).getId() == id) {
                            removeBarre(this.compose.get(i));
                        }
                    }
                }
                //marche pas
            } else if (rep == 22) {
                removeAllNoeud();
                //marche pas
            } else if (rep == 24) {
                removeAllBarre();
            } else if (rep == 11 || rep == 27) {
                String s = "";
                for (int i = 0; i < this.catalogueBarre.size(); i++) {
                    s = s + this.catalogueBarre.get(i).toString() + "\n";
                }
                System.out.println(s);
                if (rep == 27) {
                    System.out.println("Quel type de barre voulezvus supprimer ?");
                    int v = Lire.i();
                    for (int i = 0; i < this.catalogueBarre.size(); i++) {
                        if (this.catalogueBarre.get(i).getId() == v) {
                            this.catalogueBarre.remove(i);
                        }
                    }
                }
            } else if (rep == 8) {
                System.out.println(this.base.toString());
            } else if (rep == 9 || rep == 26) {

                for (int i = 0; i < this.base.getConstitue().size(); i++) {
                    System.out.println(this.base.getConstitue().get(i));
                }
                if (rep == 26) {
                    System.out.println("Quel triangle terrain voulez-vous supprimer ?");
                    int id = Lire.i();
                    for (int i = 0; i < this.base.getConstitue().size(); i++) {
                        if (this.base.getConstitue().get(i).getId() == id) {
                            this.base.removeTriangleTerrain(this.base.getConstitue().get(i));
                        }
                    }
                }
            } else if (rep == 25) {
                this.base.removeAllTriangleTerrain();
            } else if (rep == 14) {
                SegmentTerrain.demandeSegmentTerain();
            } else if (rep == 15) {
                TriangleTerrain tt = TriangleTerrain.demandeTriangleTerrain();
                this.base.addTriangleTerrain(tt);
                this.idTT(tt);
            } else if (rep == 29) {
                System.out.println("Choissisez parmi la liste de triangle terrain :");

                for (int i = 0; i < this.base.getConstitue().size(); i++) {
                    System.out.println(this.base.getConstitue().get(i));
                }
                int rep1 = Lire.i();
                System.out.println("Voulez-vous crer un noeud appui simple (10) ou double (20)? repondre 10 ou 20");
                int rep3 = Lire.i();
                System.out.println("Voulez vous placer le noeud appui sur le segment Terrain 1, 2, ou 3 ?");
                System.out.println(this.base.getConstitue().get(rep1));
                int rep2 = Lire.i();
                System.out.println("donner alpha");
                double alpha = Lire.d();
                if (rep3 == 10) {
                    if (rep2 == 1) {
                        this.addAppuiSimple(new AppuiSimple(alpha, this.base.getConstitue().get(rep1).getSegTerrain1()));
                    }
                    if (rep2 == 2) {
                        this.addAppuiSimple(new AppuiSimple(alpha, this.base.getConstitue().get(rep1).getSegTerrain2()));
                    }
                    if (rep2 == 3) {
                        this.addAppuiSimple(new AppuiSimple(alpha, this.base.getConstitue().get(rep1).getSegTerrain3()));
                    }
                } else if (rep3 == 20) {
                    if (rep2 == 1) {
                        this.addAppuiDouble(new AppuiDouble(alpha, this.base.getConstitue().get(rep1).getSegTerrain1()));
                    }
                    if (rep2 == 2) {
                        this.addAppuiDouble(new AppuiDouble(alpha, this.base.getConstitue().get(rep1).getSegTerrain2()));
                    }
                    if (rep2 == 3) {
                        this.addAppuiDouble(new AppuiDouble(alpha, this.base.getConstitue().get(rep1).getSegTerrain3()));
                    }
                }
            } else if (rep == 30) {
                for (int i = 0; i < this.contient.size(); i++) {
                    System.out.println(this.contient.get(i).toString() + "\n");
                }
                System.out.println("Parmis les différents nooeuds du treilli sur lequel voulez vous exercer une force ?");
                int res = Lire.i();
                System.out.println("Donner la composante en x");
                double Fx = Lire.d();
                System.out.println("Donner la composante en y");
                double Fy = Lire.d();
            } else if (rep == 16) {
                this.addTypeBarre(TypeBarre.DemandeTypeBarre());
            } else if (rep == 17) {
                this.addTerrain(Terrain.demandeTerrain());
            }
        }
    }

    public static void testMenu() {
        Treillis t = treillisTest();
        t.menuTexte();
    }

    //public static void main(String[] args) {
    //    testMenu();
    //}
    public void dessine(GraphicsContext context) {
        for (int i = 0; i < this.compose.size(); i++) {
            this.compose.get(i).dessine(context);
        }
        for (int i = 0; i < this.contient.size(); i++) {
            this.contient.get(i).dessine(context);
        }
        if (this.base != null) {
            this.base.dessine(context);
        }
    }

    public Terrain getBase() {
        return base;
    }

    public List<Noeud> getContient() {
        return contient;
    }

    public List<Barre> getCompose() {
        return compose;
    }

    public List<TypeBarre> getCatalogueBarre() {
        return catalogueBarre;
    }

    public double distancePoint(Point p) {
        if (this.contient.isEmpty()) {
            return new Point(0, 0).distancePoint(p);
        } else {
            double dist = this.contient.get(0).distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            for (int i = 0; i < this.compose.size(); i++) {
                double cur = this.compose.get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            for (int i = 0; i < this.base.getConstitue().size(); i++) {
                double cur = this.base.getConstitue().get(i).getSegTerrain1().distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
                cur = this.base.getConstitue().get(i).getSegTerrain2().distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
                cur = this.base.getConstitue().get(i).getSegTerrain3().distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
    }

    public Noeud plusProcheN(Point p) {
        if (this.contient.isEmpty()) {
            this.noeudPlusProche = 999;
            return null;
        } else {
            Noeud nmin = this.contient.get(0);
            double Nmin = nmin.distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                Noeud ncur = this.contient.get(i);
                double cur = ncur.distancePoint(p);
                if (cur < Nmin) {
                    Nmin = cur;
                    nmin = ncur;
                }
            }
            this.noeudPlusProche = Nmin;
            return nmin;

        }
    }

    public Barre plusProcheB(Point p) {
        if (this.compose.isEmpty()) {
            this.barrePlusProche = 999;
            return null;
        } else {
            Barre bmin = this.compose.get(0);
            double Bmin = bmin.distancePoint(p);
            for (int i = 1; i < this.compose.size(); i++) {
                Barre bcur = this.compose.get(i);
                double cur = bcur.distancePoint(p);
                if (cur < Bmin) {
                    Bmin = cur;
                    bmin = bcur;
                }
            }
            System.out.println("barre min = " + bmin.getId());
            this.barrePlusProche = Bmin;
            return bmin;

        }
    }

    public SegmentTerrain plusProcheST(Point p) {
        if (this.base.getConstitue().isEmpty()) {
            System.out.println("empty");
            this.segPlusProche = 999;
            return null;
        } else {
            SegmentTerrain stmin = this.base.getConstitue().get(0).getSegTerrain1();
            double sTmin = stmin.distancePoint(p);
            for (int i = 0; i < this.base.getConstitue().size(); i++) {
                SegmentTerrain sTcur1 = this.base.getConstitue().get(i).getSegTerrain1();
                double cur = sTcur1.distancePoint(p);
                if (cur < sTmin) {
                    sTmin = cur;
                    stmin = sTcur1;
                }

                SegmentTerrain sTcur2 = this.base.getConstitue().get(i).getSegTerrain2();
                double cur2 = sTcur2.distancePoint(p);
                if (cur2 < sTmin) {
                    sTmin = cur2;
                    stmin = sTcur2;
                }

                SegmentTerrain sTcur3 = this.base.getConstitue().get(i).getSegTerrain3();
                double cur3 = sTcur3.distancePoint(p);
                if (cur3 < sTmin) {
                    sTmin = cur;
                    stmin = sTcur3;
                }
            }
            this.segPlusProche = sTmin;
            return stmin;

        }

    }

    public String lePlusProche() {
        String res = "";
        if (Math.abs(this.noeudPlusProche - this.segPlusProche) < 1 && this.noeudPlusProche != 999 && this.segPlusProche != 999) {
            res = "NS";
            return res;
        } else if (Math.abs(this.barrePlusProche - this.noeudPlusProche) < 1 && this.barrePlusProche != 999 && this.noeudPlusProche != 999) {
            res = "BN";
            return res;
        } else if (Math.abs(this.barrePlusProche - this.segPlusProche) < 1 && this.barrePlusProche != 999 && this.segPlusProche != 999) {
            res = "BS";
            return res;
        } else if (this.barrePlusProche < this.noeudPlusProche && this.barrePlusProche < this.segPlusProche && this.barrePlusProche != 999) {
            res = "B";
            System.out.println("barre plus proche");
            return res;

        } else if ((this.segPlusProche < this.noeudPlusProche && this.segPlusProche < this.barrePlusProche && this.segPlusProche != 999)) {
            res = "S";
            System.out.println("segt plus proche");
            return res;
        } else if (this.noeudPlusProche < this.barrePlusProche && this.noeudPlusProche < this.segPlusProche && this.noeudPlusProche != 999) {
            res = "N";
            System.out.println("noeud plus proche");
            return res;

        } else {
            res = null;
            System.out.println("rien est assez proche");
            return res;

        }
    }

    public TriangleTerrain segtTrouveTT(SegmentTerrain segt) {
        TriangleTerrain tt = null;
        for (int i = 0; i < this.getBase().getConstitue().size(); i++) {
            if (segt == this.getBase().getConstitue().get(i).getSegTerrain1()
                    || segt == this.getBase().getConstitue().get(i).getSegTerrain2()
                    || segt == this.getBase().getConstitue().get(i).getSegTerrain3()) {
                tt = this.getBase().getConstitue().get(i);
            }
        }
        return tt;
    }

    public List<AppuiDouble> getAdoub() {
        return Adoub;
    }

    public List<AppuiSimple> getAsimp() {
        return Asimp;
    }

    public List<NoeudSimple> getSimp() {
        return Simp;
    }

    public List<Reaction_Rx> getRx() {
        return rx;
    }

    public List<Reaction_Ry> getRy() {
        return ry;
    }

    public boolean nZoneConstructible(Point pt) {
        boolean res = true;
        if (pt.getPX() < this.getBase().getXmin()
                || pt.getPX() > this.getBase().getXmax()
                || pt.getPY() < this.getBase().getYmin()
                || pt.getPY() > this.getBase().getYmax()) {
            res = false;
        }
        return res;
    }

    public void sauvegarde(File F) throws IOException {
        Identificateur Num = this.identite;

        try ( BufferedWriter bout = new BufferedWriter(new FileWriter(F))) {
            for (int i = 0; i < this.Adoub.size(); i++) {
                this.Adoub.get(i).save(bout, Num);
            }//AJOUT APPUI DOUBLE
            for (int i = 0; i < this.Asimp.size(); i++) {
                this.Asimp.get(i).save(bout, Num);
            }//AJOUT APPUI SIMPLE
            for (int i = 0; i < this.Simp.size(); i++) {
                this.Simp.get(i).save(bout, Num);
            }//AJOUT NOEUD SIMPLE
            for (int i = 0; i < this.compose.size(); i++) {
                this.compose.get(i).save(bout, Num);
            }//AJOUT BARRE
            for (int i = 0; i < this.catalogueBarre.size(); i++) {
                this.catalogueBarre.get(i).save(bout, Num);
            }//AJOUT TYPE BARRE
            for (int i = 0; i < this.base.getConstitue().size(); i++) {
                this.base.getConstitue().get(i).save(bout, Num);
            }//AJOUT TRIANGLE TERRAIN+ SEGMENT TERRAIN
            this.base.save(bout, Num);
        }
    }

    public static void ExempleSauvegarde() {
        Point pos1 = new Point();
        Point pos2 = new Point(20, 30);
        Point pos3 = new Point(40, 50);
        Point pos4 = new Point(10, 40);
        Point pos5 = new Point(60, 50);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos4);
        NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);
        Barre b1 = new Barre(nS1, nS2);
        Barre b2 = new Barre(nS4, nS5);
        Barre b3 = new Barre(nS4, nS1, 5, 25, 56, 800, 1000);
        Treillis res = new Treillis();
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos1, pos2);
        SegmentTerrain seg2 = new SegmentTerrain(pos2, pos3);
        SegmentTerrain seg3 = new SegmentTerrain(pos3, pos1);
        SegmentTerrain seg4 = new SegmentTerrain(pos2, pos4);
        SegmentTerrain seg5 = new SegmentTerrain(pos4, pos1);
        AppuiDouble nAD2 = new AppuiDouble(pos2, seg1);
        AppuiSimple nAS3 = new AppuiSimple(pos3, seg2);
        AppuiSimple nAS1 = new AppuiSimple(0.9, seg3);
        Barre bAd = new Barre(nAD2, nAS3, 6, 7, 78, 567, 789);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        TriangleTerrain tT2 = new TriangleTerrain(pos2, pos4, pos1);
        TypeBarre tB1 = new TypeBarre(2, 30, 55, 550, 500);
        TypeBarre tB2 = new TypeBarre();

        nS1.setId(0);
        nS2.setId(1);
        nS4.setId(2);
        nS5.setId(3);
        b1.setId(4);
        b2.setId(5);
        b3.setId(6);
        seg1.setId(7);
        seg2.setId(8);
        seg3.setId(9);
        nAD2.setId(12);
        nAS3.setId(13);
        nAS1.setId(14);
        bAd.setId(15);
        tT1.setId(16);
        tB1.setId(18);
        tB2.setId(19);

        tT1.setSegTerrain1(seg1);
        tT1.setSegTerrain2(seg2);
        tT1.setSegTerrain3(seg3);
        seg1.setFaitPartieDe(tT1);
        seg2.setFaitPartieDe(tT1);
        seg3.setFaitPartieDe(tT1);
        t1.addTriangleTerrain(tT1);
        res.addBarre(b1);
        res.addBarre(b2);
        res.addTerrain(t1);
        res.addBarre(b3);
        res.addBarre(bAd);
        res.addNoeudSimple(nS1);
        res.addNoeudSimple(nS2);
        res.addNoeudSimple(nS4);
        res.addNoeudSimple(nS5);
        res.addAppuiDouble(nAD2);
        res.addAppuiSimple(nAS3);
        res.addTypeBarre(tB1);
        res.addTypeBarre(tB2);

        try {
            res.sauvegarde(new File("Test 1"));
        } catch (IOException ex) {
            throw new Error("Problème :" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ExempleSauvegarde();
    }

}
