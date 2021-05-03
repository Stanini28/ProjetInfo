/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

import fr.insa.allouche.infoprojet.AppuiDouble;
import fr.insa.allouche.infoprojet.AppuiSimple;
import fr.insa.allouche.infoprojet.Barre;
import fr.insa.allouche.infoprojet.Noeud;
import fr.insa.allouche.infoprojet.NoeudAppui;
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;
import fr.insa.allouche.infoprojet.SegmentTerrain;
import fr.insa.allouche.infoprojet.Terrain;
import fr.insa.allouche.infoprojet.Treillis;
import fr.insa.allouche.infoprojet.TriangleTerrain;
import fr.insa.allouche.infoprojet.TypeBarre;

/**
 *
 * @author stanislasallouche
 */
public class Calcul {

    public static int NoeudAPPl(NoeudAppui S) {
        return S.getappartient().getId();
    }

    public static double PangleTerrain(NoeudAppui A) {
        double An = (Math.atan2(A.getappartient().getDebut().getPY() - A.getappartient().getFin().getPY(), A.getappartient().getDebut().getPX() - A.getappartient().getFin().getPX()) - Math.atan2(0, 1));
        return An;
    }

    public static double pAngle(Noeud P, Noeud Q) {
        double angle = (Math.atan2(Q.position.getPY() - P.position.getPY(), Q.position.getPX() - P.position.getPX()) - Math.atan2(0, 1));

        return angle;

    }
    

    /*
    public static Matrice SommeNoeudS(NoeudSimple S) {
        Matrice Mns = new Matrice(2, S.getLiee().size() + 1);

        for (int j = 0; j < S.getLiee().size(); j++) {

            Mns.coeffs[0][j] = Math.cos(pAngle(S, S.getLiee().get(j).getFin())); //force de traction en X
            Mns.coeffs[1][j] = Math.sin(pAngle(S, S.getLiee().get(j).getFin()));// Force de de traction en Y

        }
        Mns.coeffs[1][S.getLiee().size()] = S.forceY;

        return Mns;
    }

    public static Matrice SommeAppuiSimple(AppuiSimple A) {
        Matrice M = new Matrice(2, A.getLiee().size() + 2);

        for (int j = 0; j < A.getLiee().size(); j++) {
            M.coeffs[0][j] = Math.cos(pAngle(A, A.getLiee().get(j).getFin()));
            M.coeffs[1][j] = Math.sin(pAngle(A, A.getLiee().get(j).getFin()));
        }

        M.coeffs[1][A.getLiee().size()] = A.forceY;
        M.coeffs[0][A.getLiee().size() + 1] = Math.cos(pAngle(A, A) + Math.PI / 2);
        M.coeffs[1][A.getLiee().size() + 1] = Math.sin(pAngle(A, A) + Math.PI / 2);

        return M;
    }

    public static Matrice SommeAppuiDouble(AppuiDouble A) {
        Matrice K = new Matrice(2, A.getLiee().size() + 2);

        for (int i = 0; i < A.getLiee().size(); i++) {
            K.coeffs[0][i] = Math.cos(pAngle(A, A.getLiee().get(i).getFin()));
            K.coeffs[1][i] = Math.sin(pAngle(A, A.getLiee().get(i).getFin()));
        }

        K.coeffs[1][A.getLiee().size()] = A.forceY;
        K.coeffs[0][A.getLiee().size() + 1] = 1;
        K.coeffs[1][A.getLiee().size() + 1] = 1;

        return K;
    }
     */
    public static Matrice Calcul(Treillis T) {

        Matrice Total = new Matrice(2 * T.getContient().size(), T.getContient().size() + T.getCompose().size() + T.getBase().getConstitue().size() * 3 + T.getCatalogueBarre().size());
        Matrice Membre2 = new Matrice(2 * T.getContient().size(), 1);

        for (int i = 0; i < T.getContient().size(); i = i + 2) {//Ligne de la matrice
            
            for (int k = 0; k < T.getSimp().size(); k++) { //Ajoute tous les noeuds simples
                for (int j = 0; j < T.getSimp().get(k).getLiee().size(); j++) { // Affiche les barres liées au Noeud Simple au dessus
                    Total.coeffs[i][T.getSimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getSimp().get(k), T.getSimp().get(k).getLiee().get(j).getFin()));
                    Total.coeffs[i + 1][T.getSimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getSimp().get(k), T.getContient().get(k).getLiee().get(j).getFin()));
                }
                Membre2.coeffs[i+1][0] = T.getSimp().get(k).forceY;
            }

            
           for (int k=0;k<T.getAdoub().size();k++){
                for (int j = 0; j < T.getAdoub().get(k).getLiee().size(); j++) {
                    Total.coeffs[i][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getFin())) + PangleTerrain(T.getAdoub().get(k));
                    Total.coeffs[i + 1][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getFin())) + PangleTerrain(T.getAdoub().get(k));
                }
                Total.coeffs[i][T.getAdoub().get(k).getappartient().getId()] = 1;
                Total.coeffs[i + 1][T.getAdoub().get(k).getappartient().getId()] = 1;//Problème Classe
                Membre2.coeffs[i+1][0] = T.getAdoub().get(k).forceY;
                //AJOUTER FORCE REACTION EN X ET EN Y

            }

            for (int k=0;k< T.getAsimp().size();k++){
                for (int j = 0; j < T.getAsimp().get(k).getLiee().size(); j++) {
                    Total.coeffs[i][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getFin())) + PangleTerrain(T.getAsimp().get(k));
                    Total.coeffs[i + 1][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getFin())) + PangleTerrain(T.getAsimp().get(k));
                }
                Total.coeffs[i][T.getAsimp().get(k).getappartient().getId()] = Math.cos(PangleTerrain(T.getAsimp().get(k)) + Math.PI / 2);
                Total.coeffs[i + 1][T.getAsimp().get(k).getappartient().getId()] = Math.cos(PangleTerrain(T.getAsimp().get(k)) + Math.PI / 2);
                Membre2.coeffs[i+1][0] = T.getAdoub().get(k).forceY;
                //AJOUTER FORCE REACTION X ET Y

            }
        }
    

