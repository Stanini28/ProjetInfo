/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SegmentTerrain {

    private Point debut;
    private Point fin;
    private List<NoeudAppui> appartient;
    //Est defini dans la classe TriangleTerrain, lorqu'un Triangle terrain est créé
    private TriangleTerrain faitPartieDe;
    private Color color;
    private Color colorSelect;
    private int id;

    public SegmentTerrain(Point posDbt, Point posFin) {
        this.debut = new Point(posDbt.getPX(), posDbt.getPY());
        this.fin = new Point(posFin.getPX(), posFin.getPY());
        this.appartient = new ArrayList<NoeudAppui>();
        this.color = Color.BLACK;
    }

    public SegmentTerrain(SegmentTerrain segt) {
        this.debut = segt.debut;
        this.fin = segt.fin;
        this.appartient = new ArrayList<NoeudAppui>();
        this.color = Color.BLACK;
    }

    public SegmentTerrain(Point posDbt, Point posFin, Color color) {
        this.debut = new Point(posDbt.getPX(), posDbt.getPY());
        this.fin = new Point(posFin.getPX(), posFin.getPY());
        this.appartient = new ArrayList<NoeudAppui>();
        this.color = color;
    }

    public SegmentTerrain(SegmentTerrain segt, Color color) {
        this.debut = segt.debut;
        this.fin = segt.fin;
        this.appartient = new ArrayList<NoeudAppui>();
        this.color = color;
    }

    public Point getDebut() {
        return debut;
    }

    public Point getFin() {
        return fin;
    }

    public List<NoeudAppui> getAppartient() {
        return appartient;
    }

    public TriangleTerrain getFaitPartieDe() {
        return faitPartieDe;
    }

    void setFaitPartieDe(TriangleTerrain faitPartieDe) {
        this.faitPartieDe = faitPartieDe;
    }

    void setAppartient(List<NoeudAppui> appartient) {
        this.appartient = appartient;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColorSelect(Color colorSelect) {
        this.colorSelect = colorSelect;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(AppuiSimple as) {
        if (as.getappartient() != this) {
            if (as.getappartient() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.appartient.add(as);
            as.setSegmentTerrain(this);
            as.calculAlpha();
        }
    }

    public void add(AppuiDouble ad) {
        if (ad.getappartient() != this) {
            if (ad.getappartient() != null) {
                throw new Error("Le Noeud appui appartient déjà à un autre SegmentTerrain");
            }
            this.appartient.add(ad);
            ad.setSegmentTerrain(this);
            ad.calculAlpha();
        }
    }

    public void remove(NoeudAppui nA) {
        if (nA.getappartient() != this) {
            throw new Error("Le Noeud appui n'appartient pas à SegmentTerrain");
        }
        nA.setContient(null);
        this.appartient.remove(nA);
        nA.setSegmentTerrain(null);
    }

    public void removeAll() {
        //pas sur d'avoir compris comment fonctionne le for
        for (int i = 0; i < this.appartient.size(); i++) {
            this.remove(this.appartient.get(i));
        }
        this.appartient.clear();
    }

    // Manque size et clear par rapport à la classe groupe du prof
    public String toString() {
        String res = "";
        res = "{" + this.debut.toString() + " ," + this.fin.toString() + "}";
        res = res + "taille liste noeud: " + this.appartient.size();
        return res;
    }

    public static SegmentTerrain demandeSegmentTerain() {
        System.out.println("Coordonée du premier noeud appui : ");
        Point p1 = Point.demandePoint();
        System.out.println("Coordonée du deuxième noeud appui : ");
        Point p2 = Point.demandePoint();
        SegmentTerrain seg = new SegmentTerrain(p1, p2);
        return seg;
    }

    public void dessine(GraphicsContext context) {
        context.setStroke(this.color);
        context.setLineWidth(1);
        context.strokeLine(this.debut.getPX(), this.debut.getPY(),
                this.fin.getPX(), this.fin.getPY());
    }
    
    public void dessineSelect(GraphicsContext context) {
        context.setStroke(this.colorSelect);
        context.setLineWidth(1);
        context.strokeLine(this.debut.getPX(), this.debut.getPY(),
                this.fin.getPX(), this.fin.getPY());
    }

    public String saveColor(Color c) {
        return c.getRed() + ";" + c.getGreen() + ";" + c.getBlue();
    }

    public void save(Writer w, Identificateur num) throws IOException {
        if (!num.objExist(this)) {
            w.append("SegmentTerrain;" + this.id + ";"
                    + this.debut.getPX() + ";" + this.debut.getPY() + ";" + this.fin.getPX()
                    + ";" + this.fin.getPY() + ";" + this.faitPartieDe.getId() +"\n");
        }
    }

    public double distancePoint(Point p) {
        double x1 = this.debut.getPX();
        double y1 = this.debut.getPY();
        double x2 = this.fin.getPX();
        double y2 = this.fin.getPY();
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
}
//oooo
