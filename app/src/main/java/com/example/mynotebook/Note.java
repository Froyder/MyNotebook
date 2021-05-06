package com.example.mynotebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;

public class Note implements Parcelable {

    private int noteName;
    private int noteText;
    Calendar noteDate;

    public String getNoteNameString() {
        return noteNameString;
    }

    public String getNoteTextString() {
        return noteTextString;
    }

    private String noteNameString;
    private String noteTextString;

    public Note(String noteNameString, String noteTextString) {
        this.noteNameString = noteNameString;
        this.noteTextString = noteTextString;
    }

    public Note () {
    }

    public Note (int noteName, int noteText) {
        this.noteName = noteName;
        this.noteText = noteText;
    }

    public Note(int name) {

    }

    protected Note(Parcel in) {
        noteName = in.readInt();
        noteText = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getNoteName() {
        return noteName;
    }

    public int getNoteText() {
        return noteText;
    }

    public Calendar getNoteDate() {
        return noteDate;
    }

    public Note (int noteName, int noteText, Calendar noteDate) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteDate = noteDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteName);
        dest.writeInt(noteText);
    }
}