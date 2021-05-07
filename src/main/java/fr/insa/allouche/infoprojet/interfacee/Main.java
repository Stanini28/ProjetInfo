/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Treillis;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;  
/**
 *
 * @author Portable
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        //Scene sc = new Scene(new interfaceDessin(Treillis.treillisTest()),800,600);
        Scene sc = new Scene (new interfaceDessin(new Treillis()),800,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
          stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}    

