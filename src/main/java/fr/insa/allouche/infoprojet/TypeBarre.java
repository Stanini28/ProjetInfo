/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.ArrayList;
import java.util.List;
import fr.insa.allouche.infoprojet.outils.Lire;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;

public class TypeBarre {

    private int id;
    private double coutAuMetre;
    private double lMin;
    private double lMax;
    private double rTraction;
    private double rComp;
    private List<Barre> type;
    private Treillis catalogueDeBarre;
    private Color couleur;

    public TypeBarre(double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp) {

        this.coutAuMetre = coutAuMetre;
        this.lMax = lMax;
        this.lMin = lMin;
        this.rComp = rComp;
        this.rTraction = rTraction;
        this.type = new ArrayList<Barre>();
    }
    public TypeBarre(double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp, Color couleur) {

        this.coutAuMetre = coutAuMetre;
        this.lMax = lMax;
        this.lMin = lMin;
        this.rComp = rComp;
        this.rTraction = rTraction;
        this.type = new ArrayList<Barre>();
        this.couleur = couleur;
    }

    public TypeBarre() {
        this(12, 20, 40, 500, 600);
    }

    public void addTBarre(Barre b) {
        if (b.getType() != this) {
            if (b.getType() != null) {
                throw new Error("La barre appartient déjà à un autre Type de barre");
            }
            this.type.add(b);
            b.setType(this);
        }
    }

    public void removeTBarre(Barre b) {
        if (b.getType() != this) {
            throw new Error("La barre n'appartient pas à ce Type de barre");
        }
        this.type.remove(b);
        b.setType(null);
    }

    public void removeAllTBarre(List<Barre> lB) {
        for (int i = 0; i < this.type.size(); i++) {
            this.type.get(i).setType(null);
        }
        this.type.clear();
    }

    public static TypeBarre DemandeTypeBarre() {
        System.out.println("Initialisation du type de barre que vous-voulez creer");

        System.out.println("Quel est sont coût au mètre  ?");
        double coutM = Lire.d();
        System.out.println("Quel ets la longeur max ?");
        double lMax = Lire.d();
        System.out.println("Quel est la longeur min ,");
        double lMin = Lire.d();
        System.out.println("Quel est la force de traction max ?");
        double rTraction = Lire.d();
        System.out.println("Quel est la force de Compressin max ?");
        double rComp = Lire.d();
        return new TypeBarre(coutM, lMin, lMax, rTraction, rComp);
    }

    public int getId() {
        return id;
    }

    public double getCoutAuMetre() {
        return coutAuMetre;
    }

    public double getlMin() {
        return lMin;
    }

    public double getlMax() {
        return lMax;
    }

    public double getrTraction() {
        return rTraction;
    }

    public double getrComp() {
        return rComp;
    }

    public List<Barre> getType() {
        return type;
    }

    public Treillis getCatalogueDeBarre() {
        return catalogueDeBarre;
    }

    void setId(int id) {
        this.id = id;
    }

    void setType(List<Barre> type) {
        this.type = type;
    }

    void setCatalogueDeBarre(Treillis catalogueDeBarre) {
        this.catalogueDeBarre = catalogueDeBarre;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    

    public String toString() {
        String res = "";
        res = "Type de barre " + this.id + ":\n"
                + "     Coût au mètre : " + this.coutAuMetre + "\n"
                + "     Longueur max : " + this.lMax + "\n"
                + "     Longueur min : " + this.lMin + "\n"
                + "     force de traction ax : " + this.rTraction + "\n"
                + "     force de compression ax : " + this.rComp + "\n";
        return res;
    }
    
    public void save(Writer w, Identificateur num) throws IOException{
        //if(num.objExist(this)){
        w.append("TypeBarre;" +this.id+ ";" + this.coutAuMetre+ ";" +  this.lMin+ ";"
                + this.lMax + ";"+ this.rTraction+ ";"+ this.rComp + "\n");
       // }

    }
}
//TEST TES TES 