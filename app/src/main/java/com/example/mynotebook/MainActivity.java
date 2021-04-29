package com.example.mynotebook;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements ListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (!isLandscape) {
            FragmentManager fM = getSupportFragmentManager();

            Fragment fragment = fM.findFragmentById(R.id.container);

            if (fragment == null) {

                fM.beginTransaction()
                        .replace(R.id.container, new ListFragment())
                        .commit();
            }
        } else {
            FragmentManager fM = getSupportFragmentManager();
            Fragment fragment = fM.findFragmentById(R.id.list_container);
            if (fragment == null) {

                fM.beginTransaction()
                        .replace(R.id.list_container, new ListFragment())
                        .commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onNoteClicked(Note note) {
        Boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        FragmentManager fM = getSupportFragmentManager();

        if (isLandscape) {
            fM.beginTransaction()
                    .replace(R.id.text_container, TextFragment.newInstance(note))
                    .commit();

        } else {
            fM.beginTransaction()
                    .replace(R.id.container, TextFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }

    }
}