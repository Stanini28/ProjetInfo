/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Un Canvas dans un StackPane pour que la taille du Canvas suive la taille du
 * conteneur. Voir
 * https://stackoverflow.com/questions/24533556/how-to-make-canvas-resizable-in-javafx
 * https://stackoverflow.com/questions/49826741/nested-borderpane-with-canvas-only-grows-never-shrinks
 *
 * @author francois
 */
public class Canvas extends StackPane {

  
    private javafx.scene.canvas.Canvas realCanvas;
    private interfaceDessin main;

    public Canvas(interfaceDessin main, double initWidth, double initHeight) {
       
        this.main = main;
        this.setPrefSize(initWidth, initHeight);
        this.realCanvas = new javafx.scene.canvas.Canvas();
        this.realCanvas.setManaged(false);
        this.realCanvas.widthProperty().bind(
                this.widthProperty());
        this.realCanvas.heightProperty().bind(
                this.heightProperty());
        this.getChildren().add(this.realCanvas);
        this.realCanvas.widthProperty().addListener((o) -> {
            redraw();
        });
        this.realCanvas.heightProperty().addListener((o) -> {
            redraw();
        });
        this.realCanvas.setOnMouseClicked((t) -> {
//            control.clicDessin(t);
        });
        
        this.realCanvas.setOnMouseClicked((t) -> {
       
        });
        redraw();
    }

    public void redraw() {
        double width = this.realCanvas.getWidth();
        double height = this.realCanvas.getHeight();
//        System.out.println("redraw2 [" + width + "," + height + "]");

        GraphicsContext gc = this.realCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

//        gc.setStroke(Color.RED);
//        gc.strokeLine(0, 0, width, height);
//        gc.strokeLine(0, height, width, 0);
   
//        this.control.drawSelection(gc);
    }

}

    
    
    
  
