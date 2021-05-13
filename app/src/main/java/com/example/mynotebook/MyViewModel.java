package com.example.mynotebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {

    NotesRepository repository = new FirestoreNotesRepository();
    NotesRepositoryLocal notesRepositoryLocal = new NotesRepositoryLocal();
    MutableLiveData<List<Note>> noteData = new MutableLiveData<>();

    public LiveData<List<Note>> getNotesLiveData() {
        return noteData;
    }

    public void requestNotes() {
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> value) {
                noteData.setValue(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public LiveData<List<Note>> getNotes() {
        noteData.postValue(notesRepositoryLocal.getNotes());
        return noteData;
    }

    public void addNote (String name, String text) {
        notesRepositoryLocal.addNote(name, text);
        noteData.postValue(notesRepositoryLocal.getNotes());
    }

    public void changeNote (Note note, String name, String text) {
        notesRepositoryLocal.changeNote(note, name, text);
        noteData.postValue(notesRepositoryLocal.getNotes());
    }

    public void deleteNote (Note note) {
        notesRepositoryLocal.deleteNote(note);
        noteData.postValue(notesRepositoryLocal.getNotes());
    }

    public void removeNote (int position) {
        notesRepositoryLocal.removeNote(position);
        noteData.postValue(notesRepositoryLocal.getNotes());
    }

    public Note openNote (int position) {
        return notesRepositoryLocal.openNote(position);
    }

}
