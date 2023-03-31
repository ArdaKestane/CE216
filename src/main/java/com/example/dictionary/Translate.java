package com.example.dictionary;

import java.util.ArrayList;

public class Translate {
    private String sourceLanguage;
    private String sourceWord;

    private ArrayList<ArrayList<String>> eng;
    private ArrayList<ArrayList<String>> deu;
    private ArrayList<ArrayList<String>> ell;
    private ArrayList<ArrayList<String>> fra;
    private ArrayList<ArrayList<String>> swe;
    private ArrayList<ArrayList<String>> ita;
    private ArrayList<ArrayList<String>> tur;

    public Translate(String sourceLanguage, String sourceWord) {
        this.sourceLanguage = sourceLanguage;
        this.sourceWord = sourceWord;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setEng(ArrayList<ArrayList<String>> eng) {
        this.eng = eng;
    }

    public void setDeu(ArrayList<ArrayList<String>> deu) {
        this.deu = deu;
    }

    public void setEll(ArrayList<ArrayList<String>> ell) {
        this.ell = ell;
    }

    public void setFra(ArrayList<ArrayList<String>> fra) {
        this.fra = fra;
    }

    public void setSwe(ArrayList<ArrayList<String>> swe) {
        this.swe = swe;
    }

    public void setIta(ArrayList<ArrayList<String>> ita) {
        this.ita = ita;
    }

    public void setTur(ArrayList<ArrayList<String>> tur) {
        this.tur = tur;
    }
}
