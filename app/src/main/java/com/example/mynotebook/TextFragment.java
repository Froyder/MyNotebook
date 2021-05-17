package com.example.mynotebook;

import android.app.Activity;
import android.content.SharedPreferences;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;


public class TextFragment extends Fragment {

    private EditText editName, editText;
    Button changeButton, deleteButton;
    MyViewModel model;
    FragmentManager fM;
    Note note;

    private static boolean ifDelete;

    private static final String ARG_NOTE = "ARG_NOTE";
    MutableLiveData<List<Note>> noteData = new MutableLiveData<>();
    private final NotesRepository repository = new FirestoreNotesRepository();

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
            if (note != null) {
                editName.setText(note.getTitle());
                editText.setText(note.getText());
            }

        return rootView;
    }

    public void dontDelete () {
        ifDelete = false;
    }

    public void doDelete () {
        ifDelete = true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View.OnClickListener changeButtonListener = (v -> {
        Activity activity = getActivity();
        try {
            InputMethodManager inputMethodManager;
            if (activity != null) {
                inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        //model.changeNote(note, editName.getText().toString(), editText.getText().toString());


        repository.changeNote(note, editName.getText().toString(), editText.getText().toString(), new Callback<Object>() {

            @Override
            public void onSuccess(Object value) {
                if (noteData.getValue() != null) {

                    ArrayList<Note> notes = new ArrayList<>(noteData.getValue());

                    noteData.setValue(notes);
                } else {
                    ArrayList<Note> notes = new ArrayList<>();

                    noteData.setValue(notes);
                }

                fM.beginTransaction()
                        .replace(R.id.container, new ListFragment())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onError(Throwable error) {

            }
        });

    });

    public View.OnClickListener deleteButtonListener = (v -> {
        Activity activity = getActivity();

        try {
            InputMethodManager inputMethodManager;
            if (activity != null) {
                inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        DeleteFragment dlgBuilder = new DeleteFragment();
        dlgBuilder.show(getParentFragmentManager(), "transactionTag");

            if (ifDelete) {
                repository.deleteNote (note, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object value) {

                        if (noteData.getValue()!= null) {

                            ArrayList<Note> notes = new ArrayList<>(noteData.getValue());

                            notes.remove(note);

                            noteData.setValue(notes);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {

                    }

                });
        }


//        fM.beginTransaction()
//                .replace(R.id.container, new ListFragment())
//                .addToBackStack(null)
//                .commit();
    });

}