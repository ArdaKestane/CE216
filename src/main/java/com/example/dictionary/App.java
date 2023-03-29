package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setMinWidth(1500);
        stage.setMinHeight(800);
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*
        SAMPLE USAGE OF CLASSES
        */
        ArrayList<Translation> translations = Translator.getTranslations();
        for(String word : translations.get(9).getAllWords())
            for (ArrayList<String> translation : translations.get(9).getTranslations(word))
                for (String synonym : translation)
                    System.out.println(synonym);

        launch();
    }
}