/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Point;
import fr.insa.allouche.infoprojet.Terrain;
import fr.insa.allouche.infoprojet.Treillis;
import fr.insa.allouche.infoprojet.TriangleTerrain;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Portable
 */
public class Controleur {

    private Point[] pointzc = new Point[4];
    private Point[] pointTT = new Point[3];
    private interfaceDessin vue;

    private int etat;

    public Controleur(interfaceDessin vue) {
        this.vue = vue;
    }

    public void changeEtat(int etat) {
        if (etat == 20 || etat == 21 || etat == 22 || etat == 23) {
            this.vue.getRemove().setDisable(true);
            this.vue.getCalcul().setDisable(true);
            this.vue.getSelect().setDisable(true);
            this.vue.getGroupe().setDisable(true);
            this.vue.getTerrain().setDisable(true);
            this.vue.getSupprimer().setDisable(true);
            this.vue.getNoeud().setDisable(true);
            this.vue.getCataloguebarre().setDisable(true);
            this.vue.getCpCouleur().setDisable(true);
            this.vue.getTriangle_Terrain().setDisable(true);
        }
        if (etat == 30 || etat == 31 || etat == 32 || etat == 33){
            this.vue.getTriangle_Terrain().setDisable(false);
            this.vue.getZoneconstructible().setDisable(true);
        }
        this.etat = etat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if (this.etat == 20) {
            double px = t.getX();
            double py = t.getY();
            this.pointzc [0]= new Point (px, py);
            this.changeEtat(21);
        } else if (this.etat == 21){
            double px = t.getX();
            double py = t.getY();
            this.pointzc [1]= new Point (px, py);
            this.changeEtat(22);
        } else if (this.etat == 22){
            double px = t.getX();
            double py = t.getY();
            this.pointzc [2]= new Point (px, py);
            this.changeEtat(23);
        } else if (this.etat == 23){
            double px = t.getX();
            double py = t.getY();
            this.pointzc [3]= new Point (px, py);
            Treillis model = this.vue.getModel();
            model.addTerrain(new Terrain(pointzc[0], pointzc[1], 
                    pointzc[2], pointzc[3]));
            this.vue.redrawAll();
            this.changeEtat(30);
        } else if (this.etat == 30){
            double px = t.getX();
            double py = t.getY();
            this.pointTT [0]= new Point (px, py);
            this.changeEtat(31);
        } else if (this.etat == 31){
            double px = t.getX();
            double py = t.getY();
            this.pointTT [1]= new Point (px, py);
            this.changeEtat(32);
        } else if (this.etat == 32){
            double px = t.getX();
            double py = t.getY();
            this.pointTT [2]= new Point (px, py);
            Treillis model = this.vue.getModel();
            model.getBase().addTriangleTerrain(new TriangleTerrain(pointTT[0], pointTT[1],pointTT[2]));
            this.vue.redrawAll();
            this.changeEtat(30);
        }
        
    }

    void boutonSelect(ActionEvent t) {
        
    }

    void boutonZoneConstructible(ActionEvent t) {
        this.changeEtat(20);
    }

    void boutontriangle_Terrain(ActionEvent t) {
        this.changeEtat(30);
    }
}
