package com.rangelstoilov.marketing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/layouts/Main.fxml"));
        primaryStage.setMinWidth(360);
        primaryStage.setMinHeight(830);



        Image image = new Image("app/resources/images/icon.png");
        primaryStage.getIcons().add(image);

//        primaryStage.setMaxWidth(355);
        primaryStage.setTitle("Amazon Marketing Suite v1.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }




    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
