package com.rangelstoilov.marketing.controllers;

import com.rangelstoilov.marketing.entities.Persona;
import com.rangelstoilov.marketing.exceptions.SMSPVA.ExpensiveNumbersException;
import com.rangelstoilov.marketing.exceptions.SMSPVA.InsufficientBalanceException;
import com.rangelstoilov.marketing.submitter.WebsiteSubmitterThread;
import com.jfoenix.controls.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class PersonasController implements Initializable {

    WebsiteSubmitterThread ws = new WebsiteSubmitterThread();

    @FXML
    private JFXCheckBox vpn;

    @FXML
    private GridPane timezone;

    @FXML
    private ImageView profilePic;


    //Input  vv -----------------------------

    @FXML
    private JFXTextField first_name;

    @FXML
    private JFXTextField last_name;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXDatePicker birthday;

    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXComboBox<String> country;

    //Buttons vv -----------------------------
    @FXML
    private JFXButton btn_submit;


    @FXML
    private JFXButton btn_choose;

    @FXML
    private JFXButton btn_generate;


    @FXML
    private JFXComboBox<String> timezone_select;

    @FXML
    private JFXProgressBar progress;


    @FXML
    private Label message;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setAllTimezonesToComboBox();
        //imports all Countries to the combo box and trims the long names
        setAllCountriesToComboBox();

        //add the genders of the comboBox
        gender.getItems().addAll("Male", "Female");

        //Handles all events on mouse clicks and actions
        eventHandler();

        //Makes the profile image a circle
        Circle shape = new Circle(75, 75, 75);
        profilePic.setClip(shape);
    }

    //Handles all events on mouse clicks and actions
    private void eventHandler() {
        btn_choose.setOnMouseClicked(event -> System.out.println("You clicked choose profile picture"));
        btn_generate.setOnMouseClicked(event -> {
            first_name.setText("Rangel");
            last_name.setText("Stoilov");
            username.setText("rororew32");
            password.setText("dqpkn65RS@!");
            birthday.setValue(LocalDate.of(1991, 8, 26));
            gender.setValue("Male");
            country.setValue("United Kingdom");
        });
        vpn.setOnMouseClicked(event -> {
            if (!timezone.isVisible()) {
                timezone.setVisible(true);
            } else {
                timezone.setVisible(false);
            }
        });
        btn_submit.setOnMouseClicked((MouseEvent event) -> {
            Persona persona = new Persona(
                    first_name.getText(),
                    last_name.getText(),
                    username.getText(),
                    password.getText(),
                    birthday.getValue(),
                    gender.getValue(),
                    country.getValue());

            if (checkInputErrors()) {
                progress.setVisible(true);


                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        //Gets the main stage
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        //gets all the bounds
                        Rectangle2D bounds = Screen.getPrimary().getBounds();
                        System.out.println(bounds);
                        //resize  the stage
                        appStage.setX(bounds.getWidth() * 0.8 + 7);
                        appStage.setY(bounds.getMinY());
                        appStage.setWidth(bounds.getWidth() * 0.2);
                        appStage.setHeight(bounds.getHeight() - 33);
                        //disable the submit button
                        btn_submit.setDisable(true);
                        try {
                            //handle progress
                            updateProgress(100, 1000);
                            updateMessage("Starting work...");
                            //TODO:Break down to tasks per domain
                            //TODO: make this to a constructor and call methods from it
                            new WebsiteSubmitterThread().invokeSession(persona);
                            updateMessage("Done");
                        } catch (InsufficientBalanceException | ExpensiveNumbersException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                };

                task.setOnSucceeded(e -> {
                    boolean result = task.getValue();
                    // updates after we have finished
                    if (result) {
                        btn_submit.setDisable(false);
                        progress.setVisible(false);
                    }
                });
                progress.progressProperty().bind(task.progressProperty());
                message.textProperty().bind(task.messageProperty());

                new Thread(task).start();
            }
        });

    }

    //Checks if the input is valid and if not it prints an error
    private boolean checkInputErrors() {
        boolean noError = true;
        if (first_name.getText().equals("")) {
            first_name.setPromptText("This field is mandatory");
            first_name.setStyle("-fx-prompt-text-fill: red;");
            noError = false;
        }

        if (last_name.getText().equals("")) {
            last_name.setPromptText("This field is mandatory");
            last_name.setStyle("-fx-prompt-text-fill: red;");
            noError = false;
        }

        if (username.getText().length() <= 6 || username.getText().length() >= 20) {
            username.setPromptText("Please use between 6 and 30 characters.");
            username.setStyle("-fx-prompt-text-fill: red;");
            username.clear();
            noError = false;
        }

        if (password.getText().length() <= 8) {
            password.setPromptText("Password needs to be at least 8 characters.");
            password.setStyle("-fx-prompt-text-fill: red;");
            password.clear();
            noError = false;
        }

        if (birthday.getValue() == null) {
            birthday.setPromptText("Please choose a birthday");
            birthday.setStyle("-fx-prompt-text-fill: red;");
            noError = false;
        }

        if (gender.getValue() == null) {
            gender.setPromptText("Please set a gender");
            gender.setStyle("-fx-prompt-text-fill: red;");
            noError = false;
        }

        if (country.getValue() == null) {
            country.setPromptText("Please choose a country");
            country.setStyle("-fx-prompt-text-fill: red;");
            noError = false;
        }

//            if (password.getText().length()<=8){
//                password.setPromptText("Password needs to be at least 8 characters.");
//                password.setStyle("-fx-prompt-text-fill: red;");
//                password.clear();
//            }
//
//            if (password.getText().length()<=8){
//                password.setPromptText("Password needs to be at least 8 characters.");
//                password.setStyle("-fx-prompt-text-fill: red;");
//                password.clear();
//            }
        return noError;
    }

    //imports all Countries to the combo box and trims the long names
    private void setAllCountriesToComboBox() {
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            String countryName = obj.getDisplayCountry();
            if (countryName.length() >= 35) {
                String substring = countryName.substring(0, 28);
                countryName = substring;
            }
            country.getItems().add(countryName);
        }
    }

    //TODO:https://github.com/JodaOrg/joda-time/blob/master/src/example/org/joda/example/time/TimeZoneTable.java
    //imports all timezones to the combo box and trims the long names
    private void setAllTimezonesToComboBox() {
        String[] timezones = TimeZone.getAvailableIDs();
        for (String timezone : timezones) {
            timezone_select.getItems().add(timezone);
        }
    }
}
