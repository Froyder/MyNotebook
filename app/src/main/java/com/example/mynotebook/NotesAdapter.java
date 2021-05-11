package com.example.mynotebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> notes = new ArrayList<>();
    private OnNoteClicked clickListener;
    private final Fragment fragment;
    private int menuPosition;

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
        holder.name.setText(note.getNoteNameString());
        holder.image.setImageResource(R.drawable.ic_baseline_android_24);

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

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuPosition = getLayoutPosition();
                    getClickListener().onNoteClicked(notes.get(getBindingAdapterPosition()));
                }
            });
            name = itemView.findViewById(R.id.note_name);
            image = itemView.findViewById(R.id.image);

        }
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);
    }
}
