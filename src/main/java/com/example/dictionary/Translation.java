package com.example.dictionary;

import java.util.ArrayList;
import java.util.Collection;
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

    public Collection<String> getAllWords() {
        return (Collection<String>) words.keySet();
    }

    public ArrayList<ArrayList<String>> getTranslations(String word) {
        return words.get(word);
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getDestinationLanguage() {
        return destinationLanguage;
    }
}

