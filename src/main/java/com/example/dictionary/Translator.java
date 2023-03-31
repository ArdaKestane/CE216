package com.example.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Translator {

    public static ArrayList<Translation> translations = getTranslations();

    private static void readFile(String fileName, Translation translation) {
        try {
            String word = "";
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/translations/" + fileName));
            String line = reader.readLine();
            while (line != null) {
                try {
                    Integer.parseInt(Character.toString(line.charAt(0)));
                    translation.addTranslation(word, line.substring(3));
                } catch (NumberFormatException ex ) {
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
    private static ArrayList<Translation> getTranslations() {
        ArrayList<Translation> translations = new ArrayList<>();

        Translation engTur = new Translation("English", "Turkish");
        translations.add(engTur);
        readFile("eng-tur.txt", engTur);

        Translation engEll = new Translation("English", "Modern Greek");
        translations.add(engEll);
        readFile("eng-ell.txt", engEll);

        Translation engFra = new Translation("English", "French");
        translations.add(engFra);
        readFile("eng-fra.txt", engFra);

        Translation engIta = new Translation("English", "Italian");
        translations.add(engIta);
        readFile("eng-ita.txt", engIta);

        Translation engSwe = new Translation("English", "Swedish");
        translations.add(engSwe);
        readFile("eng-swe.txt", engSwe);

        Translation engDeu = new Translation("English", "German");
        translations.add(engDeu);
        readFile("eng-deu.txt", engDeu);

        Translation ellEng = new Translation("Modern Greek", "English");
        translations.add(ellEng);
        readFile("ell-eng.txt", ellEng);

        Translation fraEng = new Translation("French", "English");
        translations.add(fraEng);
        readFile("fra-eng.txt", fraEng);

        Translation itaEng = new Translation("Italian", "English");
        translations.add(itaEng);
        readFile("ita-eng.txt", itaEng);

        Translation sweEng = new Translation("Swedish", "English");
        translations.add(sweEng);
        readFile("swe-eng.txt", sweEng);

        Translation turEng = new Translation("Turkish", "English");
        translations.add(turEng);
        readFile("tur-eng.txt", turEng);

        Translation deuEng = new Translation("German", "English");
        translations.add(deuEng);
        readFile("deu-eng.txt", deuEng);


        return translations;
    }

    public static ArrayList<Translate> translate(String word) {
        ArrayList<Translate> translate = new ArrayList<>();

        for(Translation translation : translations) {
            Collection<String> sourceWords = translation.getAllWords();
            for (String w : sourceWords) {
                if(w.equals(word)) {
                    // Bu bulduğum kelime hangi dilde?
                    String sourceLanguage = translation.getSourceLanguage();

                    // Diğer 6 dile çevir -> Eğer çevirdiğin dil ingilizce içermiyorsa, sourceLanguage -> English -> destinationLanguage şeklinde çevir

                    // Translate objesinin içine at
                    // translate ArrayList'inin içine ekle
                }

            }
        }

        return translate;
    }

}

