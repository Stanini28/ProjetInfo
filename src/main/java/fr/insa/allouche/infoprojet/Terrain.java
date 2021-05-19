/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import fr.insa.allouche.infoprojet.outils.Lire;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Terrain {

    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private List<TriangleTerrain> constitue;
    private Treillis base;

    public Terrain(Point p1, Point p2, Point p3, Point p4) {
        x_yMin_x_yMax(p1, p2, p3, p4);
        this.constitue = new ArrayList();
    }

    public Terrain(double xmin, double xmax, double ymin, double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.ymin = ymin;
        this.constitue = new ArrayList();
    }

    public Treillis getBase() {
        return base;
    }

    public void setBase(Treillis base) {
        this.base = base;
    }

    public List<TriangleTerrain> getConstitue() {
        return constitue;
    }
    
    
    public void x_yMin_x_yMax(Point p1, Point p2, Point p3, Point p4) {
        double res = 0;
        double p1X = p1.getPX();
        double p2X = p2.getPX();
        double p3X = p3.getPX();
        double p4X = p4.getPX();
        double p1Y = p1.getPY();
        double p2Y = p2.getPY();
        double p3Y = p3.getPY();
        double p4Y = p4.getPY();

        double xmin = Math.min(p1X, p2X);
        xmin = Math.min(xmin, p3X);
        xmin = Math.min(xmin, p4X);
        double xmax = Math.max(p1X, p2X);
        xmax = Math.max(xmax, p3X);
        xmax = Math.max(xmax, p4X);
        double ymin = Math.min(p1Y, p2Y);
        ymin = Math.min(ymin, p3Y);
        ymin = Math.min(ymin, p4Y);
        double ymax = Math.max(p1Y, p2Y);
        ymax = Math.max(ymax, p3Y);
        ymax = Math.max(ymax, p4Y);

        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public void addTriangleTerrain(TriangleTerrain tT) {
        if (tT.getConstitue() != this) {
            if (tT.getConstitue() != null) {
                throw new Error("Le Triangle Terrain appartient déjà à un autre SegmentTerrain");
            }
            this.constitue.add(tT);
            tT.setConstitue(this);
//            tT.setId(this.base.getIdentite().getOrCreateId(tT));
            
        }
    }

    public void removeTriangleTerrain(TriangleTerrain tT) {
        if (tT.getConstitue() != this) {
            throw new Error("Le Triangle Terrain n'appartient pas au terrain");
        }
        this.constitue.remove(tT);
        tT.getSegTerrain1().removeAll();
        tT.getSegTerrain2().removeAll();
        tT.getSegTerrain3().removeAll();
        tT.getSegTerrain1().getAppartient().clear();
        tT.getSegTerrain2().getAppartient().clear();
        tT.getSegTerrain3().getAppartient().clear();
        tT.setConstitue(null);
        tT.setSegTerrain1(null);
        tT.setSegTerrain2(null);
        tT.setSegTerrain3(null);
    }

    public void removeAllTriangleTerrain() {
        for (int i = 0; i < this.constitue.size(); i++) {
            this.removeTriangleTerrain(this.constitue.get(i));
        }
        this.constitue.clear();
    }

    public String toString() {
        String res = "";
        res = res + "Terrain :{\n       Zone constructible : { xmin = " + this.xmin + " xmax = " + this.xmax
                + " ymin = " + this.ymin + " ymax = " + this.ymax + "}\n\n";
        for (int i = 0; i < this.constitue.size(); i++) {
            res = res + "       " + this.constitue.get(i).toString() + "\n\n";
        }
        return res + "}";
    }

    public static Terrain demandeTerrain() {
        System.out.println("initialisation de la zonne constructible :");
        System.out.println("donnez xmin :");
        double xmin = Lire.d();
        System.out.println("donnez ymin :");
        double ymin = Lire.d();
        System.out.println("donnez xmax :");
        double xmax = Lire.d();
        System.out.println("donnez ymax :");
        double ymax = Lire.d();
        
        Terrain t = new Terrain(xmin, xmax, ymin, ymax);
        
        System.out.println("initialisation des ou du triangle terrain : ");
        int rep = 1;
        while (rep == 1) {
            TriangleTerrain tT = TriangleTerrain.demandeTriangleTerrain();
            t.addTriangleTerrain(tT);
            System.out.println("voulez vous rentrer un nouveau triangle terrain ? si oui repondre 1 sinon 0");
            rep = Lire.i();
        }
        return t;
    }
    public void dessine(GraphicsContext context) {
       
        for (int i = 0; i < this.constitue.size(); i++) {
            this.constitue.get(i).dessine(context);
        }
        context.setStroke(Color.BROWN);
        context.setLineWidth(1);
        context.strokeLine(xmin, ymin, xmin, ymax);
        context.strokeLine(xmin, ymax, xmax, ymax);
        context.strokeLine(xmax, ymax, xmax, ymin);
        context.strokeLine(xmax, ymin, xmin, ymin);
    }
    public void dessineSelect(GraphicsContext context) {
       
        for (int i = 0; i < this.constitue.size(); i++) {
            this.constitue.get(i).dessineSelect(context);
        }
        context.setStroke(Color.BROWN);
        context.setLineWidth(1);
        context.strokeLine(xmin, ymin, xmin, ymax);
        context.strokeLine(xmin, ymax, xmax, ymax);
        context.strokeLine(xmax, ymax, xmax, ymin);
        context.strokeLine(xmax, ymin, xmin, ymin);
    }
    
    public void save(Writer w, Identificateur num) throws IOException{
        w.append("ZoneConstructible;"+ this.xmin+ ";" + this.xmax +
                ";" + this.ymin+ ";" + this.ymax +"\n");
       
        
    }

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }
    
    
    
}
