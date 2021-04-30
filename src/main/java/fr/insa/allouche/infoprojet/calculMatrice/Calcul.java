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
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;
import fr.insa.allouche.infoprojet.SegmentTerrain;
import fr.insa.allouche.infoprojet.Treillis;

/**
 *
 * @author stanislasallouche
 */
public class Calcul {

    private double Rx;
    private double Ry;
    private double Fx;
    private double Fy;
    private double Py;

    public static double pAngle(Noeud P, Noeud Q) {
        double angle = (Math.atan2(Q.position.getPY() - P.position.getPY(), Q.position.getPX() - P.position.getPX()) - Math.atan2(0, 1));

        return angle;

    } // Fonctionne sûr sûr 
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

        //int k = T.compose.size()+ 2 + T.contient.size() - nbr de noeud simple ;  
        int k = 999;

        Matrice Total = new Matrice(2 * T.contient.size(), k);

        for (int i = 0; i < T.contient.size(); i=i+2) {
            if (T.contient.get(i) instanceof NoeudSimple) {
                for (int j=0;j<T.contient.get(i).getLiee().size();j++){
                    
                Total.coeffs[i][T.contient.get(i).getLiee().get(j).getId()]= Math.cos(pAngle(T.contient.get(i), T.contient.get(i).getLiee().get(j).getFin()));
                Total.coeffs[i+1][T.contient.get(i).getLiee().get(j).getId()]= Math.sin(pAngle(T.contient.get(i), T.contient.get(i).getLiee().get(j).getFin()));
            }
                Total.coeffs[i+1][999]= T.contient.get(i).forceY;
            }
            
            
            
            if (T.contient.get(i) instanceof AppuiDouble) {
                for (int j=0;j<T.contient.get(i).getLiee().size();j++){
                    Total.coeffs[i][T.contient.get(i).getLiee().get(j).getId()]= Math.cos(pAngle(T.contient.get(i), T.contient.get(i).getLiee().get(j).getFin()));
                }

            }
            
            
            
            if (T.contient.get(i) instanceof AppuiSimple) {

            }
        }
        return Total;
    }
    

    public static void main(String[] args) {
        Point pos1 = new Point(1, 1);
        Point pos2 = new Point(2, 2);
        Point pos3 = new Point(2, 2);
        Point pos4 = new Point(4, 4);
        Point pos5 = new Point(1, 4);
        NoeudSimple nS1 = new NoeudSimple(pos1);
        NoeudSimple nS2 = new NoeudSimple(pos3);
        NoeudSimple nS4 = new NoeudSimple(pos4);
        NoeudSimple nS5 = new NoeudSimple(pos5);
        nS1.id = 8;

        //Barre b3 = new Barre(nS4, nS1, 5, 25, 56, 800, 1000);
        SegmentTerrain seg4 = new SegmentTerrain(pos2, pos4);
        AppuiSimple nAS3 = new AppuiSimple(pos3, seg4);
        nS1.forceY = 9;
        Barre b1 = new Barre(nS1, nS2);
        Barre b2 = new Barre(nS1, nS4);
        //Matrice M = SommeNoeudS(nS1);
        b1.id = 6;
        b2.id = 87;

        Matrice K = SommeNoeudS(nS1);

        System.out.println(K);

        // System.out.println(M + "T" );
    }

}
