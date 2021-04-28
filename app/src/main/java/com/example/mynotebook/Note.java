package com.example.mynotebook;

import java.util.ArrayList;
import java.util.Calendar;

public class Note {
    private static ArrayList<Note> noteArrayList;
    private int name;
    private String noteName;
    private String noteText;
    Calendar noteDate;

    public Note () {
    }

    public int getName() {
        return name;
    }

    public Note(int name) {

    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public Calendar getNoteDate() {
        return noteDate;
    }

    public Note (String noteName, String noteText, Calendar noteDate) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteDate = noteDate;
    }

    public void addNote () {
        Note note = new Note ("", "", Calendar.getInstance());
        addNoteToList(note);
    }

    public void addNoteToList (Note note) {
        noteArrayList.add(note);
    }

    public static ArrayList<Note> showNotesList() {
        return noteArrayList;
    }

}