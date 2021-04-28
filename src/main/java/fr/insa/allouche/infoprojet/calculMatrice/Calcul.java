/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

import fr.insa.allouche.infoprojet.Barre;
import fr.insa.allouche.infoprojet.Noeud;
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;

/**
 *
 * @author stanislasallouche
 */
public class Calcul {

    public static double pAngle(Point O, Point X, Noeud P, Noeud Q) {
        double angle = (Math.atan2(Q.position.getPY() - P.position.getPY(), Q.position.getPX() - P.position.getPX()) - Math.atan2(X.getPY() - O.getPY(), X.getPX() - O.getPX()));

        return angle;

    } // Fonctionne sûr sûr 

    public static Matrice SommeNoeudS(NoeudSimple P) {
        Point O = new Point(0, 0);
        Point X = new Point(1, 0);
        Point Y = new Point(0, 1);
        Matrice Mns = new Matrice(2, P.getLiee().size() + 1);

        for (int j = 0; j < P.getLiee().size(); j++) {

            Mns.coeffs[0][j] = Math.cos(pAngle(O, X, P, P.getLiee().get(j).getFin())); //force de traction en X
            Mns.coeffs[1][j] = Math.sin(pAngle(O, X, P, P.getLiee().get(j).getFin()));// Force de de traction en Y

            Mns.coeffs[1][P.getLiee().size()] = P.forceY;

        }

        return Mns;
    }

    public static void main(String[] args) {
        Point pos1 = new Point(3, 2);
        Point pos2 = new Point(6, 3);
        Point O = new Point(0, 0);
        Point X = new Point(1, 0);
        Point pos5 = new Point(-1, -1);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos2);
        //NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);
        Barre b1 = new Barre(nS1, nS2);
        Barre b2 = new Barre (nS1, nS5);
        nS1.forceY = 9;

        Matrice M = SommeNoeudS(nS1);

  
                System.out.println(M.toString());
            
        

    }

}
