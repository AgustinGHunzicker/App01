package com.example.myapplication.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Plato implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Integer calories;

    public Plato() {}

    public Plato(Integer id, String title, String description, Double price, Integer calories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return title;
    }
}
