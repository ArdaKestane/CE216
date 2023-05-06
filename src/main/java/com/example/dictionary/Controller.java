package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;


import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private VBox landingPage;
    @FXML
    private VBox resultPage;

    @FXML
    private TextField searchArea ;
    @FXML
    private TextField searchField ;
    @FXML
    private TextField editSearchField ;
    @FXML
    private Button translationsButton;
    @FXML
    private Button synonymsButton;
    @FXML
    private ListView<String> wordList ;
    @FXML
    private ChoiceBox<String> sourceBox ;
    @FXML
    private ChoiceBox<String> editSourceBox ;
    @FXML
    private ChoiceBox<String> addSourceBox ;
    @FXML
    private ChoiceBox<String> dstBox ;
    @FXML
    private ChoiceBox<String> editDstBox ;
    @FXML
    private ChoiceBox<String> addDstBox ;
    
    @FXML
    private VBox mainVBox;
    @FXML
    private VBox addModal;
    @FXML
    private VBox editModal;
    @FXML
    private VBox editResult;
    @FXML
    private VBox helpSection;
    @FXML
    private VBox helpPage1;
    @FXML
    private VBox helpPage2;

   

    @FXML
    private TextField addWord, newTranslation, newSynonym;

    
    private boolean initially = true; // if the program is opened for the first time

    /***
    // Problem 1: Character set problem occurs when non-english characters are entered
    // Problem 2: ListView changes base color when data is added
    // Problem 3: ChoiceBox
    // Problem 4: GetSynonyms and GetTranslations buttons
     // Problem 5: Landing page textArea has border color when it is not in the focus mode
     ***/


    public void initialize() {
        wordList.setMouseTransparent( true );
        wordList.setFocusTraversable( false );
    }

    public void home(){
        landingPage.setVisible(true);
        resultPage.setVisible(false);
        searchArea.setText("");
    }

    public void searchFromLanding(){
        String searchedWord = searchArea.getText().trim().toLowerCase();
        if(searchedWord.equals(""))
            return ;
        searchField.setText(searchedWord);
        showTranslations(searchedWord);

        landingPage.setVisible(false);
        resultPage.setVisible(true);

    ArrayList<Translate> translationList = Translator.translate(searchedWord);
    sourceBox.getItems().clear();
        for(Translate translate : translationList){
            sourceBox.getItems().add(translate.getSourceLanguage().toString());
        }

        if(!sourceBox.getItems().isEmpty()){
            sourceBox.getSelectionModel().select(0);
        }
        else{
            System.out.println("The word is not available in the dictionary.");
        }
    }

    public void searchFromResult(){
        String searchedWord = searchField.getText().trim().toLowerCase();
        if(searchedWord.equals(""))
            return ;
        searchField.setText(searchedWord);
        showTranslations(searchedWord);

        ArrayList<Translate> translationList = Translator.translate(searchedWord);
        sourceBox.getItems().clear();
        for(Translate translate : translationList){
            sourceBox.getItems().add(translate.getSourceLanguage().toString());
        }

        if(!sourceBox.getItems().isEmpty()){
            sourceBox.getSelectionModel().select(0);
        }
        else{
            System.out.println("The word is not available in the dictionary.");
        }

    }

    public void showSynonyms(){
        wordList.getItems().removeAll();


    }
    public int showTranslations(String word){
        wordList.getItems().clear();
        ArrayList<Translate> translationList = Translator.translate(word);
        
        ArrayList<ArrayList<String>> temp = null;
        String dstLng = dstBox.getValue() == null ? "English" : dstBox.getValue() ;
        String srcLng = sourceBox.getValue() == null ? "Turkish" : sourceBox.getValue() ;
        Translate t  = null;
        if(translationList.size()==0){
            System.out.println("TranslationList is empty");
            return 0; // there is no translate
        } else if (initially) {
            t = translationList.get(0);
            initially = false;
        }
        else {
            for (Translate translate : translationList ) {
                if(translate.getSourceLanguage().equals(srcLng)){
                    t = translate ;
                }
            }
        }
        if(t == null){
            System.out.println("There is no Translate");
            return 0;
        }
        if(initially){
            temp = t.getTur(); // if the program is opened for the first time , show the translations in turkish by default
            initially = false;
        }
        else if(dstLng.equals("Turkish")){
            temp = t.getTur();
        }
        else if(dstLng.equals("English")){
            temp = t.getEng();
        }
        else if(dstLng.equals("German")){
            temp = t.getDeu();
        }
        else if(dstLng.equals("Greek")){
            temp = t.getEll();
        }
        else if(dstLng.equals("French")){
            temp = t.getFra();
        }
        else if(dstLng.equals("Italian")){
            temp = t.getIta();
        }
        else if(dstLng.equals("Swedish")){
            temp = t.getSwe();
        }
        if(temp == null)
            return 0;

        if(temp.size()==0)
            wordList.getItems().add("There is no translate");
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
            System.out.println(line);
            wordList.getItems().add(line);
        }
        sourceBox.setValue(t.getSourceLanguage());
        dstBox.setValue(dstLng);

        return 1 ;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSourceBox.getItems().addAll("Turkish","English","German","Greek","French","Italian","Swedish");
        addDstBox.getItems().addAll("Turkish","English","German","Greek","French","Italian","Swedish");
        editSourceBox.getItems().addAll("Turkish","English","German","Greek","French","Italian","Swedish");
        editDstBox.getItems().addAll("Turkish","English","German","Greek","French","Italian","Swedish");
        dstBox.getItems().addAll("Turkish","English","German","Greek","French","Italian","Swedish");
        sourceBox.setValue("Turkish");
        dstBox.setValue("English");
        searchField.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER")){
                searchFromResult();
            }
        });
        searchArea.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER")){
                searchFromLanding();
            }
        });
        translationsButton.setOnAction(event -> showTranslations(searchField.getText()));
        synonymsButton.setOnAction(event -> showSynonyms());
        dstBox.setOnAction(event -> showTranslations(searchField.getText()));
        sourceBox.setOnAction(event -> showTranslations(searchField.getText()));
        wordList.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                String selectedWord = wordList.getSelectionModel().getSelectedItem();
                searchField.setText(selectedWord);
                showTranslations(selectedWord);
            }
        });

    }


    public void openAddModal(){
        addModal.setVisible(true);

        BoxBlur blur = new BoxBlur();
        blur.setWidth(10);
        blur.setHeight(10);
        blur.setIterations(3);

        landingPage.setEffect(blur);
    }

    public void closeAddModal(){
        addModal.setVisible(false);
        landingPage.setEffect(null);

        addWord.clear();
        newTranslation.clear();
        newSynonym.clear();
    }

    public void openEditModal(){
        editModal.setVisible(true);

        BoxBlur blur = new BoxBlur();
        blur.setWidth(10);
        blur.setHeight(10);
        blur.setIterations(3);

        landingPage.setEffect(blur);
    }

    public void closeEditModal(){
        editModal.setVisible(false);
        landingPage.setEffect(null);

        /* addWord.clear();
        newTranslation.clear();
        newSynonym.clear();
        */
    }
    public void openHelp(){
        helpSection.setVisible(true);

        BoxBlur blur = new BoxBlur();
        blur.setWidth(10);
        blur.setHeight(10);
        blur.setIterations(3);

        landingPage.setEffect(blur);

    }

    public void closeHelp(){
        helpSection.setVisible(false);
        landingPage.setEffect(null);
    }
    public void nextHelp(){
        helpPage1.setVisible(false);
        helpPage2.setVisible(true);
    }
    public void prevHelp(){
        helpPage1.setVisible(true);
        helpPage2.setVisible(false);
    }

    





    public void showEditResults(){
        String[] results = {"a", "1. ingiliz alfabesinin ilk harfi", "2. birinci kalite veya derece", "(müz.) la notası, la perdesi", "(A.B.D.) en yüksek not."};
        for (String result : results) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            HBox.setMargin(hbox, new Insets(0, 0, 10, 0));
            Label label = new Label(result);
            Button button = new Button();
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/trash.png")));
            button.setGraphic(imageView);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

            VBox vbox = new VBox();
            Separator separator = new Separator();
            separator.setPrefWidth(50);
            separator.setStyle("-fx-background-color: black;");
            vbox.getChildren().addAll(hbox,separator);
            VBox.setMargin(separator, new Insets(10, 0, 10, 0));

            hbox.getChildren().addAll(label, button);
            editResult.getChildren().add(vbox);
        }

        
    }
}