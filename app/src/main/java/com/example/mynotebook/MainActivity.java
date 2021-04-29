package com.example.mynotebook;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends FragmentActivity {

    static String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_button).setOnClickListener(addButtonListener);

        initView();

    }

    public void initView () {

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        FragmentManager fM = getSupportFragmentManager();

        if (!isLandscape) {
            Fragment mainFragment = fM.findFragmentById(R.id.container);

            if (mainFragment == null) {
                fM.beginTransaction()
                        .replace(R.id.container, new NoteList())
                        .commit();
            }

        } else {
            Fragment textFragment = fM.findFragmentById(R.id.text_container);

            if (textFragment != null) {
                fM.beginTransaction()
                        .replace(R.id.text_container, new NoteText())
                        .commit();
            }
        }
    }

    View.OnClickListener addButtonListener = v -> {
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        FragmentManager fM = getSupportFragmentManager();

        if (!isLandscape) {
            Fragment mainFragment = fM.findFragmentById(R.id.container);

        } else {
            Fragment leftFragment = fM.findFragmentById(R.id.note_list);

            Fragment rightFragment = fM.findFragmentById(R.id.text_container);

            ((TextView) rightFragment.getView().findViewById(R.id.note_text))
                    .setText(details);
        }
    };
}