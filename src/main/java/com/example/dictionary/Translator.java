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

    private void setTranslate(String language) {

    }

    public static ArrayList<Translate> translate(String word) {
        ArrayList<Translate> translate = new ArrayList<>();
        boolean isEnglishFound = false;

        for(Translation translation : translations) {
            Collection<String> sourceWords = translation.getAllWords();
            for (String w : sourceWords) {
                if(w.equals(word)) {
                    String sourceLanguage = translation.getSourceLanguage();
                    if(isEnglishFound && sourceLanguage.equals("English"))
                        continue;
                    Translate t = new Translate(sourceLanguage, w);
                        if (sourceLanguage.equals("English")) {
                            for (Translation tra : translations) {
                                if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("German")) {
                                    t.setDeu(tra.getTranslations(w));
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("Turkish")) {
                                    t.setTur(tra.getTranslations(w));
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("Modern Greek")) {
                                    t.setEll(tra.getTranslations(w));
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("French")) {
                                    t.setFra(tra.getTranslations(w));
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("Italian")) {
                                    t.setIta(tra.getTranslations(w));
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("Swedish")) {
                                    t.setSwe(tra.getTranslations(w));
                                }
                            }

                            isEnglishFound = true;
                        } else {
                            for (Translation tra : translations) {
                                if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("English")) {
                                    t.setEng(tra.getTranslations(w));
                                    String engTra = tra.getTranslations(w).get(0).get(0);
                                    for (Translation tra2 : translations) {
                                        if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("German")) {
                                            t.setDeu(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Turkish")) {
                                            t.setTur(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Modern Greek")) {
                                            t.setEll(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("French")) {
                                            t.setFra(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Italian")) {
                                            t.setIta(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Swedish")) {
                                            t.setSwe(tra2.getTranslations(engTra));
                                        }
                                    }
                                }
                            }
                        }
                        translate.add(t);
                }

            }
        }

        return translate;
    }

}

