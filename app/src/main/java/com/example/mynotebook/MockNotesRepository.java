package com.example.mynotebook;

import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public void getNotes(Callback<List<Note>> callback) {

    }

    @Override
    public void addNote(String title, String imageUrl, String text, Callback<Note> callback) {

    }

    @Override
    public void changeNote(Note note, String name, String text, Callback<Object> callback) {

    }

    @Override
    public void deleteNote(Note item, Callback<Object> callback) {

    }

}
