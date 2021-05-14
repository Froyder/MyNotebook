package com.example.mynotebook;

import java.util.List;

public interface NotesRepository {

    void getNotes (Callback <List<Note>> callback);

    void addNote(String title, String imageUrl, String text, Callback<Note> callback);

    void changeNote (Note note, String name, String text, Callback<Object> callback);

    void deleteNote (Note item, Callback <Object> callback);

}
