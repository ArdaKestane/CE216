package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    private VBox landingPage;

    @FXML
    private VBox resultPage;

    public void initialize() {
        
    }

    public void home(){
        landingPage.setVisible(true);
        resultPage.setVisible(false);
    }

    public void search(){
        landingPage.setVisible(false);
        resultPage.setVisible(true);
    }


}