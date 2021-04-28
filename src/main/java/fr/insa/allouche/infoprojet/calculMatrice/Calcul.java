/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

import fr.insa.allouche.infoprojet.Noeud;
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;

/**
 *
 * @author stanislasallouche
 */
public class Calcul {

    public static double pAngle(Point O, Point X, Noeud P, Noeud Q) {
        double angle = Math.atan2(O.getPY() - X.getPY(), O.getPX() - X.getPX()) - Math.atan2(P.position.getPY() - Q.position.getPY(), P.position.getPX() - Q.position.getPX());

        angle = angle * Math.PI / 180;
        return angle;

       
    }

    public void SommeNoeudS(NoeudSimple P) {
        Point O= new Point(0,0);
        Point X= new Point(0,1);
        Point Y=new Point(1,0);
        
        

        Matrice Mns = new Matrice(2, P.getLiee().size()+1);

        for (int j = 0; j < P.getLiee().size(); j++) {
            Mns.coeffs[0][j] = P.liee.get(j).Fx * Math.cos(pAngle(O, X, P, P.liee.get(j).getDebut())); //force de traction en X
            Mns.coeffs[1][j] = P.liee.get(j).Fy * Math.sin(pAngle(O, X, P, P.liee.get(j).getDebut()));// Force de de traction en Y

            Mns.coeffs[1][P.getLiee().size()+1] = P.forceY;
        }

    }

}
