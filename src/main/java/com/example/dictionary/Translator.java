package com.example.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class Translator {

    public static ArrayList<Translation> translations = getTranslations();

    private static void readFile(String fileName, Translation translation) {
        try {
            String word = "";
            URL url = Translator.class.getResource("/translations/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                try {
                    Integer.parseInt(Character.toString(line.charAt(0)));
                    translation.addTranslation(word, line.substring(3));
                } catch (NumberFormatException ex ) {
                    word = line.toLowerCase();
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

        Translation engEll = new Translation("English", "Greek");
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

        Translation ellEng = new Translation("Greek", "English");
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
                                } else if (tra.getSourceLanguage().equals(sourceLanguage) && tra.getDestinationLanguage().equals("Greek")) {
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
                                        if(tra2.getDestinationLanguage().equals(sourceLanguage))
                                            continue;
                                        if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("German")) {
                                            t.setDeu(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Turkish")) {
                                            t.setTur(tra2.getTranslations(engTra));
                                        } else if (tra2.getSourceLanguage().equals("English") && tra2.getDestinationLanguage().equals("Greek")) {
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

    public static ArrayList<Synonym> findSynonyms(String word) { //okul
        ArrayList<Translate> translations = translate(word); //okul'un çevirileri
        ArrayList<Synonym> synonyms = new ArrayList<>();
        for(Translate translation : translations) {
            String srcLan = translation.getSourceLanguage(); // türkçe
            String tempWord;
            if (srcLan.equals("English")) {
                tempWord = translation.getTur().get(0).get(0);
                ArrayList<Translate> selfTranslations = translate(tempWord); //school'un çevirileri
                ArrayList<ArrayList<String>> backTranslations;
                for (Translate st : selfTranslations) {
                    if(st.getSourceLanguage().equals("Turkish")) {
                        backTranslations = st.getEng() != null ? st.getEng()  : new ArrayList<>();
                        synonyms.add(new Synonym(srcLan, word));
                    }
                    else
                        backTranslations = new ArrayList<>();


                    for (ArrayList<String> bt : backTranslations) {
                        for (String w : bt) {
                            if (w.equals(word)) {
                                Synonym s = synonyms.get(synonyms.size() - 1);
                                s.setSynonyms(bt);
                            }
                        }
                    }
                }
            } else {
                tempWord = translation.getEng().get(0).get(0); //school
                ArrayList<Translate> selfTranslations = translate(tempWord); //school'un çevirileri
                for (Translate st : selfTranslations) {
                    ArrayList<ArrayList<String>> backTranslations;
                    synonyms.add(new Synonym(srcLan, word));
                    switch (srcLan) {
                        case "Turkish" -> backTranslations = st.getTur();
                        case "Greek" -> backTranslations = st.getEll();
                        case "French" -> backTranslations = st.getFra();
                        case "Italian" -> backTranslations = st.getIta();
                        case "Swedish" -> backTranslations = st.getSwe();
                        case "German" -> backTranslations = st.getDeu();
                        default -> {
                            backTranslations = new ArrayList<>();
                            synonyms.remove(synonyms.size() - 1);
                        }
                    }

                    for (ArrayList<String> bt : backTranslations) {
                        for (String w : bt) {
                            if (w.equals(word)) {
                                Synonym s = synonyms.get(synonyms.size() - 1);
                                s.setSynonyms(bt);
                            }


                        }
                    }
                }
            }
        }
        return synonyms;
    }

}

