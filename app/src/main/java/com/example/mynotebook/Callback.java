package com.example.mynotebook;

public interface Callback <T> {

    void onSuccess (T value);

    void onError (Throwable error);
}
