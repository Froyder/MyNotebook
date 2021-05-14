package com.example.mynotebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> notes = new ArrayList<>();
    private OnNoteClicked clickListener;
    private final Fragment fragment;
    private int menuPosition;
    NotesAdapter adapter;

    public void addData(List<Note> toAdd) {
        notes.clear();
        notes.addAll(toAdd);
    }

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note, position, fragment);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public OnNoteClicked getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnNoteClicked clickListener) {
        this.clickListener = clickListener;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener,
            MenuItem.OnMenuItemClickListener {

        Note note;
        private final TextView name;
        private final ImageView image;
        private Fragment fragment;
        NotesAdapter adapter;
        FragmentManager fM;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            name = itemView.findViewById(R.id.note_name);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem changeNote = menu.add(0, 0, 0, "Изменить заметку");
            MenuItem deleteNote = menu.add(0, 1, 0, "Удалить заметку");
            changeNote.setOnMenuItemClickListener(this);
            deleteNote.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 0:

                    return true;
                case 1:

                default:
                    return true;
            }
        }

            public void bind (Note note, int position, Fragment fragment) {
                name.setText(note.getTitle());
                //Glide.with(fragment).load(note.getImgURL()).into(image);
                image.setImageResource(R.drawable.ic_baseline_android_24);

                itemView.setOnClickListener(v -> getClickListener().onNoteClicked(note));
                menuPosition = position;
        }

        @Override
        public void onClick(View v) {

        }
    }

        interface OnNoteClicked {
        void onNoteClicked(Note note);
        }
}


