package com.example.mynotebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {

    private int noteName;
    private int noteText;
    private Date createdAt;

    public Note(String id, String title, String imgURL, Date date) {
    }

    public String getId() {
        return id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getTitle() {
        return title;
    }

    private String id;
    private String imgURL;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public String getText() {
        return text;
    }


    public Note(String id, String title, String imgURL, String text, Date createdAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imgURL = imgURL;
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) &&
                Objects.equals(title, note.title) &&
                Objects.equals(imgURL, note.imgURL) &&
                Objects.equals(createdAt, note.createdAt)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imgURL, createdAt);
    }

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
        id = in.readString();
        title = in.readString();
        imgURL = in.readString();
        createdAt = (Date) in.readSerializable();
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

    public Note (int noteName, int noteText, Date createdAt) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.createdAt = createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteName);
        dest.writeInt(noteText);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(imgURL);
        dest.writeSerializable(createdAt);
    }
}