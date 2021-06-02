/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.awt.BasicStroke;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * s
 *
 * @author stanislasallouche
 */
public class Barre {

    private int id;
    private Noeud debut;
    private Noeud fin;
    private Treillis compose;
    private TypeBarre type;
    public double Fx;
    public double Fy;
    public Color color;
    private Color couleurBarre;
    public static Color couleurSelect;

    public Barre(Noeud debut, Noeud fin, Color color) {
        this.debut = debut;
        this.fin = fin;
//        this.id= Identificateuer.getOrCreateId(this);
        this.debut.addBarre(this);
        this.fin.addBarre(this);
        this.color = color;
    }

    public Barre(Noeud debut, Noeud fin) {
        this(debut, fin, Color.BLUE);
    }

    public Barre(Noeud debut, Noeud fin, double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp, Color color) {
        this.debut = debut;
        this.fin = fin;
//        this.id= Identificateuer.getOrCreateId(this);
        this.debut.addBarre(this);
        this.fin.addBarre(this);
//        int n = 0;
//        if (this.compose != null) {
//            for (int i = 0; i < this.compose.getCatalogueBarre().size(); i++) {
//                if (coutAuMetre == this.compose.getCatalogueBarre().get(i).getCoutAuMetre()
//                        && lMax == this.compose.getCatalogueBarre().get(i).getlMax()
//                        && lMin == this.compose.getCatalogueBarre().get(i).getlMin()
//                        && rComp == this.compose.getCatalogueBarre().get(i).getrComp()
//                        && rTraction == this.compose.getCatalogueBarre().get(i).getrTraction()) {
//                    n++;
//                    this.type = this.compose.getCatalogueBarre().get(i);
//                }
//            }
//        }
//        if (n == 0) {
//            this.type = new TypeBarre(coutAuMetre, lMin, lMax, rTraction, rComp);
//            this.type.addTBarre(this);
//        }
        System.out.println("création barre");
        this.type = new TypeBarre(coutAuMetre, lMin, lMax, rTraction, rComp);
        this.type.addTBarre(this);
        this.color = color;
    }

    public Barre(Noeud debut, Noeud fin, double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp) {
        this(debut, fin, coutAuMetre, lMin, lMax, rTraction, rComp, Color.BLUE);
    }

    public Barre(Noeud debut, Noeud fin, TypeBarre tb) {
        this(debut, fin, tb.getCoutAuMetre(), tb.getlMin(), tb.getlMax(), tb.getrTraction(), tb.getrComp(), Color.BLUE);
    }

    public Barre(Noeud debut, Noeud fin, TypeBarre tb, Color couleur) {
        this(debut, fin, tb.getCoutAuMetre(), tb.getlMin(), tb.getlMax(), tb.getrTraction(), tb.getrComp(), couleur);
    }

    public Barre() {
        this.debut = new NoeudSimple();
        this.fin = new NoeudSimple();
    }

    void setDebut(Noeud debut) {
        this.debut = debut;
    }

    void setFin(Noeud fin) {
        this.fin = fin;
    }

    public Noeud getDebut() {
        return this.debut;
    }

    public Noeud getFin() {
        return this.fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TypeBarre getType() {
        return type;
    }

    void setType(TypeBarre type) {
        this.type = type;
    }

    void setCompose(Treillis compose) {
        this.compose = compose;
    }

    public Treillis getCompose() {
        return compose;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCouleurBarre(Color couleurBarre) {
        this.couleurBarre = couleurBarre;
    }

    public void setCouleurSelect(Color couleurSelect) {
        Barre.couleurSelect = couleurSelect;
    }
    
    

    public String toString() {
        String res = "Barre " + this.id + "{ ";
        res = res + "[" + this.getDebut().getPosition().getPX()
                + " , " + this.getDebut().getPosition().getPY() + "] ,";
        res = res + "[" + this.getFin().getPosition().getPX()
                + " , " + this.getFin().getPosition().getPY() + "] }\n";
        res = res + this.type.toString();
        return res;
    }

    public static Barre demandeBarre(TypeBarre tB) {
        System.out.println("noeud de début : ");
        Noeud noeudD = Noeud.demandeNoeud();

        System.out.println("noeud de fin : ");
        Noeud noeudF = Noeud.demandeNoeud();

        Barre b = new Barre(noeudD, noeudF, tB.getCoutAuMetre(), tB.getlMin(), tB.getlMax(), tB.getrTraction(), tB.getrComp());
        return b;
    }

    public static Barre demandeBarreNewTB() {
        System.out.println("noeud de début : ");
        Noeud noeudD = Noeud.demandeNoeud();

        System.out.println("noeud de fin : ");
        Noeud noeudF = Noeud.demandeNoeud();

        Barre b = new Barre(noeudD, noeudF);
        return b;
    }

    public void dessine(GraphicsContext context) {
        context.setLineWidth(4);
        context.setStroke(this.getType().getCouleur());
        context.strokeLine(this.debut.position.getPX(), this.debut.position.getPY(),
                this.fin.position.getPX(), this.fin.position.getPY());
    }

    public void dessineSelect(GraphicsContext context) {
        context.setLineWidth(4);
        context.setStroke(this.color);
        context.strokeLine(this.debut.position.getPX(), this.debut.position.getPY(),
                this.fin.position.getPX(), this.fin.position.getPY());
    }

    public void dessinePbBarre(GraphicsContext context) {
        context.setLineWidth(4);
        context.setStroke(this.couleurBarre);
        context.strokeLine(this.debut.position.getPX(), this.debut.position.getPY(),
                this.fin.position.getPX(), this.fin.position.getPY());
    }

    public double distancePoint(Point p) {
        double x1 = this.debut.position.getPX();
        double y1 = this.debut.position.getPY();
        double x2 = this.fin.position.getPX();
        double y2 = this.fin.position.getPY();
        double x3 = p.getPX();
        double y3 = p.getPY();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.debut.distancePoint(p);
        } else if (up > 1) {
            return this.fin.distancePoint(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }

    public static double longueur(Point p1, Point p2) {
        double res;
        res = Math.sqrt(Math.pow(p2.getPX() - p1.getPX(), 2) + Math.pow(p2.getPY() - p1.getPY(), 2));
        return res;
    }

    public String saveColor(Color c) {
        return c.getRed() + ";" + c.getGreen() + ";" + c.getBlue();
    }

    public void save(Writer w, Identificateur num) throws IOException {
//        if (num.objExist(this)) {
        //this.type.save(w, num);
        System.out.println("id noeud début :" + this.debut.getId());
        System.out.println("id noeud fin :" + this.fin.getId());
        w.append("Barre;" + this.id + ";" + this.type.getId() + ";" + this.debut.getId() + ";" + this.fin.getId() + "\n");
//        }

    }
}
