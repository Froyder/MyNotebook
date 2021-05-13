package com.example.mynotebook;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {

    private static String NOTES = "notes";
    private static String TITLE = "title";
    private static String IMAGE = "image";
    private static String CREATED = "createdAt";
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {
        fireStore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            ArrayList <Note> tmp = new ArrayList<>();

                            List <DocumentSnapshot> docs = task.getResult().getDocuments();
                            for (DocumentSnapshot doc : docs) {

                                String id = doc.getId();
                                String title = doc.getString(TITLE);
                                String image = doc.getString(IMAGE);
                                Date createdAt = doc.getDate(CREATED);

                                tmp.add(new Note (id, title, image, createdAt));
                            }
                            callback.onSuccess(tmp);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    @Override
    public void addNote(Callback<Note> callback) {

    }

    @Override
    public void remove(Note item, Callback<Object> callback) {

    }
}
