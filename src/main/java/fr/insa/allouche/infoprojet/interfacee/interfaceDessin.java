/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Treillis;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.Insets;
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
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
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
    private MenuItem choiceB4;
    private SplitMenuButton choiceBoxN;
    private MenuItem choiceN1;
    private MenuItem choiceN2;
    private MenuItem choiceN3;
    private ToggleButton Prix;

    private Button removeAll;

    private ColorPicker cpCouleur;

    private HBox entete;
    private VBox AG;
    private VBox englobe;
   
    private DessinCanvas zoneDessin;
    private MainMenu menu;
    

    public interfaceDessin(Treillis model) {
        this(model,20);
    }
    
    public interfaceDessin(Treillis model, int etat ) {

        this.inStage = inStage;
        // this.curFile = fromFile;
        this.model = model;
        this.controleur = new Controleur(this);

        this.Select = new ToggleButton("Select",imageselect);

        this.Select.setOnAction((t) -> {
            this.controleur.boutonSelect(t);
        });

        this.zoneconstructible = new ToggleButton("Zone constructible",imagezc);
        this.zoneconstructible.setOnAction((t) -> {
            this.controleur.boutonZoneConstructible(t);
        });
        this.triangle_Terrain = new ToggleButton("Triangle Terrain",imagetriangle);
        this.triangle_Terrain.setOnAction((t) -> {
            this.controleur.boutontriangle_Terrain(t);
        });

        this.supprimer = new ToggleButton("Supprimer",imagesupprimer);
        this.supprimer.setOnAction((t) -> {
            this.controleur.bouttonSuprimer(t);
        });

        this.calcul = new Button("Calcul",imagecal);
        this.calcul.setOnAction((t) -> {
            this.controleur.bouttonCalcul(t);
        });
        this.Prix = new ToggleButton("Prix", imageprix);
        this.Prix.setOnAction((t) -> {
            this.controleur.bouttonPrix(t);
        });

        this.removeAll = new Button("Remove All");
        this.removeAll.setOnAction((t) -> {
            this.controleur.bouttonRemoveAll(t);
        });

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
        this.choiceB4 = new MenuItem("info_type_barre");
        this.catalogueBarre.getItems().addAll(this.choiceB1, this.choiceB2, this.choiceB3, this.choiceB4);
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
        this.choiceB4.setOnAction((t) -> {
            this.catalogueBarre.setText("info_type_barre");
            this.controleur.buttonBarre4(t);
        });

        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.controleur.bouttonCouleur(cpCouleur.getValue());
        });
        this.menu = new MainMenu(this);

        this.entete = new HBox(this.triangle_Terrain, this.Select, this.choiceBoxN, this.catalogueBarre,
                this.supprimer, this.zoneconstructible, this.cpCouleur, this.removeAll);

        this.entete.setStyle(" -fx-padding: 2;");
        this.entete = new HBox(this.zoneconstructible, this.triangle_Terrain, this.choiceBoxN, this.catalogueBarre);
        
        this.englobe=new VBox(this.menu,this.entete);
        this.setTop(this.englobe);
        
        this.AG= new VBox(this.Select,this.removeAll, this.cpCouleur,this.calcul, this.Prix,  this.supprimer);
        this.setLeft(this.AG);

        this.AG.setStyle(" -fx-padding: 1;");
        this.entete.setStyle(" -fx-padding: 1;");
        this.Select.setStyle("-fx-background-color: #0080FF; -fx-padding: 10;");
        this.removeAll.setStyle("-fx-background-color: #2E9AFE; -fx-padding: 10;");
        this.zoneconstructible.setStyle("-fx-background-color: #084B8A; -fx-padding: 10;");
        this.cpCouleur.setStyle("-fx-background-color: #58ACFA ; -fx-padding: 10;");
        this.choiceBoxN.setStyle("-fx-background-color: #0174DF; -fx-padding: 10;");
        this.triangle_Terrain.setStyle("-fx-background-color: #045FB4; -fx-padding: 10;");
        this.supprimer.setStyle("-fx-background-color: #DF0101; -fx-padding: 10;");
        this.choiceN1.setStyle("-fx-background-color: #e7a9a7; -fx-padding: 8;");
        this.choiceN2.setStyle("-fx-background-color: #f1cccb; -fx-padding: 8;");
        this.choiceN3.setStyle("-fx-background-color: #f6e0df; -fx-padding: 8;");
        this.choiceB1.setStyle("-fx-background-color: #b5b6eb; -fx-padding: 8;");
        this.choiceB2.setStyle("-fx-background-color: #cfcff2; -fx-padding: 8;");
        this.choiceB3.setStyle("-fx-background-color: #e7e7f8; -fx-padding: 8;");
        this.choiceB4.setStyle("-fx-background-color: #e7e7f8; -fx-padding: 8;");
        this.catalogueBarre.setStyle("-fx-background-color: #0080FF; -fx-padding: 8;");
        this.calcul.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
        this.Prix.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");

        this.Select.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.catalogueBarre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.cpCouleur.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.supprimer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.triangle_Terrain.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.removeAll.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.zoneconstructible.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.choiceBoxN.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.calcul.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.Prix.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.controleur.changeEtat(etat);
        
        
        this.imageselect.setFitHeight(50);
        this.imageselect.setFitWidth(50);
        this.imagesupprimer.setFitHeight(50);
        this.imagesupprimer.setFitWidth(50);
        this.imagetriangle.setFitHeight(50);
        this.imagetriangle.setFitWidth(50);
        this.imagezc.setFitHeight(50);
        this.imagezc.setFitWidth(50);
        this.imagecal.setFitHeight(50);
        this.imagecal.setFitWidth(50);
        this.imageprix.setFitHeight(50);
        this.imageprix.setFitWidth(50);
        
        
        this.AG.setAlignment(Pos.TOP_LEFT);
        this.AG.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.entete.setAlignment(Pos.CENTER_RIGHT);
        this.entete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
       
        this.Select.setBackground(Background.EMPTY);
        
        Border border1=new Border(new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.entete.setBorder(border1);
        Border border2=new Border(new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.AG.setBorder(border2);
        Border border3=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.Select.setBorder(border3);
        Border border4=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.catalogueBarre.setBorder(border4);
        Border border5=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.choiceBoxN.setBorder(border5);
        Border border6=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.removeAll.setBorder(border6);
        Border border7=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.triangle_Terrain.setBorder(border7);
        Border border8=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.zoneconstructible.setBorder(border8);
        Border border9=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.cpCouleur.setBorder(border9);
        Border border10=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.supprimer.setBorder(border10);
        Border border11=new Border(new BorderStroke(Color.STEELBLUE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.menu.setBorder(border11);
        Border border12=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.calcul.setBorder(border12);
        Border border13=new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(5)));
        this.Prix.setBorder(border13);
        
        this.entete.setStyle("-fx-background-color: #CEE3F6;");
        this.AG.setStyle("-fx-background-color: #CEE3F6;");
        

        
        
            this.Select.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    
                    interfaceDessin.this.Select.setStyle("-fx-background-color: #5DADE2;");
                    
                    }
            });
            
            this.Select.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.Select.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
           
            
        
            
            this.catalogueBarre.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.catalogueBarre.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            
            this.catalogueBarre.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.catalogueBarre.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.supprimer.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.supprimer.setStyle("-fx-background-color: #F54848;");
                }
            });
            this.supprimer.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.supprimer.setStyle("-fx-background-color: #ED1515; -fx-padding: 8;");
                }
            });
            
            this.zoneconstructible.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.zoneconstructible.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            this.zoneconstructible.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.zoneconstructible.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.triangle_Terrain.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.triangle_Terrain.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            this.triangle_Terrain.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.triangle_Terrain.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.choiceBoxN.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.choiceBoxN.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            this.choiceBoxN.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.choiceBoxN.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.cpCouleur.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.cpCouleur.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            this.cpCouleur.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.cpCouleur.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.calcul.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    interfaceDessin.this.calcul.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            
            this.calcul.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.calcul.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.Prix.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.Prix.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            
            this.Prix.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.Prix.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            this.removeAll.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.removeAll.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            
            this.removeAll.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.removeAll.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            this.calcul.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.calcul.setStyle("-fx-background-color: #5DADE2;");
                }
            });
            
            this.calcul.setOnMouseExited(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    interfaceDessin.this.calcul.setStyle("-fx-background-color: #295C92; -fx-padding: 8;");
                }
            });
            
            this.zoneDessin = new DessinCanvas(this);
            this.setCenter(this.zoneDessin);
            
            
            
