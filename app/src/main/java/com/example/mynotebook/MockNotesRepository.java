package com.example.mynotebook;

import java.util.List;

public class MockNotesRepository implements NotesRepository {
    @Override
    public void getNotes(Callback<List<Note>> callback) {

    }

    @Override
    public void addNote(Callback<Note> callback) {

    }

    @Override
    public void remove(Note item, Callback<Object> callback) {

    }
}
