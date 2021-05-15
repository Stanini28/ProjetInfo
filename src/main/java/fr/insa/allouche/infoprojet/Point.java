/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import fr.insa.allouche.infoprojet.outils.Lire;
import java.io.IOException;
import java.io.Writer;

public class Point {

    private double PX;
    private double PY;

    public Point(double PX, double PY) {
        this.PX = PX;
        this.PY = PY;
    }

    public Point() {
        this(0, 0);
    }

    /**
     * @return the PX
     */
    public double getPX() {
        return PX;
    }

    /**
     * @return the PY
     */
    public double getPY() {
        return PY;
    }

    public void setPX(double Px) {
        this.PX = Px;
    }

    public void setPY(double Px) {
        this.PX = Px;
    }

    public String toString() {
        String res = "";
        res = "(" + this.PX + " ," + this.PY + ")";
        return res;
    }

    public static Point demandePoint() {
        System.out.println("abscisse : ");
        double ab = Lire.d();
        System.out.println("ordonnée : ");
        double ord = Lire.d();

        Point pt = new Point(ab, ord);
        return pt;
    }

    public void save(Writer w, Identificateur num) throws IOException {
        if (!num.objExist(this)) {
            int id = num.getOrCreateId(this);
            w.append("Point;" + id + ";" + this.PX + ";" + this.PY + "\n");
        }

    }
    

    public double distancePoint(Point p) {
        double dx = this.PX - p.PX;
        double dy = this.PY - p.PY;
        return Math.sqrt(dx * dx + dy * dy);

    }

}
