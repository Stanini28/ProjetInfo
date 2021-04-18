/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 *
 * @author Portable
 */
public class interfaceDessin extends BorderPane {
    
    private ToggleButton Select;
    private Button zoneconstructible;
    private Button groupe;
    private Button noeudsimple;
    private Button terrain;
    private Button supprimer;
    private RadioButton remove;
    private ComboBox noeudappui;
    private ComboBox cataloguebarre;
    private ComboBox couleur; 
    private HBox entete; 
    
    
    public interfaceDessin(){
        ToggleGroup tgState = new ToggleGroup() ;
        this.Select = new ToggleButton("Select") ;
        this.Select.setToggleGroup(tgState);
        this.zoneconstructible = new Button("Zone constructible");
        this.terrain = new Button("Terrain");
        this.supprimer = new Button("Supprimer");
        this.groupe = new Button("Groupe");
        this.noeudsimple = new Button("Noeud Simple");
        this.remove = new RadioButton("Remove");
        this.cataloguebarre = new ComboBox();
        this.couleur = new ComboBox();
        this.noeudappui = new ComboBox();
        this.entete = new HBox(this.Select, this.noeudsimple, this.noeudappui, this.cataloguebarre,this.remove, this.groupe,this.terrain,this.supprimer,this.zoneconstructible,this.couleur);
        this.setTop(this.entete);
        


        
        
        
        
    }
    }

   
    
