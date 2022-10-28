package com.pawel.threadtask.enums;

public class InputData {

    private int min;
    private int max;
    private int howMuchStrings;
    private String characters;

    public InputData() {
    }

    public InputData(int min, int max, int howMuchStrings, String characters) {
        this.min = min;
        this.max = max;
        this.howMuchStrings = howMuchStrings;
        this.characters = characters;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getHowMuchStrings() {
        return howMuchStrings;
    }

    public void setHowMuchStrings(int howMuchStrings) {
        this.howMuchStrings = howMuchStrings;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
