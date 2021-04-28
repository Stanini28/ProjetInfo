/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Treillis;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Portable
 */
public class interfaceDessin extends BorderPane {
    
    private Controleur controleur;
    private Treillis model;
    
    private Stage inStage;
    private File curFile;
    
    private Button calcul;
    
    private ToggleButton Select;
    private ToggleButton zoneconstructible;
    private ToggleButton groupe;
    private ToggleButton terrain;
    private ToggleButton supprimer;
    private ToggleButton triangle_Terrain;
    
    private RadioButton remove;
    
    private ComboBox noeud;
    private ComboBox cataloguebarre;
    private ColorPicker cpCouleur;

    private HBox entete; 
  
    
    private DessinCanvas zoneDessin;
    
    public interfaceDessin(Treillis model){
        
        this.inStage = inStage;
       // this.curFile = fromFile;
        this.model = model;
        this.controleur = new Controleur(this);

        this.Select = new ToggleButton("Select") ;
        this.Select.setOnAction((t) -> {
            this.controleur.boutonSelect(t);
        });
        
        this.zoneconstructible = new ToggleButton("Zone constructible");
        this.zoneconstructible.setOnAction((t) -> {
            this.controleur.boutonZoneConstructible(t);
        });
        this.triangle_Terrain = new ToggleButton("Triangle Terrain");
        this.triangle_Terrain.setOnAction((t) -> {
            this.controleur.boutontriangle_Terrain(t);
        });
        this.terrain = new ToggleButton("Terrain");
        this.supprimer = new ToggleButton("Supprimer");
        this.groupe = new ToggleButton("Groupe");
        
        this.calcul = new Button("Calcul");
        
        this.remove = new RadioButton("Remove");
        
        this.cataloguebarre = new ComboBox();
        this.cataloguebarre.getItems().addAll( "Catalogue de Barre", "new type de barre");
        this.cataloguebarre.getSelectionModel().selectFirst();
        
        this.noeud = new ComboBox();
        this.noeud.getItems().addAll("Noeud","appui double","appui simple", "noeud simple");
        this.noeud.getSelectionModel().selectFirst();
        this.noeud.setOnAction((t) -> {
            
        });
        
        this.cpCouleur = new ColorPicker(Color.BLACK);
        
        this.entete = new HBox(this.triangle_Terrain, this.noeud, this.cataloguebarre,
                this.groupe,this.terrain,this.supprimer,this.zoneconstructible,this.cpCouleur ,this.remove);
        
        this.setTop(this.entete);
        
        this.zoneDessin = new DessinCanvas(this);
        this.setCenter(this.zoneDessin);
    
        this.controleur.changeEtat(20);

  }
    public void redrawAll(){
        this.zoneDessin.redrawAll();
    }

    public Controleur getControleur() {
        return controleur;
    }

    public Stage getInStage() {
        return inStage;
    }

    public File getCurFile() {
        return curFile;
    }

    public Treillis getModel() {
        return model;
    }

    public Button getCalcul() {
        return calcul;
    }

    public ToggleButton getSelect() {
        return Select;
    }

    public ToggleButton getZoneconstructible() {
        return zoneconstructible;
    }

    public ToggleButton getGroupe() {
        return groupe;
    }

    public ToggleButton getTerrain() {
        return terrain;
    }

    public ToggleButton getSupprimer() {
        return supprimer;
    }

    public RadioButton getRemove() {
        return remove;
    }

    public ComboBox getNoeud() {
        return noeud;
    }

    public ComboBox getCataloguebarre() {
        return cataloguebarre;
    }

    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }

    public HBox getEntete() {
        return entete;
    }

    public DessinCanvas getZoneDessin() {
        return zoneDessin;
    }

    public ToggleButton getTriangle_Terrain() {
        return triangle_Terrain;
    }
    
}
    
    
    

   
    
