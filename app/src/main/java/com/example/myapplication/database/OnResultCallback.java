package com.example.myapplication.database;

import java.util.List;

public interface OnResultCallback<T> {
    void onResult(List<T> result);
}
