package com.example.user.kelasinid;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Fragment fragment;
    private static final String TAG = "Tag";
    public ArrayList<toDo> model;
    DatabaseHelper db;

    public ArrayList<toDo> getModel() {
        return model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new DatabaseHelper(this).getData();
        db = new DatabaseHelper(this);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view = getSupportActionBar().getCustomView();
        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        Log.d(TAG, String.valueOf(bottomNavigationView.getSelectedItemId()));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, "id now: " + item.getItemId());
//                Log.d(TAG, String.valueOf(bottomNavigationView.getSelectedItemId()));
                int selectedItem = bottomNavigationView.getSelectedItemId();
//                Log.d(TAG, "raw input: " + String.valueOf(bottomNavigationView.getSelectedItemId()));
//                Log.d(TAG, "convert to Selected: " + String.valueOf(selectedItem));
                String status = "";
                switch (item.getItemId()) {

                    case (int) R.id.action_toDo:

                        status = "onclick to Do";

                        fragment = new fragment_toDo();
                        break;

                    case (int) R.id.action_profile:

                        Log.d(TAG, "onClick: profile ");
                        fragment = new fragment_profile();
                        status = "onclick profile";
                        break;
                }
                Log.d(TAG, " status=" + status);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;

            }
        });
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, new fragment_toDo()).commit();

        ImageButton addCalendar = (ImageButton) findViewById(R.id.buttonAddCalendar);
        addCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderEditor.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {


                Bundle extras = data.getExtras();
                if (extras != null) {
                    String id = String.valueOf(model.size() - 1);
                    String title = extras.getString(CalenderEditor.EXTRA_TITLEREMAKS);
                    String date = extras.getString(CalenderEditor.EXTRA_DATE);
                    String time = extras.getString(CalenderEditor.EXTRA_TIME);
                    Log.d(TAG, "title received" + title);
                    Log.d(TAG, "date received " + date);
                    Log.d(TAG, "time  received" + time);
                    toDo addedtoDo = new toDo(id, title, date, time, 0, false);
                    db.addData(addedtoDo);
                    model.add(addedtoDo);
                    fragment_toDo.adapter.setList(model);
                    fragment_toDo.adapter.notifyItemInserted(model.size());

                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                Bundle extras = data.getExtras();
                if (extras != null) {
                    String id = String.valueOf(fragment_toDo.adapter.getList().get(toDoAdapter.position).getId());
                    String title = extras.getString(CalenderEditor.EXTRA_TITLEREMAKS);
                    String date = extras.getString(CalenderEditor.EXTRA_DATE);
                    String time = extras.getString(CalenderEditor.EXTRA_TIME);
                    Log.d(TAG, "title received" + title);
                    Log.d(TAG, "date received " + date);
                    Log.d(TAG, "time  received" + time);
                    toDo addedtoDo = new toDo(id, title, date, time, 0, false);
                    model.remove(toDoAdapter.position);
                    Log.d(TAG, "position" + toDoAdapter.position);
                    model.add(toDoAdapter.position, addedtoDo);
                    db.updateData(id, title, date, time, 0, false, "Not Done");
                    fragment_toDo.adapter.setList(model);
                    fragment_toDo.adapter.notifyItemChanged(toDoAdapter.position);

                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}


