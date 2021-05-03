/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.interfacee;

import fr.insa.allouche.infoprojet.AppuiDouble;
import fr.insa.allouche.infoprojet.AppuiSimple;
import fr.insa.allouche.infoprojet.Barre;
import fr.insa.allouche.infoprojet.Noeud;
import fr.insa.allouche.infoprojet.NoeudSimple;
import fr.insa.allouche.infoprojet.Point;
import fr.insa.allouche.infoprojet.SegmentTerrain;
import fr.insa.allouche.infoprojet.Terrain;
import fr.insa.allouche.infoprojet.Treillis;
import fr.insa.allouche.infoprojet.TriangleTerrain;
import fr.insa.allouche.infoprojet.TypeBarre;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Portable
 */
public class Controleur {

    private Point[] pointzc = new Point[4];
    private Point[] pointTT = new Point[3];
    private Point[] pointB = new Point[2];
    private Noeud[] noeudB = new Noeud[2];
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
            this.vue.getSupprimer().setDisable(true);
            this.vue.getCpCouleur().setDisable(true);
            this.vue.getTriangle_Terrain().setDisable(true);
            this.vue.getChoiceBoxN().setDisable(true);
            this.vue.getCatalogueBarre().setDisable(true);
        }
        if (etat == 29 || etat == 30 || etat == 31 || etat == 32 || etat == 33) {
            this.vue.getTriangle_Terrain().setDisable(false);
            this.vue.getZoneconstructible().setDisable(false);
        }
        if (etat >= 39) {
            this.vue.getRemove().setDisable(false);
            this.vue.getCalcul().setDisable(false);
            this.vue.getSelect().setDisable(false);
            this.vue.getSupprimer().setDisable(false);
            this.vue.getCpCouleur().setDisable(false);
            this.vue.getZoneconstructible().setDisable(false);
            this.vue.getChoiceBoxN().setDisable(false);
            this.vue.getCatalogueBarre().setDisable(false);
        }
        this.etat = etat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        if (this.etat == 20) {
            double px = t.getX();
            double py = t.getY();
            this.pointzc[0] = new Point(px, py);
            this.changeEtat(21);
        } else if (this.etat == 21) {
            double px = t.getX();
            double py = t.getY();
            this.pointzc[1] = new Point(px, py);
            this.changeEtat(22);
        } else if (this.etat == 22) {
            double px = t.getX();
            double py = t.getY();
            this.pointzc[2] = new Point(px, py);
            this.changeEtat(23);
        } else if (this.etat == 23) {
            double px = t.getX();
            double py = t.getY();
            this.pointzc[3] = new Point(px, py);
            Treillis model = this.vue.getModel();
            model.addTerrain(new Terrain(pointzc[0], pointzc[1],
                    pointzc[2], pointzc[3]));
            this.vue.redrawAll();
            this.changeEtat(29);
        } else if (this.etat == 30 || this.etat == 39) {
            double px = t.getX();
            double py = t.getY();
            this.pointTT[0] = new Point(px, py);
            this.changeEtat(31);
        } else if (this.etat == 31) {
            double px = t.getX();
            double py = t.getY();
            this.pointTT[1] = new Point(px, py);
            this.changeEtat(32);
        } else if (this.etat == 32) {
            double px = t.getX();
            double py = t.getY();
            this.pointTT[2] = new Point(px, py);
            Treillis model = this.vue.getModel();
            model.getBase().addTriangleTerrain(new TriangleTerrain(pointTT[0], pointTT[1], pointTT[2]));
            this.vue.redrawAll();
            this.changeEtat(39);
        } else if (this.etat == 40) {
            double px = t.getX();
            double py = t.getY();
            Treillis model = this.vue.getModel();
            model.addNoeudSimple(new NoeudSimple(new Point(px, py)));
            this.vue.redrawAll();
            this.changeEtat(40);
        } else if (this.etat == 50) {
            Treillis model = this.vue.getModel();
//            Alert dialog = new Alert(AlertType.INFORMATION);
//            dialog.setTitle("An information dialog-box");
//            dialog.setHeaderText("An information dialogwithheader");
//            dialog.setContentText("Vueillez selctioner le segment sur lesuel\n"
//                    + "vous voudriez mettre votre Noeud Appui");
//            dialog.showAndWait();

            Point clic = new Point(t.getX(), t.getY());
            SegmentTerrain segt = model.plusProcheST(clic);
            TextInputDialog inDialog = new TextInputDialog("Guest");
            inDialog.setTitle("A Text-Input Dialog");
            inDialog.setHeaderText("Le segment sélectioné mesure :"
                    + segt.getDebut().distancePoint(segt.getFin())
                    + "donner le raport de proximité entre le debut du segment "
                    + "(" + segt.getDebut().toString() + ") et le noeud appui "
                    + "(le nombre doit être compris entre 0 et 1 (attentionmettre un '.'");
            inDialog.setContentText("distance:");
            Optional<String> textIn = inDialog.showAndWait();
            //---Getresponsevalue (traditionalway)
            if (textIn.isPresent()) {
                double distance = Double.parseDouble(textIn.get());
                AppuiSimple as = new AppuiSimple(distance, segt);
                model.addAppuiSimple(as);
                segt.add(as);
                this.vue.redrawAll();
            }
            this.changeEtat(50);
//            double distanceEC = segt.distancePoint(clic);
//            double distance90 = segt.distancePoint(clic);
        } else if (this.etat == 60) {
            Treillis model = this.vue.getModel();
//           
            Point clic = new Point(t.getX(), t.getY());
            SegmentTerrain segt = model.plusProcheST(clic);
            TextInputDialog inDialog = new TextInputDialog("Guest");
            inDialog.setTitle("A Text-Input Dialog");
            inDialog.setHeaderText("Le segment sélectioné mesure :"
                    + segt.getDebut().distancePoint(segt.getFin())
                    + "donner le raport de proximité entre le debut du segment "
                    + "(" + segt.getDebut().toString() + ") et le noeud appui "
                    + "(le nombre doit être compris entre 0 et 1 (attentionmettre un '.'");
            inDialog.setContentText("distance:");
            Optional<String> textIn = inDialog.showAndWait();
            //---Getresponsevalue (traditionalway)
            if (textIn.isPresent()) {
                double distance = Double.parseDouble(textIn.get());
                AppuiDouble ad= new AppuiDouble(distance, segt);
                model.addAppuiDouble(ad);
                segt.add(ad);
                this.vue.redrawAll();
            }
            this.changeEtat(60);
        } else if (this.etat == 70) {
            Treillis model = this.vue.getModel();
            model.addTypeBarre(new TypeBarre(20, 40, 67, 700, 800));
            Alert dialogC = new Alert(AlertType.CONFIRMATION);
            dialogC.setTitle("A confirmation choix type barre");
            dialogC.setHeaderText(null);
            dialogC.setContentText("Le type de barre selectioné et :" + model.getCatalogueBarre().get(0).toString());
            Optional<ButtonType> answer = dialogC.showAndWait();
            if (answer.get() == ButtonType.OK) {
                Alert dBox = new Alert(AlertType.CONFIRMATION);
                dBox.setTitle("choix du type de noeud");
                dBox.setHeaderText("Ma barre !");
                dBox.setContentText("Choisissez le type de noeud débutant votre "
                        + "barre ou si vouvoulez creer votre barre à partir de "
                        + "noeud déjà existant");
                ButtonType btnNS = new ButtonType("Noeud Simple");
                ButtonType btnAS = new ButtonType("Appui Simple");
                ButtonType btnAD = new ButtonType("Appui Double");
                ButtonType btoldn = new ButtonType("Noeud déjà existant");
                ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                dBox.getButtonTypes().setAll(btnNS, btnAS, btnAD, btoldn, btnCancel);
                Optional<ButtonType> choice = dBox.showAndWait();
                if (choice.get() == btnNS) {
                    double px = t.getX();
                    double py = t.getY();
                    this.pointB[0] = new Point(px, py);
                } else if (choice.get() == btnAS) {
                    // model.addBarre(new Barre(new AppuiSimple(this.pointB[0]), new AppuiSimple(this.pointB[1])));
                } else if (choice.get() == btnAD) {
                } else if (choice.get() == btoldn) {
                    this.noeudB[0] = model.plusProcheN(new Point(t.getX(), t.getY()));
                } else {
                }
                this.changeEtat(71);
            } else {
                this.changeEtat(70);
            }
        } else if (this.etat == 71) {
            Treillis model = this.vue.getModel();
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de noeud");
            dBox.setHeaderText("Ma barre !");
            dBox.setContentText("Choisissez le type de noeud débutant votre "
                    + "barre ou si vouvoulez creer votre barre à partir de "
                    + "noeud déjà existant");
            ButtonType btnNS = new ButtonType("Noeud Simple");
            ButtonType btnAS = new ButtonType("Appui Simple");
            ButtonType btnAD = new ButtonType("Appui Double");
            ButtonType btoldn = new ButtonType("Noeud déjà existant");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnNS, btnAS, btnAD, btoldn, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnNS) {
                double px = t.getX();
                double py = t.getY();
                this.pointB[1] = new Point(px, py);
                model.addBarre(new Barre(new NoeudSimple(this.pointB[0]), new NoeudSimple(this.pointB[1]), 50, 70, 78, 99, 800));
                model.addNoeudSimple(new NoeudSimple(this.pointB[0]));
                model.addNoeudSimple(new NoeudSimple(this.pointB[1]));

            } else if (choice.get() == btnAS) {
                // model.addBarre(new Barre(new AppuiSimple(this.pointB[0]), new AppuiSimple(this.pointB[1])));
            } else if (choice.get() == btnAD) {
            } else if (choice.get() == btoldn) {
                this.noeudB[1] = model.plusProcheN(new Point(t.getX(), t.getY()));
                model.addBarre(new Barre(this.noeudB[0], this.noeudB[1], 50, 70, 78, 99, 800));
            } else {
            }
            this.changeEtat(70);
            this.vue.redrawAll();
        } else if (this.etat == 100) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.selction(model, clic);
            this.vue.redrawAll();
            this.changeEtat(100);
        } else if (this.etat == 110) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.supression(model, clic);
            this.vue.redrawAll();
            this.changeEtat(110);
        }

    }

    void boutonSelect(ActionEvent t
    ) {
        this.changeEtat(100);
    }

    void boutonZoneConstructible(ActionEvent t
    ) {
        this.changeEtat(20);
    }

    void boutontriangle_Terrain(ActionEvent t
    ) {
        this.changeEtat(30);
    }

    void splitMenuButtonNS(ActionEvent t
    ) {
        this.changeEtat(40);
    }

    void splitMenuButtonAS(ActionEvent t
    ) {
        this.changeEtat(50);
    }

    void splitMenuButtonAD(ActionEvent t
    ) {
        this.changeEtat(60);
    }

    void buttonBarre1() {
        this.changeEtat(70);
    }

    void buttonBarre2(ActionEvent t
    ) {
        this.changeEtat(80);
    }

    void buttonBarre3(ActionEvent t
    ) {
        this.changeEtat(90);
    }

    void bouttonSuprimer(ActionEvent t
    ) {
        this.changeEtat(110);
    }

    public void selction(Treillis model, Point clic) {
        SegmentTerrain segt = model.plusProcheST(clic);
        Noeud noeud = model.plusProcheN(clic);
        Barre barre = model.plusProcheB(clic);
        String res = model.lePlusProche();
        if (res.equals("N")) {
            noeud.setColor(Color.GREEN);
            System.out.println("noeud" + noeud.getColor());
        }
        if (res.equals("B")) {
            barre.setColor(Color.GREEN);
            System.out.println("barre " + barre.getColor());
        }
        if (res.equals("S")) {
            segt.setColor(Color.GREEN);

            System.out.println("seg " + segt.getColor());
        }
        if (res.equals("NS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Noeud ou Segment Terrain ???");
            dBox.setContentText("Voulez vous séléctinez le noeud ou le segment terrain");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnS = new ButtonType("Sgment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnN, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnN) {
                System.out.println("noued " + noeud.getColor());
                noeud.setColor(Color.GREEN);
            }
            if (choice.get() == btnS) {
                System.out.println("segt " + segt.getColor());
                segt.setColor(Color.GREEN);
            }
        }
        if (res.equals("BS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Segment Terrain ???");
            dBox.setContentText("Voulez vous séléctinez la barre ou le segment terrain");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnS = new ButtonType("Sgment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                barre.setColor(Color.GREEN);
            }
            if (choice.get() == btnS) {
                System.out.println("segt " + segt.getColor());
                segt.setColor(Color.GREEN);
            }
        }
        if (res.equals("BN")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Noeud ???");
            dBox.setContentText("Voulez vous séléctinez la barre ou le noeud");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnN, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                barre.setColor(Color.GREEN);
            }
            if (choice.get() == btnN) {
                System.out.println("noued " + segt.getColor());
                noeud.setColor(Color.GREEN);
            }
        }
    }

    public void supression(Treillis model, Point clic) {
        SegmentTerrain segt = model.plusProcheST(clic);
        Noeud noeud = model.plusProcheN(clic);
        Barre barre = model.plusProcheB(clic);
        String res = model.lePlusProche();
        if (res.equals("N")) {
            model.removeNoeud(noeud);
            System.out.println("noeud");
        }
        if (res.equals("B")) {
            model.removeBarre(barre);
            System.out.println("barre ");
        }
        if (res.equals("S")) {
            TriangleTerrain tt = model.segtTrouveTT(segt);
            System.out.println("tt ="+tt+"\n");
            System.out.println("tt.getSegTerrain1().getAppartient().size()"+tt.getSegTerrain1().getAppartient().size());
            for (int i = 0; i < tt.getSegTerrain1().getAppartient().size(); i++) {
                System.out.println("prout 1");
                System.out.println("liste noeud seg1 : " +tt.getSegTerrain1().getAppartient().get(i));
                
            }for (int i = 0; i < tt.getSegTerrain2().getAppartient().size(); i++) {
                System.out.println("prout 2");
                System.out.println("liste noeud seg2 : " +tt.getSegTerrain2().getAppartient().get(i));
                
            } for (int i = 0; i < tt.getSegTerrain3().getAppartient().size(); i++) {
                System.out.println("prout 3");
                System.out.println("liste noeud seg3 : " +tt.getSegTerrain3().getAppartient().get(i));
                
            }
            model.removeTriangleTerrain(tt);
            System.out.println("seg ");
        }
        if (res.equals("NS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Noeud ou Segment Terrain ?");
            dBox.setContentText("Voulez vous sélectionnez le noeud ou le segment terrain");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnS = new ButtonType("Segment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnN, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnN) {
                model.removeNoeud(noeud);
                System.out.println("noeud");
            }
            if (choice.get() == btnS) {
                TriangleTerrain tt = model.segtTrouveTT(segt);
                model.removeTriangleTerrain(tt);
                System.out.println("seg ");
            }
        }
        if (res.equals("BS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Segment Terrain ???");
            dBox.setContentText("Voulez vous séléctinez la barre ou le segment terrain");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnS = new ButtonType("Sgment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                model.removeBarre(barre);
                System.out.println("barre ");
            }
            if (choice.get() == btnS) {
                TriangleTerrain tt = model.segtTrouveTT(segt);
                model.removeTriangleTerrain(tt);
                System.out.println("seg ");
            }
        }
        if (res.equals("BN")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Noeud ???");
            dBox.setContentText("Voulez vous séléctinez la barre ou le noeud");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnN, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                model.removeBarre(barre);
                System.out.println("barre ");
            }
            if (choice.get() == btnN) {
                model.removeNoeud(noeud);
                System.out.println("noeud");
            }
        }
    }
}
