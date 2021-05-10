/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author stanislasallouche
 */
public class NoeudSimple extends Noeud {

    public static double RAYON_NOEUD = 5;

    public NoeudSimple(Point position) {
        super(position, Color.CRIMSON);
    }

    public NoeudSimple() {
        super(new Point(), Color.CRIMSON);
    }
    
    public NoeudSimple(Point position, Color color) {
        super(position, color);
    }

    public void sommeX() {

    }

    public void sommeY() {
    }

    public String toString() {
        String res = "NoeudSimple " + this.getId() + " [";
        res = res + this.getPosition().getPX()
                + " , " + this.getPosition().getPY() + "]";
        return res;
    }

    @Override
    public void dessine(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillOval(this.position.getPX() - RAYON_NOEUD,
                this.position.getPY() - RAYON_NOEUD, 2 * RAYON_NOEUD, 2 * RAYON_NOEUD);

    }
}
