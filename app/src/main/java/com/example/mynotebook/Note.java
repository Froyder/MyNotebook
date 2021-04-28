package com.example.mynotebook;

import java.util.Calendar;

public class Note {
    private String noteName;
    private String noteText;
    Calendar noteDate;

    public Note () {
    }

    public Note (String noteName, String noteText, Calendar noteDate) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteDate = noteDate;
    }

    public void addNote () {
        Note note = new Note ("", "", Calendar.getInstance());
    }

}