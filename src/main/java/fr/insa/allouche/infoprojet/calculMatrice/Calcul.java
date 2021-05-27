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
import fr.insa.allouche.infoprojet.Treillis;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

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
            System.out.println("TAILLE DU IDVERSOBJET" + T.getIdentite().getIdVersObjet().size());
            System.out.println("TAILLE DU OBJETVERSID" + T.getIdentite().getObjetVersId().size());
        
        Matrice Total = new Matrice(2 * T.getContient().size(), T.getIdentite().getObjetVersId().size());
        double epsilon_pivot = 0.00000001;

        //Ajout de tous les Appuis Doubles qu'il y a sur le Treillis
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
                Total.coeffs[i + 1][T.getRy().get(k).getIdRy()] = 1;
            }
        }

        //Ajout de tous les Appuis simples qu'il y a sur le Treillis
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

        //Ajout de tous les noeuds simples qu'il y a sur la Treillis
        for (int i = (T.getAdoub().size() + T.getAsimp().size()) * 2; i < (T.getContient().size()) * 2; i = i + 2) {
            for (int k = 0; k < T.getSimp().size(); k++) {
                NoeudSimple ns = T.getSimp().get(k);
                for (int j = 0; j < ns.getLiee().size(); j++) {
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

        //Cela permet de remplacer les erreurs d'arrondis (4E-18) par 0
        int h=0;
        for (int i = 0; i < 2 * T.getContient().size(); i++) {
            for (int j = 0; j <T.getIdentite().getObjetVersId().size(); j++) {
                if (Total.coeffs[i][j] < epsilon_pivot && Total.coeffs[i][j] > 0) {
                    Total.coeffs[i][j] = 0;
                    h=h+1;
                    
                }
            }
        }
        for (int i=0;i<Total.getNbrLig();i++){
            Total.coeffs[i][0]=0;
        }
        
        System.out.println(h);
        return Total;
    }

    //Permet la création du Membre2 avec le poids des différents Noeuds
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

    //Permet la Création d'une matrice carrée à partir de la matrice du Treillis
    // qui était rectangulaire
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

    //Permet de faire le lien entre les différents résultats et les barres 
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

    //Permet de regrouper les différentes méthodes dans une seule ce qui nous facilitera
    //la tâche dans le controleur
    public static String regroup(Treillis T) {
        for (int i = 0; i < T.getRx().size(); i++) {
            T.getRx().get(i).setIdRx(T.getIdentite().getOrCreateId(T.getRx().get(i)));
        }

        for (int i = 0; i < T.getRy().size(); i++) {
            T.getRy().get(i).setIdRy(T.getIdentite().getOrCreateId(T.getRy().get(i)));
        }
String s = "";
        Matrice M = Calcul(T);
        System.out.println("M \n"+ M.toString());
        Matrice N = Création(M);
        Matrice A = Lien(M);
        Matrice Membre2 = Membre2(T);
        Matrice H = N.concatCol(Membre2);
        ResSup Z = H.resolution(Membre2);
        if (Z.getSet() == null){
            s = "Le treillis n'est pas isostatique!";
        }else{
        System.out.println("Z: \n" + Z.toString());
        Matrice K = Z.getSet().concatCol(A);
        if (Z.verifSolUnique() == false) {
            s = "Le treillis n'est pas isostatique!";
        }
        List BarreAPbC = new ArrayList ();
        List BarreAPbT = new ArrayList ();
        for (int i = 0; i < K.getNbrLig(); i++) {
            double m = K.coeffs[i][1];
            for (int k = 0; k < T.getCompose().size(); k++) {
                if (T.getCompose().get(k).getId() == m) {
                    if (T.getCompose().get(k).getType().getrComp() < Math.abs(K.coeffs[i][0])
                            || T.getCompose().get(k).getType().getrTraction() < K.coeffs[i][0]) {
                        if (K.coeffs[i][0] <= 0) {
                            if (Math.abs(T.getCompose().get(k).getType().getrComp()) < Math.abs(K.coeffs[i][0])) {
                                s = s + "\n - la barre n°" + T.getCompose().get(k).getId() + " a une trop forte compression.\n";
                                BarreAPbC.add(T.getCompose().get(k));
                                T.setBarrePbC(BarreAPbC);
                            }
                        } else {
                            if (T.getCompose().get(k).getType().getrTraction() < K.coeffs[i][0]) {
                                s = s + "\n - la barre n°" + T.getCompose().get(k).getId() + " a une trop forte traction.\n";

                                BarreAPbT.add(T.getCompose().get(k));
                                T.setBarrePbT(BarreAPbT);
                            }
                        }
                    }
                }
            }
        }
        }

        return s;
    }
    
    public static int Prix(Treillis T){
        double a=0;
        for (int i=0; i<T.getCompose().size();i++){
            Barre B = T.getCompose().get(i); 
            a = a + Barre.longueur(B.getDebut().getPosition(), B.getFin().getPosition()) * 
                    B.getType().getCoutAuMetre();
        }
        return (int) a;
}
}
