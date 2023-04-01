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

    public void appendEng(ArrayList<ArrayList<String>> eng) {
        this.eng.addAll(eng);
    }

    public void appendDeu(ArrayList<ArrayList<String>> deu) {
        if(this.deu != null)
            this.deu.addAll(deu);
    }

    public void appendEll(ArrayList<ArrayList<String>> ell) {
        if(this.ell != null)
            this.ell.addAll(ell);
    }

    public void appendFra(ArrayList<ArrayList<String>> fra) {
        if(this.fra != null)
            this.fra.addAll(fra);
    }

    public void appendSwe(ArrayList<ArrayList<String>> swe) {
        if(this.swe != null)
            this.swe.addAll(swe);
    }

    public void appendIta(ArrayList<ArrayList<String>> ita) {
        if(this.ita != null)
            this.ita.addAll(ita);
    }

    public void appendTur(ArrayList<ArrayList<String>> tur) {
        if(this.tur != null)
            this.tur.addAll(tur);
    }
}
