package com.mobile.ocelot.ocelot_mobile_test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Abby on 3/29/16.
 */
public class NewThoughtRecord extends MainActivity {

    EditText activityF;
    EditText thoughtsF;
    EditText feelingF;
    EditText s1F;
    EditText s2F;
    EditText alt_respF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tr_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        activityF = (EditText) findViewById(R.id.tr_activity);
        thoughtsF = (EditText) findViewById(R.id.tr_thoughts);
        feelingF = (EditText) findViewById(R.id.tr_feeling);
        s1F = (EditText) findViewById(R.id.tr_strength1);
        s2F = (EditText) findViewById(R.id.tr_strength2);
        alt_respF = (EditText) findViewById(R.id.tr_alt_resp);
    }

    public void saveThoughtRecord(View button){
        String activity = activityF.getText().toString();
        String thoughts = thoughtsF.getText().toString();
        String feeling = feelingF.getText().toString();
        String s1 = s1F.getText().toString();
        String s2 = s2F.getText().toString();
        String alt_resp = alt_respF.getText().toString();

        String add_record = "INSERT INTO Thought_Records (Activity,Emotion,Strength_Before,Thoughts,Alternatives,Strength_After)" +
                "VALUES ('"+activity+"','"+feeling+"','"+s1+"','"+thoughts+"','"+alt_resp+"','"+s2+"')";
        app_db.execSQL(add_record);
        Log.d("New Thought Record", "New record added");

        setContentView(R.layout.new_tr_main);
    }
}
