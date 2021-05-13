package com.example.mynotebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {

    NotesAdapter adapter;
    RecyclerView notesList;
    boolean isLandscape;
    FragmentManager fM;
    MyViewModel model;

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }

    }

    public interface OnNoteClicked {
        void onNoteClicked (Note note);
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_recycler, container, false);

        fM = getParentFragmentManager();
        isLandscape = getResources().getBoolean(R.bool.isLandscape);
        adapter = new NotesAdapter(this);
        notesList = rootView.findViewById(R.id.notes_list);
        RecyclerView.LayoutManager lm = /*new GridLayoutManager(this, 2);*/new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        notesList.setLayoutManager(lm);
        notesList.setAdapter(adapter);
        return rootView;
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyViewModel model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        model.requestNotes();

            model.getNotes().observe(getActivity(), new Observer<List<Note>>() {
                @Override
                public void onChanged (List<Note> notes) {
                    adapter.addData(notes);
                    adapter.notifyDataSetChanged();
                }
            });

        adapter.setClickListener(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                if (isLandscape) {
                    fM.beginTransaction()
                            .replace(R.id.text_container, TextFragment.newInstance(note))
                            .addToBackStack(null)
                            .commit();

                } else {
                    fM.beginTransaction()
                            .replace(R.id.container, TextFragment.newInstance(note))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}