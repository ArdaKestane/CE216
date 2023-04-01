package com.example.dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Translation {
    private final String sourceLanguage;
    private final String destinationLanguage;

    // Key -> word itself, Outside ArrayList stores different translations (1. 2. etc.), Inside ArrayList stores Synonyms ([..], [..] in 1.)
    private final HashMap<String, ArrayList<ArrayList<String>>> words;

    public Translation(String sourceLanguage, String destinationLanguage) {
        this.sourceLanguage = sourceLanguage;
        this.destinationLanguage = destinationLanguage;
        this.words = new HashMap<>();
    }

    // Add a new word into dictionary and initialize a new ArrayList for its translations
    public void addSourceWord(String word) {
        if (!words.containsKey(word)) {
            words.put(word, new ArrayList<>());
        }
    }

    // Add translation for exists word
    public void addTranslation(String word, String translation) {
        ArrayList<ArrayList<String>> translations = words.get(word);
        String[] synm = translation.split(", ");

        ArrayList<String> synonyms = new ArrayList<>();
        Collections.addAll(synonyms, synm);

        translations.add(synonyms);
    }

    public Collection<String> getAllWords() {
        return words.keySet();
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

