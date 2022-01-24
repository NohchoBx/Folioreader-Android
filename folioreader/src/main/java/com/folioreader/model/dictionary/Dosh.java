package com.folioreader.model.dictionary;


public class Dosh {

    private String id;
    private String word;
    private String translate;


    @Override
    public String toString() {
        return "Dosh{" + "id='" + id + '\'' + "word='" + word + '\''  + "translate='" + translate + '\''+
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
