package com.example.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Translation {
    private final String sourceLanguage;
    private final String destinationLanguage;
    private final HashMap<String, ArrayList<ArrayList<String>>> words;

    public Translation(String sourceLanguage, String destinationLanguage) {
        this.sourceLanguage = sourceLanguage;
        this.destinationLanguage = destinationLanguage;
        this.words = new HashMap<>();
    }

    public void addSourceWord(String word) {
        words.put(word, new ArrayList<>());
    }

    public void addTranslation(String word, String translation) {
        ArrayList<ArrayList<String>> translations = words.get(word);
        String[] synm = translation.split(", ");

        ArrayList<String> synonyms = new ArrayList<>();
        translations.add(synonyms);

        Collections.addAll(synonyms, synm);
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getDestinationLanguage() {
        return destinationLanguage;
    }
}

