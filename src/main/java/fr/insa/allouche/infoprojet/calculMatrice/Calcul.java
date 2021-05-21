/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

import fr.insa.allouche.infoprojet.AppuiDouble;
import fr.insa.allouche.infoprojet.AppuiSimple;
import fr.insa.allouche.infoprojet.Barre;
import fr.insa.allouche.infoprojet.Identificateur;
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
        double An = (Math.atan2(A.getappartient().getDebut().getPY() - A.getappartient().getFin().getPY(), A.getappartient().getDebut().getPX() - A.getappartient().getFin().getPX()) - Math.atan2(0, -1));
        return An;
    }

    public static double pAngle(Noeud P, Noeud Q) {
        double angle = (Math.atan2(Q.position.getPY() - P.position.getPY(), Q.position.getPX() - P.position.getPX()) - Math.atan2(0, 1));

        return angle;

    }

    public static Matrice Calcul(Treillis T) {

        Matrice Total = new Matrice(2 * T.getContient().size(), T.getIdentite().getObjetVersId().size()+1);
        Matrice Membre2 = new Matrice(2 * T.getContient().size(), 1);
        double epsilon_pivot = 0.00000001;

        //Ligne de la matrice
        for (int i = 0; i < T.getAdoub().size() * 2; i = i + 2) {
            for (int k = 0; k < T.getAdoub().size(); k++) {
                AppuiDouble AD = T.getAdoub().get(k);
                for (int j = 0; j < AD.getLiee().size(); j++) {
                    if (AD == AD.getLiee().get(j).getDebut()) {
                        Total.coeffs[i][AD.getLiee().get(j).getId()] = Math.cos(pAngle(AD, AD.getLiee().get(j).getFin()));
                        Total.coeffs[i + 1][AD.getLiee().get(j).getId()] = -Math.sin(pAngle(AD, AD.getLiee().get(j).getFin()));
                    } else {
                        Total.coeffs[i][AD.getLiee().get(j).getId()] = Math.cos(pAngle(AD, AD.getLiee().get(j).getDebut()));
                        Total.coeffs[i + 1][AD.getLiee().get(j).getId()] = -Math.sin(pAngle(AD, AD.getLiee().get(j).getDebut()));
                    }
                }

                Total.coeffs[i][T.getRx().get(k).getIdRx()] = 1;
                Total.coeffs[i + 1][T.getRy().get(k).getIdRy()] = 1;//Problème Classe
                //AJOUTER FORCE REACTION EN X ET EN Y
            }
        }
        for (int i = T.getAdoub().size() * 2; i < (T.getAdoub().size() + T.getAsimp().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getAsimp().size(); k++) {
                AppuiSimple AS = T.getAsimp().get(k);
                for (int j = 0; j < AS.getLiee().size(); j++) {
                    if (AS == AS.getLiee().get(j).getDebut()) {
                        Total.coeffs[i][AS.getLiee().get(j).getId()] = Math.cos(pAngle(AS, AS.getLiee().get(j).getFin()));// + PangleTerrain(T.getAsimp().get(k));
                        Total.coeffs[i + 1][AS.getLiee().get(j).getId()] = -Math.sin(pAngle(AS, AS.getLiee().get(j).getFin()));// + PangleTerrain(T.getAsimp().get(k));
                    } else {
                        Total.coeffs[i][AS.getLiee().get(j).getId()] = Math.cos(pAngle(AS, AS.getLiee().get(j).getDebut()));// + PangleTerrain(T.getAsimp().get(k));
                        Total.coeffs[i + 1][AS.getLiee().get(j).getId()] = -Math.sin(pAngle(AS, AS.getLiee().get(j).getDebut()));// + PangleTerrain(T.getAsimp().get(k));
                    }
                }
                Total.coeffs[i][T.getRx().get(k + T.getAdoub().size()).getIdRx()] = Math.cos(PangleTerrain(AS) + (Math.PI / 2));
                Total.coeffs[i + 1][T.getRx().get(k + T.getAdoub().size()).getIdRx()] = -Math.sin(PangleTerrain(AS) + (Math.PI / 2));

                //AJOUTER FORCE REACTION X ET Y
            }
        }
        for (int i = (T.getAdoub().size() + T.getAsimp().size()) * 2; i < (T.getContient().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getSimp().size(); k++) { //Ajoute tous les noeuds simples
                NoeudSimple ns = T.getSimp().get(k);
                for (int j = 0; j < ns.getLiee().size(); j++) {// Affiche les barres liées au Noeud Simple au dessus
                    if (ns == ns.getLiee().get(j).getDebut()) {
                        Total.coeffs[i][ns.getLiee().get(j).getId()] = Math.cos(pAngle(ns, ns.getLiee().get(j).getFin()));
                        Total.coeffs[i + 1][ns.getLiee().get(j).getId()] = -Math.sin(pAngle(ns, ns.getLiee().get(j).getFin()));
                    } else {
                        Total.coeffs[i][ns.getLiee().get(j).getId()] = Math.cos(pAngle(ns, ns.getLiee().get(j).getDebut()));
                        Total.coeffs[i + 1][ns.getLiee().get(j).getId()] = -Math.sin(pAngle(ns, ns.getLiee().get(j).getDebut()));
                    }

                }
            }
        }

        for (int i = 0; i < 2 * T.getContient().size(); i++) {
            for (int j = 0; j < T.getIdentite().getObjetVersId().size()+1; j++) {
                if (Total.coeffs[i][j] < epsilon_pivot && Total.coeffs[i][j] > 0) {
                    Total.coeffs[i][j] = 0;
                }
            }
        }

        return Total;
    }

    public static Matrice Membre2(Treillis T) {
        Matrice Membre2 = new Matrice(2 * T.getContient().size(), 1);

        for (int i = 0; i < T.getAdoub().size() * 2; i = i + 2) {
            for (int k = 0; k < T.getAdoub().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getAdoub().get(k).forceY;
            }
        }
        for (int i = T.getAdoub().size() * 2; i < (T.getAdoub().size() + T.getAsimp().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getAsimp().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getAsimp().get(k).forceY;
            }
        }

        for (int i = (T.getAdoub().size() + T.getAsimp().size()) * 2; i < (T.getContient().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getSimp().size(); k++) {
                Membre2.coeffs[i + 1][0] = T.getSimp().get(k).forceY;
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

    public static Matrice Lien(Matrice M) {
        Matrice K = new Matrice(M.getNbrLig(), 1);
        int H = 0;
        for (int i = 0; i < M.getNbrCol(); i++) {
            if (M.Max(i) != -1) {
                K.coeffs[H][0] = i;
                H++;
            }
        }

        return K;
    }

    public static String Regroup(Treillis T) {
        for (int i = 0; i < T.getRx().size(); i++) {
            T.getRx().get(i).setIdRx(T.getIdentite().getOrCreateId(T.getRx().get(i)));
        }

        for (int i = 0; i < T.getRy().size(); i++) {
            T.getRy().get(i).setIdRy(T.getIdentite().getOrCreateId(T.getRy().get(i)));
        }

        Matrice M = Calcul(T);
        Matrice N = Création(M);
        System.out.println(" Création : \n" + N.toString());
        Matrice A = Lien(M);
        Matrice Membre2 = Membre2(T);
        Matrice H = N.concatCol(Membre2);
        System.out.println("Concat H : \n" + H.toString());
        ResSup Z = H.resolution(Membre2);
        System.out.println("Z: \n" + Z.toString());
        Matrice K = Z.getSet().concatCol(A);
        String s = "";
        if (Z.verifSolUnique() == false) {
            s = "Le treillis n'est pas isostatique";
        }

        for (int i = 0; i < K.getNbrLig(); i++) {
            double m = K.coeffs[i][1];
            for (int k = 0; k < T.getCompose().size(); k++) {
                if (T.getCompose().get(k).getId() == m) {
                    if (T.getCompose().get(k).getType().getrComp() < Math.abs(K.coeffs[i][0])
                            || T.getCompose().get(k).getType().getrTraction() < K.coeffs[i][0]) {
                        if (K.coeffs[i][0] <= 0) {
                            if (T.getCompose().get(k).getType().getrComp() < Math.abs(K.coeffs[i][0])) {
                                s = s + "La barre n°" + T.getCompose().get(k).getId() + " a une trop faible compression.\n";
                            }
                        } else {
                            if (T.getCompose().get(k).getType().getrComp() > K.coeffs[i][0]) {
                                s = s + "La barre n°" + T.getCompose().get(k).getId() + " a une trop forte traction.\n";
                            }
                        }
                    }
                }
            }
        }

        if (s == "") {
            s = s + "Les types de barres sont bons!";
        }

        return s;
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
        NoeudSimple nS1 = new NoeudSimple(pos9);
        Treillis res = new Treillis();
        Terrain t1 = new Terrain(pos1, pos2, pos3, pos4);
        SegmentTerrain seg1 = new SegmentTerrain(pos5, pos6);
        SegmentTerrain seg2 = new SegmentTerrain(pos5, pos7);
        SegmentTerrain seg3 = new SegmentTerrain(pos8, pos7);
        TriangleTerrain tT1 = new TriangleTerrain(seg1, seg2, seg3);
        AppuiDouble AD1 = new AppuiDouble(pos10, seg1);
        AppuiSimple AS1 = new AppuiSimple(pos11, seg1);
        Barre b1 = new Barre(nS1, AD1, 20, 20, 70, 900, 900);
        Barre b2 = new Barre(nS1, AS1, 20, 20, 70, 900, 900);
        Barre b3 = new Barre(AD1, AS1, 20, 20, 70, 900, 900);
//        Reaction_Rx ADX = new Reaction_Rx(AD1);
//        Reaction_Ry ADY = new Reaction_Ry(AD1);
//        Reaction_Rx ASX = new Reaction_Rx(AS1);

        res.addTerrain(t1);
        t1.addTriangleTerrain(tT1);
        res.addAppuiDouble(AD1);
        res.addAppuiSimple(AS1);
        res.addNoeudSimple(nS1);
        res.addBarre(b1);
        res.addBarre(b2);
        res.addBarre(b3);
//        res.getRx().add(ASX);
//        res.getRx().add(ADX);
//        res.getRy().add(ADY);

//        tT1.setId(0);
//        seg1.setId(1);
//        seg2.setId(2);
//        seg3.setId(3);
//        AD1.setId(4);
//        AS1.setId(5);
//        nS1.setId(6);
//        b1.setId(7);
//        b2.setId(8);
//        b3.setId(9);
//        ADX.setIdRx(10);
//        ADY.setIdRy(11);
//        ASX.setIdRx(12);
        AD1.forceY = 0;
        AS1.forceY = 0;
        nS1.forceY = 1000;

        for (int i = 0; i < res.getRx().size(); i++) {
            res.getRx().get(i).setIdRx(res.getIdentite().getOrCreateId(res.getRx().get(i)));
        }

        for (int i = 0; i < res.getRy().size(); i++) {
            res.getRy().get(i).setIdRy(res.getIdentite().getOrCreateId(res.getRy().get(i)));
        }

        Matrice M = Calcul(res);
        Matrice N = Création(M);
        Matrice A = Lien(M);
        Matrice Membre2 = Membre2(res);
        Matrice H = N.concatCol(Membre2);
        ResSup Z = H.resolution(Membre2);
        Matrice K = Z.getSet().concatCol(A);

        System.out.println(Regroup(res));

    }
}
