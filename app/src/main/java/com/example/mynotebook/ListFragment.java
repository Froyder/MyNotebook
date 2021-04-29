package com.example.mynotebook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ListFragment extends Fragment {

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

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {

        List <Note> notes = new NotesRepository().getNotes();
        LinearLayout layoutView = view.findViewById(R.id.list_layout);

        for (Note note : notes){

            View listView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, layoutView, false);
            ImageView image = listView.findViewById(R.id.image);
            image.setImageResource(R.drawable.ic_launcher_foreground);

            TextView title = listView.findViewById(R.id.note_name);
            title.setText(note.getNoteName());

            title.setTextSize(20);
            layoutView.addView(listView);

            listView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDetails(note);
                }
            });
        }
    }

    public void openDetails(Note note) {
        if (onNoteClicked != null) {
            onNoteClicked.onNoteClicked(note);
        }
    }

}