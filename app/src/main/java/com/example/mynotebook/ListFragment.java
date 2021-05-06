package com.example.mynotebook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {

    LinearLayout layoutView;
    NotesAdapter adapter;
    RecyclerView notesList;

    public interface OnNoteClicked {
        void onNoteClicked (Note note);
    }

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }

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
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    // создаём список заметок из репозитория
    private void initList(View view) {

        adapter = new NotesAdapter();

        /*
        adapter.setClickListener(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(NotesListActivity.this, note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

         */

        MyViewModel model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        model.getNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged (List<Note> notes) {
                adapter.addData(notes);
                adapter.notifyDataSetChanged();
            }
        });

        notesList = notesList.findViewById(R.id.notes_list);
        RecyclerView.LayoutManager lm = /*new GridLayoutManager(this, 2);*/new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        notesList.setLayoutManager(lm);
        notesList.setAdapter(adapter);

        /*
        MyViewModel model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        model.getNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                layoutView = view.findViewById(R.id.list_layout);
                layoutView.removeAllViews();

                for (Note note : notes){

                    View listView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, layoutView, false);
                    ImageView image = listView.findViewById(R.id.image);
                    image.setImageResource(R.drawable.ic_launcher_foreground);

                    TextView title = listView.findViewById(R.id.note_name);
                    title.setText(note.getNoteNameString());

                    title.setTextSize(20);
                    layoutView.addView(listView);

                    listView.setOnClickListener(v -> openDetails(note));
                }
            }
        });
        */
    }

    /*
    public void openDetails(Note note) {
        if (onNoteClicked != null) {
            onNoteClicked.onNoteClicked(note);
        }
    }

     */

}