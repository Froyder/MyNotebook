package com.example.mynotebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    NotesRepository notesRepository = new NotesRepository();
    MutableLiveData<List<Note>> noteData = new MutableLiveData<>();

    public LiveData<List<Note>> getNotes() {
        noteData.postValue(notesRepository.getNotes());
        return noteData;
    }

    public void addNote (String name, String text) {
        notesRepository.addNote(name, text);
        noteData.postValue(notesRepository.getNotes());
    }

}
