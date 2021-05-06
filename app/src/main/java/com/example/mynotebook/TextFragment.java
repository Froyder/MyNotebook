package com.example.mynotebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


public class TextFragment extends Fragment {

    private TextView noteText;
    private static final String ARG_NOTE = "ARG_NOTE";

    public TextFragment() {

    }

    public static TextFragment newInstance(Note note) {
        TextFragment fragment = new TextFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);

        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteText = view.findViewById(R.id.note_text);

        Note note = getArguments().getParcelable(ARG_NOTE);
        noteText.setText(note.getNoteText());
    }

}