package com.example.mynotebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


public class TextFragment extends Fragment {

    private EditText editName, editText;
    Button changeButton, deleteButton;
    MyViewModel model;
    FragmentManager fM;
    Note note;

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
        View rootView = inflater.inflate(R.layout.fragment_note_text, container, false);
            fM = getParentFragmentManager();
            model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
            editName = rootView.findViewById(R.id.note_name);
            editText = rootView.findViewById(R.id.note_text);

            changeButton = rootView.findViewById(R.id.change_note_button);
            changeButton.setOnClickListener(changeButtonListener);
            deleteButton = rootView.findViewById(R.id.delete_note_button);
            deleteButton.setOnClickListener(deleteButtonListener);

            note = getArguments().getParcelable(ARG_NOTE);
            editName.setText(note.getNoteNameString());
            editText.setText(note.getNoteTextString());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View.OnClickListener changeButtonListener = (v -> {

        Activity activity = getActivity();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        model.changeNote(note, editName.getText().toString(), editText.getText().toString());
        fM.beginTransaction()
                .replace(R.id.container, new ListFragment())
                .addToBackStack(null)
                .commit();
    });

    public View.OnClickListener deleteButtonListener = (v -> {

        Activity activity = getActivity();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        model.deleteNote(note);
        fM.beginTransaction()
                .replace(R.id.container, new ListFragment())
                .addToBackStack(null)
                .commit();
    });

}