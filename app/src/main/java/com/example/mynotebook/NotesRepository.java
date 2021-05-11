package com.example.mynotebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotesRepository {
    ArrayList<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        if (notes.isEmpty()) {
            notes.add(new Note("First note", "Some 1 text"));
            notes.add(new Note("Second note", "Some 2 text"));
            notes.add(new Note("Third note", "Some 3 text"));
            notes.add(new Note("Fourth note", "Some 4 text"));
            notes.add(new Note("Fifth note", "Some 5 text"));
            notes.add(new Note("Sixth note", "Some 6 text"));
            notes.add(new Note("Seventh note", "Some 7 text"));
            notes.add(new Note("Eighth note", "Some 8 text"));

            return notes;
        }
        return notes;
    }

    public void addNote(String name, String text) {
        notes.add(new Note(name, text));
    }

    public void changeNote (Note note, String name, String text) {
        if (notes.contains(note)) {
            notes.set(notes.indexOf(note), (new Note(name, text)));
        }
    }

    public void deleteNote (Note note) {
            notes.remove(note);
    }

    public void removeNote (int position) {
        notes.remove(position);
    }

    public Note openNote (int position) {
        return notes.get(position);
    }

}