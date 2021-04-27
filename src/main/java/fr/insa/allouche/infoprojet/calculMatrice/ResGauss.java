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
public class ResGauss {

    public int rang;
    public int signature;

    public ResGauss(int rang, int signature) {
        this.rang = rang;
        this.signature = signature;
    }

    public String toString() {
        String s = "";
        s = "{ResGauss : rang = " + rang + " ; sigPerm = " + signature + "}";
        return s;
    }

}
