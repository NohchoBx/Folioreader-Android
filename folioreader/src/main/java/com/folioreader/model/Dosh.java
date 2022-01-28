package com.folioreader.model;

public class Dosh {

    String word;
    String word1;
    String translate;
    String translate1;
    boolean expanded;


    public Dosh(String word, String word1, String translate, String translate1) {
        this.word = word;
        this.word1 = word1;
        this.translate = translate;
        this.translate1 = translate1;
        this.expanded = false;
    }




    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranslate1() {
        return translate1;
    }

    public void setTranslate1(String translate1) {
        this.translate1 = translate1;
    }

    @Override
    public String toString() {
        return "Dosh{" +
                "word='" + word + '\'' +
                ", word1='" + word1 + '\'' +
                ", translate='" + translate + '\'' +
                ", translate1='" + translate1 + '\'' +
                '}';
    }
}