return Total;
}  

    public static void main(String[] args) {
        Point pos1 = new Point();
        Point pos2 = new Point(20, 30);
        Point pos3 = new Point(40, 50);
        Point pos4 = new Point(10, 40);
        Point pos5 = new Point(60, 50);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos4);
        NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);
        Barre b1 = new Barre(nS1, nS2);
        Barre b2 = new Barre(nS4, nS5);
        Barre b3 = new Barre(nS4, nS1, 5, 25, 56, 800, 1000);
        Treillis res = new Treillis();
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos1, pos2);
        SegmentTerrain seg2 = new SegmentTerrain(pos2, pos3);
        SegmentTerrain seg3 = new SegmentTerrain(pos3, pos1);
        SegmentTerrain seg4 = new SegmentTerrain(pos2, pos4);
        SegmentTerrain seg5 = new SegmentTerrain(pos4, pos1);
        AppuiDouble nAD2 = new AppuiDouble(pos2, seg1);
        AppuiSimple nAS3 = new AppuiSimple(pos3, seg2);
        AppuiSimple nAS1 = new AppuiSimple (0.9, seg3);
        Barre bAd = new Barre(nAD2, nAS3, 6, 7, 78, 567, 789);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        TriangleTerrain tT2 = new TriangleTerrain(pos2, pos4, pos1);
        TypeBarre tB1 = new TypeBarre(2, 30, 55, 550, 500);
        TypeBarre tB2 = new TypeBarre();
        t1.addTriangleTerrain(tT1);
        t1.addTriangleTerrain(tT2);
//        res.addBarre(b1);
//        res.addBarre(b2);
        res.addTerrain(t1);
        res.addBarre(b3);
        res.addBarre(bAd);
        res.addNoeudSimple(nS1);
        res.addNoeudSimple(nS2);
        res.addNoeudSimple(nS4);
        res.addNoeudSimple(nS5);
        res.addAppuiDouble(nAD2);
        res.addAppuiSimple(nAS3);
        res.addTypeBarre(tB1);
        res.addTypeBarre(tB2);
        
        Matrice M = Calcul(res);
        
        System.out.println(M.toString());

        //Matrice K = SommeNoeudS(nS1);
        //System.out.println(K);
        // System.out.println(M + "T" );
    }

}
