package com.example.mynotebook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    boolean isLandscape;
    FragmentManager fM;
    Toolbar toolbar;
    MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        isLandscape = getResources().getBoolean(R.bool.isLandscape);
        fM = getSupportFragmentManager();
        toolbar = findViewById(R.id.toolbar);
        model = new ViewModelProvider(this).get(MyViewModel.class);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.about_button) {
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

        if (item.getItemId() == R.id.add_note) {
            if (!isLandscape) {
                fM.beginTransaction()
                        .replace(R.id.container, new AddNoteFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                fM.beginTransaction()
                        .replace(R.id.text_container, new AddNoteFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }

        if (item.getItemId() == R.id.back_button) {
            fM.popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        setSupportActionBar(toolbar);
        initSideMenu();

        if (!isLandscape) {
            Fragment fragment = fM.findFragmentById(R.id.container);

                fM.beginTransaction()
                        .replace(R.id.container, new StartFragment())
                        .commit();

        } else {
            Fragment fragment = fM.findFragmentById(R.id.list_container);
            if (fragment == null) {

                fM.beginTransaction()
                        .replace(R.id.list_container, new ListFragment())
                        .commit();
            }
        }
    }

    public void initSideMenu() {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView sideMenu = findViewById(R.id.side_menu);
        sideMenu.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.about_button) {
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

            if (item.getItemId() == R.id.add_note) {
                if (!isLandscape) {
                    fM.beginTransaction()
                            .replace(R.id.container, new AddNoteFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    fM.beginTransaction()
                            .replace(R.id.text_container, new AddNoteFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }

            if (item.getItemId() == R.id.back_button) {
                fM.popBackStack();
            }
            drawer.close();
            return false;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}