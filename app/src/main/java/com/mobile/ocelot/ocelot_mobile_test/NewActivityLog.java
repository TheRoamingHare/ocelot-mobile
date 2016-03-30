package com.mobile.ocelot.ocelot_mobile_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class NewActivityLog extends MainActivity {

    EditText activityF, moodF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity_log);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        activityF = (EditText) findViewById(R.id.al_activity);
        moodF = (EditText) findViewById(R.id.al_mood);


    }

    public void saveActivityLog(View button){
        String activity = activityF.getText().toString();
        String mood = moodF.getText().toString();


        String add_record = "INSERT INTO Activity_Log (Activity,Mood)" +
                "VALUES ('"+activity+"','"+mood+"')";
        app_db.execSQL(add_record);
        Log.d("New Activity Log", "New activity log added");

        startActivity(new Intent(this, NewActivityLog.class));
    }
}
