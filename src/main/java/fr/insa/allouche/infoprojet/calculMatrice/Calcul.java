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
import fr.insa.allouche.infoprojet.Reaction_Rx;
import fr.insa.allouche.infoprojet.Reaction_Ry;
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
        double An = (Math.atan2(A.getappartient().getFin().getPY() - A.getappartient().getDebut().getPY(), A.getappartient().getFin().getPX() - A.getappartient().getDebut().getPX()) - Math.atan2(0, 1));
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
     2 * T.getContient().size()*/
    public static Matrice Calcul(Treillis T) {

        Matrice Total = new Matrice(2 * T.getContient().size(), T.getContient().size() + T.getCompose().size() + T.getBase().getConstitue().size() * 6 + T.getCatalogueBarre().size());
        Matrice Membre2 = new Matrice(2 * T.getContient().size(), 1);
        double epsilon_pivot = 0.00000001;

        //Ligne de la matrice
        for (int i = 0; i < T.getAdoub().size() * 2; i = i + 2) {
            for (int k = 0; k < T.getAdoub().size(); k++) {
                for (int j = 0; j < T.getAdoub().get(k).getLiee().size(); j++) {
                    if (T.getAdoub().get(k) == T.getAdoub().get(k).getLiee().get(j).getDebut()) {
                        Total.coeffs[i][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getFin()));
                        Total.coeffs[i + 1][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getFin()));
                    } else {
                        Total.coeffs[i][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getDebut()));
                        Total.coeffs[i + 1][T.getAdoub().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAdoub().get(k), T.getAdoub().get(k).getLiee().get(j).getDebut()));
                    }
                }

                Total.coeffs[i][T.getRx().get(k).getIdRx()] = 1;
                Total.coeffs[i + 1][T.getRy().get(k).getIdRy()] = 1;//Problème Classe
                //AJOUTER FORCE REACTION EN X ET EN Y
            }
        }
        for (int i = T.getAdoub().size() * 2; i < (T.getAdoub().size() + T.getAsimp().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getAsimp().size(); k++) {
                for (int j = 0; j < T.getAsimp().get(k).getLiee().size(); j++) {
                    if (T.getAsimp().get(k) == T.getAsimp().get(k).getLiee().get(j).getDebut()) {
                        Total.coeffs[i][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getFin()));// + PangleTerrain(T.getAsimp().get(k));
                        Total.coeffs[i + 1][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getFin()));// + PangleTerrain(T.getAsimp().get(k));
                    } else {
                        Total.coeffs[i][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getDebut()));// + PangleTerrain(T.getAsimp().get(k));
                        Total.coeffs[i + 1][T.getAsimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getAsimp().get(k), T.getAsimp().get(k).getLiee().get(j).getDebut()));// + PangleTerrain(T.getAsimp().get(k));
                    }
                }
                Total.coeffs[i][T.getRx().get(k + T.getAdoub().size()).getIdRx()] = Math.cos(PangleTerrain(T.getAsimp().get(k)) + (Math.PI / 2));
                Total.coeffs[i + 1][T.getRx().get(k + T.getAdoub().size()).getIdRx()] = Math.sin(PangleTerrain(T.getAsimp().get(k)) + (Math.PI / 2));
          
                //AJOUTER FORCE REACTION X ET Y
            }
        }
        for (int i = (T.getAdoub().size() + T.getAsimp().size()) * 2; i < (T.getContient().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getSimp().size(); k++) { //Ajoute tous les noeuds simples
                for (int j = 0; j < T.getSimp().get(k).getLiee().size(); j++) {// Affiche les barres liées au Noeud Simple au dessus
                    if (T.getSimp().get(k) == T.getSimp().get(k).getLiee().get(j).getDebut()) {
                        Total.coeffs[i][T.getSimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getSimp().get(k), T.getSimp().get(k).getLiee().get(j).getFin()));
                        Total.coeffs[i + 1][T.getSimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getSimp().get(k), T.getContient().get(k).getLiee().get(j).getFin()));
                    } else {
                        Total.coeffs[i][T.getSimp().get(k).getLiee().get(j).getId()] = Math.cos(pAngle(T.getSimp().get(k), T.getSimp().get(k).getLiee().get(j).getDebut()));
                        Total.coeffs[i + 1][T.getSimp().get(k).getLiee().get(j).getId()] = Math.sin(pAngle(T.getSimp().get(k), T.getContient().get(k).getLiee().get(j).getDebut()));
                    }

                }
            }
        }

        for (int i = 0; i < 2 * T.getContient().size(); i++) {
            for (int j = 0; j < T.getContient().size() + T.getCompose().size() + T.getBase().getConstitue().size() * 6 + T.getCatalogueBarre().size(); j++) {
                if (Total.coeffs[i][j] < epsilon_pivot && Total.coeffs[i][j] > 0) {
                    Total.coeffs[i][j] = 0;
                }
            }
        }

        return Total;
    }

    public static Matrice Membre2(Treillis T) {
        Matrice Membre2 = new Matrice(2 * T.getContient().size(), 1);
        for (int i = 0; i < T.getSimp().size(); i = i + 2) {
            for (int k = 0; k < T.getSimp().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getSimp().get(k).forceY;
            }
        }
        for (int i = T.getSimp().size() * 2; i < (T.getSimp().size() + T.getAdoub().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getAdoub().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getAdoub().get(k).forceY;
            }
        }
        for (int i = (T.getSimp().size() + T.getAdoub().size()) * 2; i < (T.getContient().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getAsimp().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getAsimp().get(k).forceY;
            }
        }
        return Membre2;
    }

    public static Matrice Création(Matrice M) {
        Matrice N = new Matrice(M.getNbrLig(), M.getNbrLig());
        int H = 0;
        for (int i = 0; i < M.getNbrCol(); i++) {
            if (M.Max(i) != -1) {
                for (int k = 0; k < M.getNbrLig(); k++) {
                    N.coeffs[k][H] = M.coeffs[k][i];
                }
                H++;
            }
        }

        return N;
    }

    public static void main(String[] args) {
        Point pos1 = new Point(0, 5);
        Point pos2 = new Point(4, 0);
        Point pos3 = new Point(-5, 0);
        Point pos4 = new Point(1, 0);
        Point pos5 = new Point(0, 0);
        Point pos6 = new Point(0, -6);
        Point pos7 = new Point(2, 0);
        Point pos8 = new Point(-3, 0);
        Point pos9 = new Point(1, 1);
        Point pos10 = new Point(0, 0);
        Point pos11 = new Point(0, 2);
        Point pos12 = new Point(7, 9);
        NoeudSimple nS1 = new NoeudSimple(pos9);
        Treillis res = new Treillis();
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos5, pos6);
        SegmentTerrain seg2 = new SegmentTerrain(pos5, pos7);
        SegmentTerrain seg3 = new SegmentTerrain(pos8, pos7);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        AppuiDouble AD1 = new AppuiDouble(pos10, seg1);
        AppuiSimple AS1 = new AppuiSimple(pos11, seg1);
        Barre b1 = new Barre(AD1, nS1);
        Barre b2 = new Barre(AS1, nS1);
        Barre b3 = new Barre(AD1, AS1);
        Reaction_Rx ADX = new Reaction_Rx(AD1);
        Reaction_Ry ADY = new Reaction_Ry(AD1);
        Reaction_Rx ASX = new Reaction_Rx(AS1);

        res.addTerrain(t1);
        t1.addTriangleTerrain(tT1);
        res.addAppuiDouble(AD1);
        res.addAppuiSimple(AS1);
        res.addNoeudSimple(nS1);
        res.addBarre(b1);
        res.addBarre(b2);
        res.addBarre(b3);
        res.getRx().add(ASX);
        res.getRx().add(ADX);
        res.getRy().add(ADY);

        tT1.setId(0);
        seg1.setId(1);
        seg2.setId(2);
        seg3.setId(3);
        AD1.setId(4);
        AS1.setId(5);
        nS1.setId(6);
        b1.setId(7);
        b2.setId(8);
        b3.setId(9);
        ADX.setIdRx(10);
        ADY.setIdRy(11);
        ASX.setIdRx(12);
        AD1.forceY = 0;
        AS1.forceY = 0;
        nS1.forceY = -1000;

        Matrice M = Calcul(res);
        Matrice N = Création(M);
        Matrice Membre2 = Membre2(res);

        System.out.println(N.toString());

        //Matrice K = SommeNoeudS(nS1);
        //System.out.println(K);
        // System.out.println(M + "T" );
    }

}
