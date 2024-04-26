package com.example.demo.entity;

import java.util.List;

public class Question {
    private int id;
    private String description;
    private List<Choix> choix;

    public Question() {
    }

    public Question(int id, String description, List<Choix> choix) {
        this.id = id;
        this.description = description;
        this.choix = choix;
    }

    public Question(int id, String description) {
        this.id = id;
        this.description=description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Choix> getChoix() {
        return choix;
    }

    public void setChoix(List<Choix> choix) {
        this.choix = choix;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", choix=" + choix +
                '}';
    }
}