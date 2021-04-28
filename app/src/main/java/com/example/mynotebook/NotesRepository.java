package com.example.mynotebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotesRepository {

        public List<Note> getNotes () {
            ArrayList<Note> notes = new ArrayList<>();
            notes.add(new Note(R.string.fst));
            notes.add(new Note(R.string.scn));
            return notes;
        }

    }

