/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

/**
 *
 * @author Portable
 */
import javax.swing.DefaultRowSorter;

public class ResSup {

    private Matrice sol;
    private boolean solUnique;

    public ResSup(Matrice sol) {
        this.sol = sol;
        this.solUnique = true;
    }

    public ResSup() {

        this.solUnique = false;
    }

    public boolean isSolUnique() {
        return this.solUnique;
    }

    public Matrice getSet() {
//       if (this.solUnique == false) {
//          throw new Error("pas de solution");
//     }
        return this.sol;
    }
    public boolean verifSolUnique() {
        boolean res =true;
        if (this.solUnique == false) {
            res = false;
        }
        return res;
    }

    public static ResSup pasDeSol() {
        return new ResSup();
    }

    public static ResSup solUnique(Matrice sol) {
        return new ResSup(sol);
    }

    public String toString() {
        String s = "";
        s = "{la solution est = \n" + sol + "unicité = " + solUnique + "}";
        return s;
    }

    public String toString1() {
        String s = "";
        s = "Il y a une infinité de solution. Unicité =" + solUnique;
        return s;
    }
}
