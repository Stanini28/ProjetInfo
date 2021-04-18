/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insa.allouche.infoprojet.interfacee;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;  
/**
 *
 * @author Portable
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Interface Dessin");
      Scene scene = new Scene(new interfaceDessin());
//        Groupe model = Groupe.groupeTest();
//        scene = new Scene(new interfaceDessin());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}    

