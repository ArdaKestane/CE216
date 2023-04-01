package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private VBox landingPage;
    @FXML
    private VBox resultPage;
    @FXML
    private TextArea searchArea ;
    @FXML
    private TextField searchField ;
    @FXML
    private Button translationsButton;
    @FXML
    private Button synonymsButton;
    @FXML
    private ListView<String> wordList ;
    @FXML
    private ChoiceBox<String> sourceBox ;
    @FXML
    private ChoiceBox<String> dstBox ;

    private boolean isTOpened = false; // if the translation is opened
    private boolean isSOpened = false; // if the synonyms is opened
    private boolean initially = true; // if the program is opened for the first time

    /***
    // Problem 1: Character set problem occurs when non-english characters are entered
    // Problem 2: ListView changes base color when data is added
    // Problem 3: ChoiceBox
    // Problem 4: GetSynonyms and GetTranslations buttons
     // Problem 5: Landing page textArea has border color when it is not in the focus mode
     ***/


    public void initialize() {

    }

    public void home(){
        landingPage.setVisible(true);
        resultPage.setVisible(false);
    }

    public void searchFromLanding(){
        String searchedWord = searchArea.getText();
        if(searchedWord.equals(""))
            return ;
        searchField.setText(searchedWord);
        showTranslations(searchedWord , dstBox.getValue());
        landingPage.setVisible(false);
        resultPage.setVisible(true);
    }
    public void searchFromResult(){
        String searchedWord = searchField.getText();
        if(searchedWord.equals(""))
            return ;

        searchField.setText(searchedWord);
        showTranslations(searchedWord , dstBox.getValue());


    }

    public void showSynonyms(){
        wordList.getItems().removeAll();


    }
    public void showTranslations(String word , String dstLng){
        if(word.equals("")) // if the word is empty , do nothing
            return ;
        wordList.getItems().clear();
        ArrayList<Translate> translationList = Translator.translate(word);
        ArrayList<ArrayList<String>> temp = null;
        for (Translate t : translationList) {
            if(initially){
                temp = t.getTur(); // if the program is opened for the first time , show the translations in turkish by default
                initially = false;
            }
            else if(dstLng.equals("tr")){
                temp = t.getTur();
            }
            else if(dstLng.equals("en")){
                temp = t.getEng();
            }
            else if(dstLng.equals("de")){
                temp = t.getDeu();
            }
            else if(dstLng.equals("fr")){
                temp = t.getFra();
            }
            else if(dstLng.equals("fra")){
                temp = t.getFra();
            }
            else if(dstLng.equals("ita")){
                temp = t.getIta();
            }
            else if(dstLng.equals("swe")){
                temp = t.getSwe();
            }
            if(temp == null)
                return ;

            for (ArrayList<String> arr : temp) {
                StringBuilder tempS = new StringBuilder();
                for (int i =0 ; i<arr.size() ; i++)
                {
                    String w= arr.get(i);
                    if(i!=arr.size()-1)
                        tempS.append(w).append(",");
                    else
                        tempS.append(w);
                }
                String line = tempS.toString() ;
                wordList.getItems().add(line);

            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sourceBox.getItems().addAll("tr","en","de","fr","fra","ita","swe");
        dstBox.getItems().addAll("tr","en","de","fr","fra","ita","swe");
        sourceBox.setValue("en");
        dstBox.setValue("tr");
        searchField.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER")){
                searchFromResult();
            }
        });
        translationsButton.setOnAction(event -> {
            showTranslations(searchField.getText() , dstBox.getValue());
        });
        synonymsButton.setOnAction(event -> {
            showSynonyms();
        });
    }



}