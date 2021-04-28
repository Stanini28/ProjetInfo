/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet;

import java.util.Set;

/**
 *
 * @author stanislasallouche
 */
public class Barre {

    private int id;
    private Noeud debut;
    private Noeud fin;
    private Treillis compose;
    private TypeBarre type;
    public double Fx;
    public double Fy;
    
    
    
    public Barre(Noeud debut, Noeud fin) {
        this.debut = debut;
        this.fin = fin;
//        this.id= Identificateuer.getOrCreateId(this);
        this.debut.addBarre(this);
        this.fin.addBarre(this);
    }
    
    public Barre(Noeud debut, Noeud fin, double coutAuMetre, double lMin,
            double lMax, double rTraction, double rComp) {
        this.debut = debut;
        this.fin = fin;
//        this.id= Identificateuer.getOrCreateId(this);
        this.debut.addBarre(this);
        this.fin.addBarre(this);
        this.type = new TypeBarre(coutAuMetre, lMin, lMax, rTraction, rComp);
        this.type.addTBarre(this);
    }

    public Barre() {
        this.debut = new Noeud();
        this.fin = new Noeud();
    }

    void setDebut(Noeud debut) {
        this.debut = debut;
    }

    void setFin(Noeud fin) {
        this.fin = fin;
    }

    public Noeud getDebut() {
        return this.debut;
    }

    public Noeud getFin() {
        return this.fin;
    }

    void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public TypeBarre getType() {
        return type;
    }

    void setType(TypeBarre type) {
        this.type = type;
    }
    
    void setCompose(Treillis compose) {
        this.compose = compose;
    }

    public Treillis getCompose() {
        return compose;
    }

    public String toString() {
        String res = "Barre "+this.id+"{ ";
        res = res + "[" + this.getDebut().getPosition().getPX()
                + " , " + this.getDebut().getPosition().getPY() + "] ,";
        res = res + "[" + this.getFin().getPosition().getPX()
                + " , " + this.getFin().getPosition().getPY() + "] }\n";
        res = res + this.type.toString();
        return res;
    }

    public static Barre demandeBarre(TypeBarre tB) {
        System.out.println("noeud de début : ");
        Noeud noeudD = Noeud.demandeNoeud();

        System.out.println("noeud de fin : ");
        Noeud noeudF = Noeud.demandeNoeud();
        
        Barre b = new Barre(noeudD, noeudF, tB.getCoutAuMetre(), tB.getlMin(), tB.getlMax(), tB.getrTraction(), tB.getrComp());
        return b;
    }
    public static Barre demandeBarreNewTB() {
        System.out.println("noeud de début : ");
        Noeud noeudD = Noeud.demandeNoeud();

        System.out.println("noeud de fin : ");
        Noeud noeudF = Noeud.demandeNoeud();
        
        Barre b = new Barre(noeudD, noeudF);
        return b;
    }
}
