/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Treillis;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
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
    private ToggleButton barre;
    private ToggleButton zoneconstructible;
    private ToggleButton supprimer;
    private ToggleButton triangle_Terrain;

    private SplitMenuButton catalogueBarre;
    private MenuItem choiceB1;
    private MenuItem choiceB2;
    private MenuItem choiceB3;
    private SplitMenuButton choiceBoxN;
    private MenuItem choiceN1;
    private MenuItem choiceN2;
    private MenuItem choiceN3;

    private Button removeAll;

    private ColorPicker cpCouleur;

    private HBox entete;

    private DessinCanvas zoneDessin;

    public interfaceDessin(Treillis model) {

        this.inStage = inStage;
        // this.curFile = fromFile;
        this.model = model;
        this.controleur = new Controleur(this);

        this.Select = new ToggleButton("Select");

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

        this.supprimer = new ToggleButton("Supprimer");
        this.supprimer.setOnAction((t) -> {
            this.controleur.bouttonSuprimer(t);
        });

        this.calcul = new Button("Calcul");

        this.removeAll = new Button("Remove All");

        this.choiceBoxN = new SplitMenuButton();
//        String var = "C:\\Users\\Portable\\OneDrive\\Desktop\\point.png";
//        InputStream input = new FileInputStream(var);
//        FileInputStream input1 = new FileInputStream(var);
//        Image image = new Image(input);
//        ImageView imageView = new ImageView(image);
//
//        this.choiceBoxN.setGraphic(imageView);
        this.choiceBoxN.setText("Noeud");
        this.choiceN1 = new MenuItem("Noeud Simple");
        this.choiceN2 = new MenuItem("Appui Simple");
        this.choiceN3 = new MenuItem("Appui Double");
        this.choiceBoxN.getItems().addAll(this.choiceN1, this.choiceN2, this.choiceN3);
        this.choiceN1.setOnAction((t) -> {
            this.choiceBoxN.setText("Noeud Simple");
            this.controleur.splitMenuButtonNS(t);
        });
        this.choiceN2.setOnAction((t) -> {
            this.choiceBoxN.setText("Appui Simple");
            this.controleur.splitMenuButtonAS(t);
        });
        this.choiceN3.setOnAction((t) -> {
            this.choiceBoxN.setText("Appui Double");
            this.controleur.splitMenuButtonAD(t);
        });

        this.catalogueBarre = new SplitMenuButton();
        this.catalogueBarre.setText("Barre");;;
        this.choiceB1 = new MenuItem("Barre type 1");
        this.choiceB2 = new MenuItem("Barre type 2");
        this.choiceB3 = new MenuItem("Barre type 3");
        this.catalogueBarre.getItems().addAll(this.choiceB1, this.choiceB2, this.choiceB3);
        this.choiceB1.setOnAction((t) -> {
            this.catalogueBarre.setText("Barre type 1");
            this.controleur.buttonBarre1();
        });
        this.choiceB2.setOnAction((t) -> {
            this.catalogueBarre.setText("Barre type 2");
            this.controleur.buttonBarre2(t);
        });
        this.choiceB3.setOnAction((t) -> {
            this.catalogueBarre.setText("Barre type 3");
            this.controleur.buttonBarre3(t);
        });

        this.cpCouleur = new ColorPicker(Color.BLACK);

        this.entete = new HBox(this.triangle_Terrain, this.Select, this.choiceBoxN, this.catalogueBarre,
                this.supprimer, this.zoneconstructible, this.cpCouleur, this.removeAll);

        this.entete.setStyle(" -fx-padding: 2;");
        this.Select.setStyle("-fx-background-color: #00f8c3; -fx-padding: 10;");
        this.removeAll.setStyle("-fx-background-color: #67d5c1; -fx-padding: 10;");
        this.zoneconstructible.setStyle("-fx-background-color: #ffb12e; -fx-padding: 10;");
        this.cpCouleur.setStyle("-fx-background-color: #c5dfff; -fx-padding: 10;");
        this.choiceBoxN.setStyle("-fx-background-color: #fbad84; -fx-padding: 10;");
        this.triangle_Terrain.setStyle("-fx-background-color: #6bd314; -fx-padding: 10;");
        this.supprimer.setStyle("-fx-background-color: #fe2705; -fx-padding: 10;");
        this.choiceN1.setStyle("-fx-background-color: #e7a9a7; -fx-padding: 8;");
        this.choiceN2.setStyle("-fx-background-color: #f1cccb; -fx-padding: 8;");
        this.choiceN3.setStyle("-fx-background-color: #f6e0df; -fx-padding: 8;");
        this.choiceB1.setStyle("-fx-background-color: #b5b6eb; -fx-padding: 8;");
        this.choiceB2.setStyle("-fx-background-color: #cfcff2; -fx-padding: 8;");
        this.choiceB3.setStyle("-fx-background-color: #e7e7f8; -fx-padding: 8;");

        this.setTop(this.entete);

        this.zoneDessin = new DessinCanvas(this);
        this.setCenter(this.zoneDessin);

        this.controleur.changeEtat(20);
        
    }

    public void redrawAll() {
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

    public ToggleButton getSupprimer() {
        return supprimer;
    }

    public Button getRemove() {
        return removeAll;
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

    public SplitMenuButton getChoiceBoxN() {
        return choiceBoxN;
    }

    public MenuItem getChoice1() {
        return choiceN1;
    }

    public MenuItem getChoice2() {
        return choiceN2;
    }

    public MenuItem getChoice3() {
        return choiceN3;
    }

    public ToggleButton getBarre() {
        return barre;
    }

    public SplitMenuButton getCatalogueBarre() {
        return catalogueBarre;
    }

    public MenuItem getChoiceB1() {
        return choiceB1;
    }

    public MenuItem getChoiceB2() {
        return choiceB2;
    }

    public MenuItem getChoiceB3() {
        return choiceB3;
    }

    public MenuItem getChoiceN1() {
        return choiceN1;
    }

    public MenuItem getChoiceN2() {
        return choiceN2;
    }

    public MenuItem getChoiceN3() {
        return choiceN3;
    }
}
