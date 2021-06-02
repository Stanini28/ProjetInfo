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
import fr.insa.allouche.infoprojet.calculMatrice.Calcul;
import static fr.insa.allouche.infoprojet.calculMatrice.Calcul.Prix;
import fr.insa.allouche.infoprojet.calculMatrice.Matrice;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Portable
 */
public class Controleur {

    //private double[] type1 = {20, 40, 200, 700, 800};
    private TypeBarre type1 = new TypeBarre(20, 10, 200, 900, -700, Color.LIMEGREEN);
    private TypeBarre type2 = new TypeBarre(30, 10, 300, 500, -500, Color.GREEN);
    private TypeBarre type3 = new TypeBarre(40, 10, 400, 10000, -10000, Color.GREY);
    private Point[] pointzc = new Point[4];
    private Point[] pointTT = new Point[3];
    private Point[] pointB = new Point[2];
    private Noeud[] noeudB = new Noeud[2];
    private AppuiDouble[] noeudADB = new AppuiDouble[2];
    private AppuiSimple[] noeudASB = new AppuiSimple[2];
    private interfaceDessin vue;
    private Color couleur;

    private int etat;

    public Controleur(interfaceDessin vue) {
        this.vue = vue;
    }

    // Méthode permettant de définir l'état des boutons selon les posiblité 
    //offerte à l'utilisateur pour créer le treillis
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
            this.vue.getPrix().setDisable(true);
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
            this.vue.getPrix().setDisable(false);
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
            this.vue.getModel().addTerrain(new Terrain(pointzc[0], pointzc[1],
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
            TriangleTerrain tT = new TriangleTerrain(pointTT[0], pointTT[1], pointTT[2]);
            // this.vue.getModel().getBase().addTriangleTerrain(tT);
            this.vue.getModel().addTriangleTerrain(tT);
            System.out.println("identifiant Triangle Terrain : " + tT.getId());
            this.vue.redrawAll();
            this.changeEtat(39);
        } else if (this.etat == 40) {
            double px = t.getX();
            double py = t.getY();
            Point clic = new Point(px, py);
            if (this.vue.getModel().nZoneConstructible(clic) == true) {
                this.vue.getModel().addNoeudSimple(new NoeudSimple(clic));
            } else {
                Alert dialogW = new Alert(AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null);  // No header
                dialogW.setContentText("Caution : Vous ne pouvez pas dessinez votre"
                        + " treillis en dehors de la zone constructible. "
                        + "\nSelectionez une nouvelle position !");
                dialogW.showAndWait();
            }
            this.vue.redrawAll();
            this.changeEtat(40);
        } else if (this.etat == 50) {
//            Treillis model = this.vue.getModel();
//            System.out.println("model :" + model);
//            Alert dialog = new Alert(AlertType.INFORMATION);
//            dialog.setTitle("An information dialog-box");
//            dialog.setHeaderText("An information dialogwithheader");
//            dialog.setContentText("Vueillez selctioner le segment sur lesuel\n"
//                    + "vous voudriez mettre votre Noeud Appui");
//            dialog.showAndWait();

            Point clic = new Point(t.getX(), t.getY());
            creationAS(clic);
            this.vue.redrawAll();
            this.changeEtat(50);
//            double distanceEC = segt.distancePoint(clic);
//            double distance90 = segt.distancePoint(clic);
        } else if (this.etat == 60) {
            Point clic = new Point(t.getX(), t.getY());
            creationAD(clic);

            this.vue.redrawAll();
            this.changeEtat(60);
        } else if (this.etat == 70) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre1(clic, 70);
            this.vue.redrawAll();
        } else if (this.etat == 71) {
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre2(clic, this.type1, Color.LIMEGREEN, 71);
            this.vue.redrawAll();
        } else if (this.etat == 80) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre1(clic, 80);
            this.vue.redrawAll();
        } else if (this.etat == 81) {
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre2(clic, this.type2, Color.GREEN, 81);
            this.vue.redrawAll();
        } else if (this.etat == 90) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre1(clic, 90);
            this.vue.redrawAll();
        } else if (this.etat == 91) {
            Point clic = new Point(t.getX(), t.getY());
            this.typeBarre2(clic, this.type3, Color.GREY, 91);
            this.vue.redrawAll();
        } else if (this.etat == 95) {
            Alert dialogC1 = new Alert(AlertType.CONFIRMATION);
            dialogC1.setTitle("Confirmation du choix du type de barre");
            dialogC1.setHeaderText(null);
            dialogC1.setContentText("Le type de barre 1 est :" + this.type1.toString());
            Optional<ButtonType> answer1 = dialogC1.showAndWait();

            Alert dialogC2 = new Alert(AlertType.CONFIRMATION);
            dialogC2.setTitle("Confirmation du choix du type de barre");
            dialogC2.setHeaderText(null);
            dialogC2.setContentText("Le type de barre 2 est :" + this.type2.toString());
            Optional<ButtonType> answer2 = dialogC2.showAndWait();

            Alert dialogC3 = new Alert(AlertType.CONFIRMATION);
            dialogC3.setTitle("Confirmation du choix du type de barre");
            dialogC3.setHeaderText(null);
            dialogC3.setContentText("Le type de barre 3 est :" + this.type3.toString());
            Optional<ButtonType> answer3 = dialogC3.showAndWait();
            this.vue.redrawAll();
        } else if (this.etat == 100) {
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.selection(model, clic);
            this.vue.redrawAllSelect();
            this.changeEtat(100);
        } else if (this.etat == 110) {
            try {
                this.vue.getModel().sauvegarde(new File("Test 1"));
            } catch (IOException ex) {
                throw new Error("Problème :" + ex.getMessage());
            }
            Treillis model = this.vue.getModel();
            Point clic = new Point(t.getX(), t.getY());
            this.supression(model, clic);
            this.vue.redrawAll();
            this.changeEtat(110);
        } else if (this.etat == 120) {
//            this.vue.getModel().removeAllBarre();
//            this.vue.getModel().removeAllNoeud();
//            this.vue.getModel().removeAllTypeBarre();
//            this.vue.getModel().removeTerrain();
//            this.vue.setModel(null);
            this.vue.setModel(new Treillis());
            this.vue.redrawAll();
            this.changeEtat(20);

            System.out.println(this.vue.getModel().toString());

            
            
            //Méthode permettant de voir si le treillis est isostatique.
            //Il existe une condition. Il faut qu'il y est autant de forces inconnues
            // (Traction, Compression, Réaction) que 2 fois le nombre de noeuds.
            //Comme cela, la matrice pourra fonctionner sans problèmes.
        } else if (this.etat == 130) {
            Treillis model = this.vue.getModel();
int u= (model.getCompose().size()) + model.getAsimp().size()+ 2*model.getAdoub().size();
            if (2*model.getContient().size() != u) {
                Alert dialogW = new Alert(AlertType.ERROR);
                dialogW.setTitle("Impossibilité!");
                dialogW.setHeaderText(null);
                if (model.getCompose().size() > u) {
                    dialogW.setContentText("Merci d'enlever les barres superflues. Il y en a "
                            + (u - model.getContient().size()*2) + ".");
                } else {
                    dialogW.setContentText("Merci de rajouter des barres. Il en manque "
                            + (model.getContient().size()*2 - u) + ".");
                    dialogW.showAndWait();
                }
            } else {
                String S = Calcul.regroup(model);
                Alert dialogW = new Alert(AlertType.INFORMATION);

                dialogW.setTitle("Isostatisme du Treillis");
                if (S == "") {
                    dialogW.setHeaderText(null);
                    dialogW.setContentText("Le Treillis est isostatique et les types des barres sont bons.");
                    dialogW.showAndWait();
                }
                if (S != "") {
                    if (S == "Le treillis n'est pas isostatique!") {
                        dialogW.setHeaderText(null);
                        dialogW.setContentText(S);
                        dialogW.showAndWait();
                    } else {
                        dialogW.setHeaderText(null);
                        dialogW.setContentText("Le Treillis est isostatique. Cependant, il n'est pas fonctionnel car : " + S);
                        dialogW.showAndWait();
                    }
                }
            }
            this.vue.redrawAllPbBarre();
            this.changeEtat(39);
        } else if (this.etat == 135) {
            Point clic = new Point(t.getX(), t.getY());
            this.changeCouleur(this.vue.getModel(), clic);
            this.vue.redrawAll();
        } else if (this.etat == 140) {
            Treillis model = this.vue.getModel();
            double a = Prix(model);

            Alert dialogW = new Alert(AlertType.INFORMATION);
            dialogW.setTitle("Prix du Treillis");
            dialogW.setHeaderText(null);
            dialogW.setContentText("Le prix du Treillis est de " + a + "€");
            dialogW.showAndWait();
            this.changeEtat(39);
        }

        System.out.println(this.vue.getModel().toString());
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

    void buttonBarre4(ActionEvent t) {
        this.changeEtat(95);
    }

    void bouttonSuprimer(ActionEvent t
    ) {
        this.changeEtat(110);
    }

    void bouttonRemoveAll(ActionEvent t) {
        this.changeEtat(120);
    }

    void bouttonCalcul(ActionEvent t) {
        this.changeEtat(130);
    }

    void bouttonCouleur(Color couleur) {
        this.changeEtat(135);
        this.couleur = couleur;
    }

    void bouttonPrix(ActionEvent t) {
        this.changeEtat(140);
    }

    // méthode perméttant de séléctioner un objet selon la proximité de l'objet 
    //le plus proche
    //Les méthode "setCouleurSelect" permettent de modifier la couleur de l'objet
    //de manière éphémère de telle sorte que losrque l'on appui sur un autre boutton 
    //la couleur de base de l'objet revienne
    //La méthode redrawAllSelect à était créé à cet effet
    public void selection(Treillis model, Point clic) {
        SegmentTerrain segt = model.plusProcheST(clic);
        Noeud noeud = model.plusProcheN(clic);
        Barre barre = model.plusProcheB(clic);
        String res = model.lePlusProche();
        if (res.equals("N")) {
            this.vue.getModel().getObjSelect().add(noeud);
            System.out.println("noeud" + noeud.getColor());
        }
        if (res.equals("B")) {
            this.vue.getModel().getObjSelect().add(barre);
            System.out.println("barre " + barre.getColor());
        }
        if (res.equals("S")) {
            this.vue.getModel().getObjSelect().add(segt);
            System.out.println("seg " + segt.getColor());
        }
        if (res.equals("NS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Noeud ou Segment Terrain ?");
            dBox.setContentText("Voulez-vous sélectionner le noeud ou le segment terrain?");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnS = new ButtonType("Segment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnN, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnN) {
                System.out.println("Noeud " + noeud.getColor());
                this.vue.getModel().getObjSelect().add(noeud);
            }
            if (choice.get() == btnS) {
                System.out.println("Segment " + segt.getColor());
                this.vue.getModel().getObjSelect().add(segt);
            }
        }
        if (res.equals("BS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Segment Terrain ?");
            dBox.setContentText("Voulez-vous sélectionner la barre ou le segment terrain?");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnS = new ButtonType("Segment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                this.vue.getModel().getObjSelect().add(barre);
            }
            if (choice.get() == btnS) {
                System.out.println("Segment " + segt.getColor());
                this.vue.getModel().getObjSelect().add(segt);
            }
        }
        if (res.equals("BN")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Noeud ?");
            dBox.setContentText("Voulez-vous sélectionner la barre ou le noeud?");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnN, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                this.vue.getModel().getObjSelect().add(barre);
            }
            if (choice.get() == btnN) {
                System.out.println("Noeud " + segt.getColor());
                this.vue.getModel().getObjSelect().add(noeud);
            }
        }

    }

    public void changeCouleur(Treillis model, Point clic) {
        SegmentTerrain segt = model.plusProcheST(clic);
        Noeud noeud = model.plusProcheN(clic);
        Barre barre = model.plusProcheB(clic);
        String res = model.lePlusProche();
        if (res.equals("N")) {
            noeud.setColor(couleur);
            System.out.println("noeud" + noeud.getColor());
        }
        if (res.equals("B")) {
            barre.setColor(couleur);
            barre.getType().setCouleur(couleur);
            System.out.println("barre " + barre.getColor());
        }
        if (res.equals("S")) {
            segt.setColor(couleur);

            System.out.println("seg " + segt.getColor());
        }
        if (res.equals("NS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Noeud ou Segment Terrain ?");
            dBox.setContentText("Voulez-vous sélectionnez le noeud ou le segment terrain?");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnS = new ButtonType("Segment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnN, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnN) {
                System.out.println("noued " + noeud.getColor());
                noeud.setColor(couleur);
            }
            if (choice.get() == btnS) {
                System.out.println("segt " + segt.getColor());
                segt.setColor(couleur);
            }
        }
        if (res.equals("BS")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la sélection");
            dBox.setHeaderText("Barre ou Segment Terrain ?");
            dBox.setContentText("Voulez-vous sélectionner la barre ou le segment terrain?");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnS = new ButtonType("Segment Terrain");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnS, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                barre.setColor(couleur);
                barre.getType().setCouleur(couleur);
            }
            if (choice.get() == btnS) {
                System.out.println("segt " + segt.getColor());
                segt.setColor(couleur);
            }
        }
        if (res.equals("BN")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Noeud ?");
            dBox.setContentText("Voulez-vous sélectionner la barre ou le noeud");
            ButtonType btnB = new ButtonType("Barre");
            ButtonType btnN = new ButtonType("Noeud");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            dBox.getButtonTypes().setAll(btnB, btnN, btnCancel);
            Optional<ButtonType> choice = dBox.showAndWait();
            if (choice.get() == btnB) {
                System.out.println("Barre " + barre.getColor());
                barre.setColor(couleur);
                barre.getType().setCouleur(couleur);
            }
            if (choice.get() == btnN) {
                System.out.println("Noeud " + segt.getColor());
                noeud.setColor(couleur);
            }
        }

    }

    //Méthode permétttant de supprimer un objet du treillis
    //Nous avons fait le choix de le pas donner la possiblité à l'utilisateur
    //de supprimer la zone constructible ou encore les triangles terrains
    public void supression(Treillis model, Point clic) {
        SegmentTerrain segt = model.plusProcheST(clic);
        Noeud noeud = model.plusProcheN(clic);
        Barre barre = model.plusProcheB(clic);
        String res = model.lePlusProche();
        if (res.equals("N") && noeud != null) {
            if (noeud instanceof NoeudSimple) {
                model.removeNoeudSimple((NoeudSimple) noeud);
            } else if (noeud instanceof AppuiSimple) {
                model.removeAppuiSimple((AppuiSimple) noeud);
            } else if (noeud instanceof AppuiDouble) {
                model.removeAppuiDouble((AppuiDouble) noeud);
            } else {
                System.out.println("instance of rien... pb");
            }
            //model.removeNoeud(noeud);
            System.out.println("noeud");
        }
        if (res.equals("B") && barre != null) {
            model.removeBarre(barre);
            System.out.println("barre ");
        }
//        if (res.equals("S")) {
//            TriangleTerrain tt = model.segtTrouveTT(segt);
//            System.out.println("tt =" + tt + "\n");
//            System.out.println("tt.getSegTerrain1().getAppartient().size()" + tt.getSegTerrain1().getAppartient().size());
//            for (int i = 0; i < tt.getSegTerrain1().getAppartient().size(); i++) {
//                System.out.println("prout 1");
//                System.out.println("liste noeud seg1 : " + tt.getSegTerrain1().getAppartient().get(i));
//
//            }
//            for (int i = 0; i < tt.getSegTerrain2().getAppartient().size(); i++) {
//                System.out.println("prout 2");
//                System.out.println("liste noeud seg2 : " + tt.getSegTerrain2().getAppartient().get(i));
//
//            }
//            for (int i = 0; i < tt.getSegTerrain3().getAppartient().size(); i++) {
//                System.out.println("prout 3");
//                System.out.println("liste noeud seg3 : " + tt.getSegTerrain3().getAppartient().get(i));
//
//            }
//            model.removeTriangleTerrain(tt);
//            System.out.println("seg ");
//        }
        if (res.equals("NS") && noeud != null) {
//            Alert dBox = new Alert(AlertType.CONFIRMATION);
//            dBox.setTitle("choix du type de la selection");
//            dBox.setHeaderText("Noeud ou Segment Terrain ?");
//            dBox.setContentText("Voulez vous sélectionnez le noeud ou le segment terrain");
//            ButtonType btnN = new ButtonType("Noeud");
//            ButtonType btnS = new ButtonType("Segment Terrain");
//            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
//            dBox.getButtonTypes().setAll(btnN, btnS, btnCancel);
//            Optional<ButtonType> choice = dBox.showAndWait();
//            if (choice.get() == btnN) {
//                model.removeNoeud(noeud);
//                System.out.println("noeud");
//            }
//            if (choice.get() == btnS) {
//                TriangleTerrain tt = model.segtTrouveTT(segt);
//                model.removeTriangleTerrain(tt);
//                System.out.println("seg ");
//            }
            if (noeud instanceof NoeudSimple) {
                model.removeNoeudSimple((NoeudSimple) noeud);
            } else if (noeud instanceof AppuiSimple) {
                model.removeAppuiSimple((AppuiSimple) noeud);
            } else if (noeud instanceof AppuiDouble) {
                model.removeAppuiDouble((AppuiDouble) noeud);
            } else {
                System.out.println("instance of rien... pb");
            }
            //model.removeNoeud(noeud);
            System.out.println("noeud");
        }
        if (res.equals("BS") && barre != null) {
//            Alert dBox = new Alert(AlertType.CONFIRMATION);
//            dBox.setTitle("choix du type de la selection");
//            dBox.setHeaderText("Barre ou Segment Terrain ???");
//            dBox.setContentText("Voulez vous séléctinez la barre ou le segment terrain");
//            ButtonType btnB = new ButtonType("Barre");
//            ButtonType btnS = new ButtonType("Sgment Terrain");
//            ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
//            dBox.getButtonTypes().setAll(btnB, btnS, btnCancel);
//            Optional<ButtonType> choice = dBox.showAndWait();
//            if (choice.get() == btnB) {
//                model.removeBarre(barre);
//                System.out.println("barre ");
//            }
//            if (choice.get() == btnS) {
//                TriangleTerrain tt = model.segtTrouveTT(segt);
//                model.removeTriangleTerrain(tt);
//                System.out.println("seg ");
//            }
            model.removeBarre(barre);
            System.out.println("barre ");
        }
        if (res.equals("BN")) {
            Alert dBox = new Alert(AlertType.CONFIRMATION);
            dBox.setTitle("choix du type de la selection");
            dBox.setHeaderText("Barre ou Noeud ?");
            dBox.setContentText("Voulez-vous sélectionner la barre ou le noeud?");
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
                if (noeud instanceof NoeudSimple) {
                    model.removeNoeudSimple((NoeudSimple) noeud);
                } else if (noeud instanceof AppuiSimple) {
                    model.removeAppuiSimple((AppuiSimple) noeud);
                } else if (noeud instanceof AppuiDouble) {
                    model.removeAppuiDouble((AppuiDouble) noeud);
                } else {
                    System.out.println("instance of rien... pb");
                }
                //model.removeNoeud(noeud);
                System.out.println("noeud");
            }
        }
    }

    //Les deux prochaine méthode permmettent de créer des noeud appuis
    // Elles comportent des fenêtre pop permettant une interaction avec l'utilisateur
    public AppuiDouble creationAD(Point clic) {

        SegmentTerrain segt = this.vue.getModel().plusProcheST(clic);

        TextInputDialog inDialog = new TextInputDialog("Guest");
        inDialog.setTitle("A Text-Input Dialog");
        inDialog.setHeaderText("Le segment sélectionné mesure :"
                + segt.getDebut().distancePoint(segt.getFin())
                + ". Donner le raport de proximité entre le debut du segment "
                + "(" + segt.getDebut().toString() + ") et le noeud appui "
                + ". Le nombre doit être compris entre 0 et 1. Veuillez mettre un '.' !");
        inDialog.setContentText("distance:");
        Optional<String> textIn = inDialog.showAndWait();
        //---Getresponsevalue (traditionalway)
        if (textIn.isPresent()) {
            double distance = Double.parseDouble(textIn.get());
            AppuiDouble ad = new AppuiDouble(distance, segt, segt.getFaitPartieDe());
            if (this.vue.getModel().nZoneConstructible(ad.getPosition()) == true) {
                this.vue.getModel().addAppuiDouble(ad);
                segt.add(ad);
                System.out.println("dans creation ad\n" + ad.getappartient().getFaitPartieDe());
                System.out.println("");
                return ad;
            } else {
                segt.remove(ad);
                Alert dialogW = new Alert(AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null);  // No header
                dialogW.setContentText("Caution : Vous ne pouvez pas dessinez votre"
                        + " treillis en dehors de la zone constructible "
                        + "\nSelectionez une nouvelle position !");
                dialogW.showAndWait();
                return null;
            }
        } else {
            return null;
        }
    }

    public AppuiSimple creationAS(Point clic) {
        SegmentTerrain segt = this.vue.getModel().plusProcheST(clic);
        TextInputDialog inDialog = new TextInputDialog("Guest");
        inDialog.setTitle("A Text-Input Dialog");
        inDialog.setHeaderText("Le segment sélectionné mesure :"
                + segt.getDebut().distancePoint(segt.getFin())
                + ". Donner le raport de proximité entre le debut du segment "
                + "(" + segt.getDebut().toString() + ") et le noeud appui "
                + ". Le nombre doit être compris entre 0 et 1. Veuillez mettre un '.' !");
        inDialog.setContentText("distance:");
        Optional<String> textIn = inDialog.showAndWait();
        //---Getresponsevalue (traditionalway)
        if (textIn.isPresent()) {
            double distance = Double.parseDouble(textIn.get());
            AppuiSimple as = new AppuiSimple(distance, segt, segt.getFaitPartieDe());
            if (this.vue.getModel().nZoneConstructible(as.getPosition()) == true) {
                this.vue.getModel().addAppuiSimple(as);
                //segt.add(as);
                return as;
            } else {
                segt.remove(as);
                Alert dialogW = new Alert(AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null);  // No header
                dialogW.setContentText("Caution : Vous ne pouvez pas dessinez votre"
                        + " treillis en dehors de la zone constructible "
                        + "\nSelectionez une nouvelle position !");
                dialogW.showAndWait();
                return null;
            }
        } else {
            return null;
        }
    }

    //methodde permettant de calculer la longeur d'une barre et de vérifier que
    //la barre que veux crée l'utilisateur n'est pas trop grande ou trop petite
    public boolean bonneLongeurB(Point clic, TypeBarre type) {
        double longeure = Barre.longueur(this.noeudB[0].getPosition(), clic);
        if (longeure < type.getlMin()
                || longeure > type.getlMax()) {
            Alert dialogW = new Alert(AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null);  // No header
            if (longeure < type.getlMin()) {
                dialogW.setContentText("Caution : La barre est trop petite, elle mesure " + longeure
                        + "\n Alors que la longueur min pour votre type de barre est " + type.getlMin() + " !\n"
                        + "Veuillez cliquer à nouveau sur la zone dessin pour definir un nouveau noeud de fin de barre");
            } else {
                dialogW.setContentText("Caution : La barre est trop grande, elle mesure " + longeure
                        + ".\n Alors que la longeur max pour votre type de barre est " + type.getlMax() + " !\n"
                        + "Veuillez cliquer à nouveau sur la zone dessin pour definir un nouveau noeud de fin de barre");
            }
            dialogW.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    //Les deux prochaines méthodes on pour objectifs de créer un barre 
    //la première va garder en mémoire le premier noeud grâce à un attribut
    //la seconde va créer la barre à partir des deux noued
    //L'utilisateur à l'opportunité de créer sa barre à partir 
    //"d'un noued déjà existant", "d'un noued simple" "d'un noeud appui double"
    //"noeud appui simple"
    public void typeBarre1(Point clic, int etat) {
        Alert dBox = new Alert(AlertType.CONFIRMATION);
        dBox.setTitle("choix du type de noeud");
        dBox.setHeaderText("Ma barre !");
        dBox.setContentText("Choisissez le type de noeud débutant votre "
                + "barre ou si vous voulez créer votre barre à partir d'un "
                + "noeud déjà existant");
        ButtonType btnNS = new ButtonType("Noeud Simple");
        ButtonType btnAS = new ButtonType("Appui Simple");
        ButtonType btnAD = new ButtonType("Appui Double");
        ButtonType btoldn = new ButtonType("Noeud déjà existant");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dBox.getButtonTypes().setAll(btnNS, btnAS, btnAD, btoldn, btnCancel);
        Optional<ButtonType> choice = dBox.showAndWait();

        // boutton noeud simple pressé
        if (choice.get() == btnNS) {
            //condition pour savoir si le noeud simple est bien dans la zonne constructible
            if (this.vue.getModel().nZoneConstructible(clic) == true) {
                NoeudSimple ns = new NoeudSimple(clic);
                this.noeudB[0] = ns;
                this.vue.getModel().addNoeudSimple(ns);
                this.changeEtat(etat + 1);
//                TextInputDialog inDialog = new TextInputDialog("100");
//
//                inDialog.setTitle("Poids du Noeud Simple");
//                inDialog.setHeaderText("Veuillez entrer un poids pour le Noeud Simple ");
//                inDialog.setContentText("Poids : ");
//
//                Optional<String> textIn = inDialog.showAndWait();
//
//                if (textIn.isPresent()) {
//                    ns.setForceY(Double.parseDouble(textIn.get()));
//                }

            } else {
                Alert dialogW = new Alert(AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null);  // No header
                dialogW.setContentText("Caution : Vous ne pouvez pas dessinez votre"
                        + " treillis en dehors de la zone constructible "
                        + ".\n Selectionez une nouvelle position !");
                dialogW.showAndWait();
                this.changeEtat(etat);
            }
            // boutton appui simple pressé
        } else if (choice.get() == btnAS) {
            AppuiSimple as = creationAS(clic);
            //condition pour savoir si le appui simple à pu être crée
            if (as != null) {
                System.out.println("as != null");
                //this.noeudASB[0] = as;
                this.noeudB[0] = as;
                this.changeEtat(etat + 1);
            } else {
                System.out.println("as = null");
                this.changeEtat(etat);
            }
            // boutton appui double pressé
        } else if (choice.get() == btnAD) {
            AppuiDouble ad = creationAD(clic);
            //condition pour savoir si le appui double à pu être crée
            if (ad != null) {
                // this.noeudADB[0] = ad;
                this.noeudB[0] = ad;
                this.changeEtat(etat + 1);
            } else {
                this.changeEtat(etat);
            }
            // boutton noeud déja existant pressé
        } else if (choice.get() == btoldn) {
            this.noeudB[0] = this.vue.getModel().plusProcheN(clic);
            this.changeEtat(etat + 1);
            // boutton cancel pressé
        } else {
            this.changeEtat(etat);
        }
    }

    public void typeBarre2(Point clic, TypeBarre type, Color color, int etat) {
        Treillis model = this.vue.getModel();
        Alert dBox = new Alert(AlertType.CONFIRMATION);
        dBox.setTitle("choix du type de noeud");
        dBox.setHeaderText("Ma barre !");
        dBox.setContentText("Choisissez le type de noeud débutant votre "
                + "barre ou si vous voulez créer votre barre à partir de "
                + "noeud déjà existant");
        ButtonType btnNS = new ButtonType("Noeud Simple");
        ButtonType btnAS = new ButtonType("Appui Simple");
        ButtonType btnAD = new ButtonType("Appui Double");
        ButtonType btoldn = new ButtonType("Noeud déjà existant");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dBox.getButtonTypes().setAll(btnNS, btnAS, btnAD, btoldn, btnCancel);
        Optional<ButtonType> choice = dBox.showAndWait();

        if (choice.get() == btnNS) {
            if (bonneLongeurB(clic, type) == false) {
                if (this.noeudB[0].getLiee() == null) {
                    if (this.noeudB[0] instanceof NoeudSimple) {
                        model.removeNoeudSimple((NoeudSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiSimple) {
                        model.removeAppuiSimple((AppuiSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiDouble) {
                        model.removeAppuiDouble((AppuiDouble) this.noeudB[0]);
                    } else {
                        System.out.println("instance of rien... pb");
                    }
                    //this.vue.getModel().removeNoeud(this.noeudB[0]);
                }
                this.changeEtat(etat);
            } else {
                if (this.vue.getModel().nZoneConstructible(clic) == true) {
                    this.noeudB[1] = new NoeudSimple(clic);
                    this.vue.getModel().addBarre(new Barre(this.noeudB[0], this.noeudB[1], color), type, color);
                    this.vue.getModel().addNoeudSimple((NoeudSimple) this.noeudB[1]);
                    this.changeEtat(etat - 1);
                } else {
                    Alert dialogW = new Alert(AlertType.WARNING);
                    dialogW.setTitle("A warning dialog-box");
                    dialogW.setHeaderText(null);  // No header
                    dialogW.setContentText("Caution : Vous ne pouvez pas dessinez votre"
                            + " treillis en dehors de la zone constructible "
                            + ". \nSelectionez une nouvelle position !");
                    dialogW.showAndWait();
                    this.changeEtat(etat);
                }

            }
//            TextInputDialog inDialog = new TextInputDialog("100");
//
//            inDialog.setTitle("Poids du Noeud Simple");
//            inDialog.setHeaderText("Veuillez entrer un poids pour le Noeud Simple ");
//            inDialog.setContentText("Poids : ");
//
//            Optional<String> textIn = inDialog.showAndWait();
//            if (textIn.isPresent()) {
//                this.noeudB[1].setForceY(Double.parseDouble(textIn.get()));
//            }

        } else if (choice.get() == btnAS) {
            AppuiSimple as = creationAS(clic);

            if (bonneLongeurB(as.getPosition(), type) == false) {
                this.vue.getModel().removeAppuiSimple(as);
                if (this.noeudB[0].getLiee() == null) {
                    //this.vue.getModel().removeNoeud(this.noeudB[0]);
                    if (this.noeudB[0] instanceof NoeudSimple) {
                        model.removeNoeudSimple((NoeudSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiSimple) {
                        model.removeAppuiSimple((AppuiSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiDouble) {
                        model.removeAppuiDouble((AppuiDouble) this.noeudB[0]);
                    } else {
                        System.out.println("instance of rien... pb");
                    }
                }
                this.changeEtat(etat);
            } else {
                //condition pour savoir si le appui simple à pu être crée
                if (as != null) {
                    System.out.println("as != null");
                    this.noeudASB[1] = as;
                    // model.addBarre(new Barre(this.noeudASB[0], this.noeudASB[1], 50, 70, 78, 99, 800));
                    model.addBarre(new Barre(this.noeudB[0], this.noeudASB[1], color), type, color);
                    this.changeEtat(etat - 1);
                } else {
                    System.out.println("as != null");
                    this.changeEtat(etat);
                }
            }
            // boutton appui double pressé
        } else if (choice.get() == btnAD) {
            AppuiDouble ad = creationAD(clic);
            System.out.println(ad.getappartient().getFaitPartieDe());
            if (bonneLongeurB(ad.getPosition(), type) == false) {
                this.vue.getModel().removeAppuiDouble(ad);
                if (this.noeudB[0].getLiee() == null) {
                    //this.vue.getModel().removeNoeud(this.noeudB[0]);
                    if (this.noeudB[0] instanceof NoeudSimple) {
                        model.removeNoeudSimple((NoeudSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiSimple) {
                        model.removeAppuiSimple((AppuiSimple) this.noeudB[0]);
                    } else if (this.noeudB[0] instanceof AppuiDouble) {
                        model.removeAppuiDouble((AppuiDouble) this.noeudB[0]);
                    } else {
                        System.out.println("instance of rien... pb");
                    }
                }
                this.changeEtat(etat);
            } else {
                //condition pour savoir si le appui double à pu être crée
                if (ad != null) {
                    this.noeudADB[1] = ad;
                    //model.addBarre(new Barre(this.noeudADB[0], this.noeudADB[1], 50, 70, 78, 99, 800));
                    model.addBarre(new Barre(this.noeudB[0], this.noeudADB[1], color), type, color);
                    this.changeEtat(etat - 1);
                } else {
                    this.changeEtat(etat);
                }
            }
            // boutton noeud déja existant pressé
        } else if (choice.get() == btoldn) {
            this.noeudB[1] = model.plusProcheN(clic);
            if (bonneLongeurB(this.noeudB[1].getPosition(), type) == false) {
                this.changeEtat(etat);
            } else {
                model.addBarre(new Barre(this.noeudB[0], this.noeudB[1], color), type, color);
                this.changeEtat(etat - 1);
            }
        } else {
            this.changeEtat(etat - 1);
        }
    }

    //La fin du codde concerne la barre menu situé en haut de l'interface
    private void realSave(File f) {
        try {
            this.vue.getModel().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        } finally {
            this.changeEtat(39);
        }
    }

    public void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }

    public void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }

    public void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                Treillis lue = Treillis.lecture(f);
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new interfaceDessin(lue, 39), 800, 600);
                nouveau.setScene(sc);
                nouveau.show();

            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } finally {
                this.changeEtat(39);
            }
        }
    }

    public void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        nouveau.setTitle("Nouveau");
        Scene sc = new Scene(new interfaceDessin(new Treillis()), 800, 600);
        nouveau.setScene(sc);
        nouveau.show();
    }

    public void menuApropos(ActionEvent t) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText(null);
        alert.setContentText("Ce logiciel a été créé par 3 élèves de l'INSA\n"
                + "qui ont passé BEAUCOUP DE TEMPS sur ce magnifique\n"
                + "logiciel que vous allez adorer, on en est sûr!\n"
                );

        alert.showAndWait();
    }

}
