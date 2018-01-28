package com.rangelstoilov.marketing.controllers;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private ImageView btn_menu;

    @FXML
    private AnchorPane root_pane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label menuTitle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane("Personas");
        // This code is responsible of switching he panes from the menu
        // Redeemer that the FXML file name should be the same as the AccessibleText
        // Also sets the title of the pane to the specific section you are in
        try {
            VBox box = FXMLLoader.load(getClass().getResource("../resources/layouts/SideMenu.fxml"));
            drawer.setSidePane(box);
            for (Node node : box.getChildren()) {
                System.out.println(node.getAccessibleText());
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        switch (node.getAccessibleText()) {
                            case "Personas":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                            case "Giveaway":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                            case "Facebook":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                            case "Statistics":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                            case "Settings":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                            case "Help":
                                switchPane(node.getAccessibleText());
                                menuTitle.setText(node.getAccessibleText());
                                break;
                        }
                    });
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btn_menu.setOnMouseClicked(event -> {
            if (!drawer.isShown()) {
                drawer.open();
                drawer.setMouseTransparent(false);
            }
        });

        root.setOnMouseClicked(event1 -> {
            if (drawer.isShown()) {
                drawer.close();
                drawer.setMouseTransparent(true);
            }
        });
    }

    private AnchorPane switchPane(String nameOfFXML) {
        try {
            //For debugging click
            System.out.println("Click!");

            AnchorPane pane = FXMLLoader.load(getClass().getResource("../resources/layouts/" + nameOfFXML + ".fxml"));
            //Strech the pane so it resizes
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            //set the pane to the inside panel
            root_pane.getChildren().setAll(pane);
            //Close drawer if open
            drawer.close();
            drawer.setMouseTransparent(true);
            return pane;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
