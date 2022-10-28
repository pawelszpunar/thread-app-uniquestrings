package com.pawel.threadtask.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "my_thread")
public class MyThread implements Runnable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)

    private Long id;

    @JsonIgnore
    private int min;

    @JsonIgnore
    private int max;

    @JsonIgnore
    private int howMuchStrings;

    @JsonIgnore
    private String characters;
    private String status;

    @JsonIgnore
    @Column(columnDefinition="TEXT")
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void run() {
        this.status = "running";
        List<String> resultList = generateAllLenghts(this.characters, this.min, this.max, this.howMuchStrings);
        this.result = String.join(",", resultList);
        this.status = "ended";
    }

    public MyThread() {
    }

    public MyThread(int min, int max, int howMuchStrings, String characters) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static List<String> generateAllLenghts(String characters, int min, int max, int howMuchStrings) {
        List<String> result = new ArrayList<>();
        int n = characters.length();
        for(int k = min; k <= max; k++) {
            generateRec(characters.toCharArray(), "", n, min, max, k, result, howMuchStrings);
        }
        return result;
    }

    static List<String> generateRec(char[] set, String prefix, int n, int min, int max, int k, List<String> list, int howMuch) {
        if (k == 0)
        {
            if(prefix.length() >= min && prefix.length() <= max && list.size() < howMuch) {
                list.add(prefix);
            }
            return list;
        }

        if(list.size() < howMuch) {
            for (int i = 0; i < n; ++i)
            {
                String newPrefix = prefix + set[i];
                generateRec(set, newPrefix, n, min, max, k - 1, list, howMuch);
            }
        }
        return list;
    }
}
