package com.example.mynotebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> notes = new ArrayList<>();
    private OnNoteClicked clickListener;

    public void addData(List<Note> toAdd) {
        notes.clear();
        notes.addAll(toAdd);
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);
    }
}
