/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

import fr.insa.allouche.infoprojet.AppuiSimple;
import fr.insa.allouche.infoprojet.Barre;
import fr.insa.allouche.infoprojet.Noeud;
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;
import fr.insa.allouche.infoprojet.SegmentTerrain;

/**
 *
 * @author stanislasallouche
 */
public class Calcul {

    double Rx;
    double Ry;

    public static double pAngle(Noeud P, Noeud Q) {
        double angle = (Math.atan2(Q.position.getPY() - P.position.getPY(), Q.position.getPX() - P.position.getPX()) - Math.atan2(0, 1));

        return angle;

    } // Fonctionne sûr sûr 

    public static Matrice SommeNoeudS(NoeudSimple P) {
        Matrice Mns = new Matrice(2, P.getLiee().size() + 1);

        for (int j = 0; j < P.getLiee().size(); j++) {

            Mns.coeffs[0][j] = Math.cos(pAngle(P, P.getLiee().get(j).getFin())); //force de traction en X
            Mns.coeffs[1][j] = Math.sin(pAngle(P, P.getLiee().get(j).getFin()));// Force de de traction en Y

            Mns.coeffs[1][P.getLiee().size()] = P.forceY;

        }

        return Mns;
    }

    public static Matrice SommeAppuiSimple(AppuiSimple A) {
        Matrice M = new Matrice(2, A.getLiee().size() + 2);

        for (int j = 0; j < A.getLiee().size(); j++) {
            M.coeffs[0][j] = Math.cos(pAngle(A, A.getLiee().get(j).getFin()));
            M.coeffs[1][j] = Math.sin(pAngle(A, A.getLiee().get(j).getFin()));

            M.coeffs[1][A.getLiee().size()] = A.forceY;
            M.coeffs[0][A.getLiee().size() + 1] = 1 ;
            M.coeffs[1][A.getLiee().size() + 1] = 1 ;

        }
        return M;
    }

    public static void main(String[] args) {
        Point pos1 = new Point(2, 0);
        Point pos2 = new Point(9, 0);
        Point pos3 = new Point(6, 0);
        Point pos4 = new Point(4, 0);
        Point pos5 = new Point(1, 4);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos3);
        NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);

        Barre b3 = new Barre(nS4, nS1, 5, 25, 56, 800, 1000);
        SegmentTerrain seg4 = new SegmentTerrain(pos2, pos4);
        AppuiSimple nAS3 = new AppuiSimple(pos3, seg4);
        nAS3.forceY = 9;
        Barre b1 = new Barre(nAS3, nS1);
        Barre b2 = new Barre(nAS3, nS5);
        //Matrice M = SommeNoeudS(nS1);
        
        Matrice N = SommeAppuiSimple(nAS3);

        System.out.println(N.toString());

    }

}
