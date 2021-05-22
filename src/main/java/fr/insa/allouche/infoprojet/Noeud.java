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
//import java.util.Set;

public abstract class Noeud {

    private int id;
    public Point position;
    private Treillis contient;
    public List<Barre> liee;
    public double forceY;
    private Color color;
    private Color colorSelect;
   
    //public Set<Barre> extremites;
    public Noeud(Point position, Color color) {
        this.position = new Point(position.getPX(), position.getPY());
        this.liee = new ArrayList();
        this.color = color;
    }
    
    public Noeud() {
        this.position = new Point();
        this.color = Color.BLACK;
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

    public void setId(int id) {
        this.id = id;
    }

    public List<Barre> getLiee() {
        return liee;
    }

    public String toString() {
        String res = this.toString();
        return res;

    }

    public void sommeX() {

    }

    public void sommeY() {

    }

    public void solUnique() {

    }

    public void addBarre(Barre barre) {
        this.liee.add(barre);
    }

    void setContient(Treillis contient) {
        this.contient = contient;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorSelect() {
        return colorSelect;
    }

    public void setColorSelect(Color colorSelect) {
        this.colorSelect = colorSelect;
    }
    
    

    public static Noeud demandeNoeud() {
        int rep = 2;
        while (rep == 2 || rep != 1 && rep != 3 && rep != 4) {
            System.out.println("voulez vous ajouter un noeud simple (1) ou un noeud appui (2) ? écrire 1 ou 2");
            rep = Lire.i();

            if (rep == 1) {
                rep = 1;

            } else if (rep == 2) {
                System.out.println("Voulez-vous créer un appui simple (3) ou double (4) ? repondre 3 ou 4");
                rep = Lire.i();

            } else {
                System.out.println("répondez les chiffre indiqué");
            }
        }
        double abs = 0;
        System.out.println("abscisse : ");
        abs = Lire.d();

        double ord = 0;
        System.out.println("ordonnée : ");
        ord = Lire.d();

        Point n = new Point(abs, ord);
        if (rep == 1) {
            return new NoeudSimple(n);
        } else if (rep == 3) {
            return new AppuiSimple(n, SegmentTerrain.demandeSegmentTerain());
        } else if (rep == 4) {
            return new AppuiDouble(n, SegmentTerrain.demandeSegmentTerain());
        } else {
            return null;
        }
    }

    public static Noeud demandeNoeud(SegmentTerrain segT) {

        System.out.println("Voulez-vous créer un appui simple (3) ou double (4) ? repondre 3 ou 4");
        int rep = Lire.i();

        System.out.println(
                "abscisse : ");
        double abs = Lire.d();

        System.out.println(
                "ordonnée : ");
        double ord = Lire.d();

        Point n = new Point(abs, ord);
        if (rep == 3) {
            return new AppuiSimple(n, segT);
        } else if (rep == 4) {
            return new AppuiDouble(n, segT);
        } else {
            return null;
        }
    }
    
    public String saveColor(Color c) {
        return c.getRed()+";"+c.getGreen()+";"+c.getBlue();
    }
    
    public abstract void dessine(GraphicsContext context);
    public abstract void dessineSelect(GraphicsContext context);
    
    public double distancePoint(Point p) {
        double dx = this.position.getPX() - p.getPX();
        double dy = this.position.getPY() - p.getPY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setForceY(double forceY) {
        this.forceY = forceY;
    }
    
    
}
