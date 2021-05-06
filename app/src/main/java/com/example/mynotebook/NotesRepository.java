package com.example.mynotebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotesRepository {

        public List<Note> getNotes () {
            ArrayList <Note> notes = new ArrayList<>();

            notes.add(new Note(R.string.note1, R.string.text1));
            notes.add(new Note(R.string.note2, R.string.text2));
            notes.add(new Note(R.string.note3, R.string.text3));
            notes.add(new Note(R.string.note4, R.string.text4));
            notes.add(new Note(R.string.note5, R.string.text5));
            notes.add(new Note(R.string.note6, R.string.text6));
            notes.add(new Note(R.string.note7, R.string.text7));
            return notes;
        }

    }

