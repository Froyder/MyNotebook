package com.example.mynotebook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements ListFragment.OnNoteClicked {

    Button action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        FragmentManager fM = getSupportFragmentManager();

        if (item.getItemId() == R.id.action_one) {
            if (!isLandscape) {
                fM.beginTransaction()
                        .replace(R.id.container, new AboutFragment())
                        .addToBackStack(null)
                        .commit();

            } else {
                fM.beginTransaction()
                        .replace(R.id.text_container, new AboutFragment())
                        .addToBackStack(null)
                        .commit();

            }
        }

        if (item.getItemId() == R.id.action_two) {
            Toast.makeText(this, "Action Two", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initView () {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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