package com.example.mynotebook;

import java.util.List;

public interface NotesRepository {

    void getNotes (Callback <List<Note>> callback);

    void addNote(String title, String imageUrl, String text, Callback<Note> callback);

    void remove (Note item, Callback <Object> callback);


}
