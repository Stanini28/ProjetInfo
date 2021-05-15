/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author stanislasallouche
 */
public class MainMenu extends MenuBar{

    private interfaceDessin main;

    public MainMenu(interfaceDessin main) {
        this.main = main;
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuNouveau(t);
        });
        MenuItem save = new MenuItem("Sauvegarder");
        save.setOnAction((t) -> {
            this.main.getControleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Sauvegarder sous...");
        saveAs.setOnAction((t) -> {
            this.main.getControleur().menuSaveAs(t);
        });
        MenuItem load = new MenuItem("Ouvrir");
        load.setOnAction((t) -> {
            this.main.getControleur().menuOpen(t);
        });        
        file.getItems().addAll(nouveau,save,saveAs,load);
        Menu help = new Menu("Aide");
        MenuItem apropos = new MenuItem("A propos");
        apropos.setOnAction((t) -> {
            this.main.getControleur().menuApropos(t);
        });        
        help.getItems().addAll(apropos);
        
        this.getMenus().addAll(file,help);
    }
    
}
