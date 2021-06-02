/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.Treillis;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 *
 * @author francois
 */
public class DessinCanvas extends Pane {

    static GraphicsContext getGraphicsContext2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private interfaceDessin interfaceD;

    private Canvas realCanvas;

    public DessinCanvas(interfaceDessin interfaceD) {
        this.interfaceD = interfaceD;
        this.realCanvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.realCanvas);
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.realCanvas.setOnMouseClicked((t) -> {
            Controleur control = this.interfaceD.getControleur();
            control.clicDansZoneDessin(t);
        });
        this.redrawAll();
    }

    public void redrawAll() {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Treillis model = this.interfaceD.getModel();
        context.clearRect(0, 0, realCanvas.getWidth(), realCanvas.getHeight());
        model.dessine(context);
//        List<Figure> select = this.interfaceD.getControleur().getSelection();
//        if (! select.isEmpty()) {
//            for (Figure f : select) {
//                f.dessineSelection(context);
//            }
//        }
    }
    public void redrawAllSelect() {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Treillis model = this.interfaceD.getModel();
        context.clearRect(0, 0, realCanvas.getWidth(), realCanvas.getHeight());
        model.dessine(context);
        model.dessineSelect(context);
    }
    public void redrawAllPbBarre() {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Treillis model = this.interfaceD.getModel();
        context.clearRect(0, 0, realCanvas.getWidth(), realCanvas.getHeight());
        model.dessinePbBarre(context);
//        List<Figure> select = this.interfaceD.getControleur().getSelection();
//        if (! select.isEmpty()) {
//            for (Figure f : select) {
//                f.dessineSelection(context);
//            }
//        }
    }
}
