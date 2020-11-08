package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Plato implements Serializable{
    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="description")
    private String description;
    @ColumnInfo(name="price")
    private Double price;
    @ColumnInfo(name="calories")
    private Integer calories;

    public Plato() {}

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
