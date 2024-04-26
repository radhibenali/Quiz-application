package com.example.demo.entity;

public class Choix {
    private int id;
    private String description;
    private String reponse_q;

    public Choix() {
    }

    public Choix(int id, String description, String reponse_q) {
        this.id = id;
        this.description = description;
        this.reponse_q = reponse_q;
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

    public String getReponse_q() {
        return reponse_q;
    }

    public void setReponse_q(String reponse_q) {
        this.reponse_q = reponse_q;
    }

    @Override
    public String toString() {
        return "Choix{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", reponse_q='" + reponse_q + '\'' +
                '}';
    }
}