//        } else {
        }

    public void redrawAll() {
        this.zoneDessin.redrawAll();
    }
    public void redrawAllSelect() {
        this.zoneDessin.redrawAllSelect();
    }
    public void redrawAllPbBarre() {
        this.zoneDessin.redrawAllPbBarre();
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

    public void setModel(Treillis model) {
        this.model = model;
    }
    
    public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    public ToggleButton getPrix() {
        return Prix;
    }
    
    
    
    
    private ImageView imageselect = new ImageView(new Image("https://image.flaticon.com/icons/png/512/99/99162.png"));
    private ImageView imagetriangle = new ImageView(new Image("https://image.shutterstock.com/image-vector/triangle-arrow-pyramid-line-art-260nw-739498723.jpg"));
    private ImageView imagesupprimer = new ImageView(new Image("https://img.icons8.com/plasticine/2x/filled-trash.png"));
    private ImageView imagezc = new ImageView(new Image("https://www.eurographics.ca/uploads/postercartel_product_option.imageEnlarge/1400-2004.jpg"));
    private ImageView imagecal = new ImageView(new Image("https://static4.depositphotos.com/1000423/472/i/950/depositphotos_4723550-stock-photo-3d-man-with-calculator.jpg"));
    private ImageView imageprix = new ImageView( new Image ("https://img2.freepng.fr/20180705/ubs/kisspng-euro-sign-finance-dollar-sign-currency-euro-5b3e7bc6e323e5.8283559115308215749304.jpg"));

}

