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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddNoteFragment extends Fragment {

    Button addButton;
    TextView tV;
    EditText addName, addText;
    MyViewModel model;
    FragmentManager fM;

    MutableLiveData<List<Note>> noteData = new MutableLiveData<>();
    private final NotesRepository repository = new FirestoreNotesRepository();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

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
        fM = getParentFragmentManager();

        addButton = rootView.findViewById(R.id.add_note_button);
        addButton.setOnClickListener(addButtonListener);

        tV = rootView.findViewById(R.id.add_note_name_tV);
        addName = rootView.findViewById(R.id.add_note_name_eT);
        addText = rootView.findViewById(R.id.add_note_text_eT);
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        return rootView;
    }

    View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Activity activity = getActivity();
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            try {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                e.fillInStackTrace();
            }

            repository.addNote(addName.getText().toString(), "https://cdn.pixabay.com/photo/2020/06/22/10/40/castle-5328719_1280.jpg", addText.getText().toString(), new Callback<Note>() {
                @Override
                public void onSuccess(Note value) {
                    if (noteData.getValue() != null) {

                        ArrayList<Note> notes = new ArrayList<>(noteData.getValue());

                        notes.add(value);

                        noteData.setValue(notes);
                    } else {
                        ArrayList<Note> notes = new ArrayList<>();
                        notes.add(value);

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

//            model.addNote(addName.getText().toString(), addText.getText().toString());
//            fM.beginTransaction()
//                    .replace(R.id.container, new ListFragment())
//                    .addToBackStack(null)
//                    .commit();
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}