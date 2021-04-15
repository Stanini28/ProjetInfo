/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;



public class SegmentTerrain {

    public SegmentTerrain() {
    }

    public static void pCaract(Point P, Point Q, Point R) {
        double angle = Math.atan2(P.getPX() - Q.getPY(), P.getPX() - Q.getPX()) - Math.atan2(P.getPY() - R.getPY(), P.getPX() - R.getPX());

        angle = angle * Math.PI / 180;

        if (angle == 0) {
            System.out.println("Colinéaire");
        } else {
            if (0 < angle && angle < Math.PI) {
                System.out.println("Positif");
            } else {
                System.out.println("Négatif");

            }

        }

    }

}
