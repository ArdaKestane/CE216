package com.example.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Translator {

    private static void readFile(String fileName, Translation translation) {
        try {
            String word = "";
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/translations/" + fileName));
            String line = reader.readLine();
            while (line != null) {
                try {
                    Integer.parseInt(Character.toString(line.charAt(0)));
                    translation.addTranslation(word, line.substring(2));
                } catch(NumberFormatException ex ) {
                    word = line;
                    translation.addSourceWord(word);
                } catch (StringIndexOutOfBoundsException s) {
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Translation> getTranslations() {
        ArrayList<Translation> translations = new ArrayList<>();

        Translation engTur = new Translation("English", "Turkish");
        translations.add(engTur);
        readFile("eng-tur.txt", engTur);



        return translations;
    }

}

