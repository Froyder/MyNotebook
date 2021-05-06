package com.example.mynotebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNoteFragment extends Fragment {

    Button addButton;
    TextView tV;
    EditText addName, addText;
    MyViewModel model;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_note, container, false);
        addButton = rootView.findViewById(R.id.add_note_button);
        addButton.setOnClickListener(addButtonListener);
        tV = rootView.findViewById(R.id.add_note_name_tV);
        addName = rootView.findViewById(R.id.add_note_name_eT);
        addText= rootView.findViewById(R.id.add_note_text_eT);
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        return rootView;
    }

    View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            model.addNote(addName.getText().toString(), addText.getText().toString());
            tV.setText("Добавлено!");
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}