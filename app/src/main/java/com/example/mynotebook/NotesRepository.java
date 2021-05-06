package com.example.mynotebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotesRepository {
    ArrayList<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        if (notes.isEmpty()) {
            notes.add(new Note("Al", "Some 1 text"));
            notes.add(new Note("A2", "Some 2 text"));
            notes.add(new Note("A3", "Some 3 text"));
            notes.add(new Note("A4", "Some 4 text"));

            return notes;
        }
        return notes;
    }

    public void addNote(String name, String text) {
        notes.add(new Note(name, text));
    }
}