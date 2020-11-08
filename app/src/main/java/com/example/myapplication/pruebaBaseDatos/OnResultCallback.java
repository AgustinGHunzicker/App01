package com.example.myapplication.pruebaBaseDatos;

import com.example.myapplication.model.Plato;

import java.util.List;

public interface OnResultCallback<T> {
    void onResult(List<T> result);
}