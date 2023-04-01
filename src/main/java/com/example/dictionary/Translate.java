package com.example.dictionary;

import java.util.ArrayList;

public class Translate {
    private final String sourceLanguage;
    private final String sourceWord;

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

    public ArrayList<ArrayList<String>> getEng() {
        return eng;
    }

    public ArrayList<ArrayList<String>> getDeu() {
        return deu;
    }

    public ArrayList<ArrayList<String>> getEll() {
        return ell;
    }

    public ArrayList<ArrayList<String>> getFra() {
        return fra;
    }

    public ArrayList<ArrayList<String>> getSwe() {
        return swe;
    }

    public ArrayList<ArrayList<String>> getIta() {
        return ita;
    }

    public ArrayList<ArrayList<String>> getTur() {
        return tur;
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
