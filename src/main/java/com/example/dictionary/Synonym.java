package com.example.dictionary;

import java.util.ArrayList;

public class Synonym {
    private final String sourceLanguage;
    private final String word;

    private ArrayList<String> synonyms;

    public Synonym(String sourceLanguage, String word) {
        this.sourceLanguage = sourceLanguage;
        this.word = word;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }
}
