package com.mobile.ocelot.ocelot_mobile_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    private static final String TAG = "MainActivity";
    public SQLiteDatabase app_db;
    private EditText activityF, moodF;
    private View send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DatabaseHelper db_help = new DatabaseHelper(this.getApplicationContext());
        app_db = db_help.getWritableDatabase();

        activityF = (EditText) findViewById(R.id.al_activity_main);
        moodF = (EditText) findViewById(R.id.al_mood_main);
        send = (View) findViewById(R.id.al_send_main);


        findViewById(R.id.al_send_main).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                boolean stat = true;
                final String act = activityF.getText().toString();
                final String mood = moodF.getText().toString();

                if (!isValidActivity(act)) {
                    activityF.setError("Empty Activity");
                    stat = false;
                }

                if (!isValidMood(mood)) {
                    moodF.setError("Invalid Mood Score");
                    stat = false;
                }

                if (stat) {
                    saveActivityLogMain(send);
                }
            }
        });
    }

    private boolean isValidActivity(String act) {
        if (act != null && act.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean isValidMood(String mood) {
        int i;
        try {
            i = Integer.parseInt(mood);
        } catch (NumberFormatException e) {
            return false;
        }
        if (i >= 0 && i <= 100) {
            return true;
        }
        return false;
    }

    public void saveActivityLogMain(View button){
        String activity = activityF.getText().toString();
        String mood = moodF.getText().toString();


        String add_record = "INSERT INTO Activity_Log (Activity, Mood)" +
                "VALUES ('"+activity+"','"+mood+"')";
        app_db.execSQL(add_record);
        Log.d("New Activity Log", "New activity log added");

        Toast.makeText(getApplicationContext(), "New activity log saved", Toast.LENGTH_SHORT).show();

        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        Intent intent = new Intent(this, OldActivityLog.class);
        Bundle b = new Bundle();
        b.putInt("day", day);
        b.putInt("month", month+1);
        b.putInt("year", year);
        intent.putExtras(b);
        startActivity(intent);
        finish();

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    /** Called when the user clicks the Send button */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.new_logs) {
            startActivity(new Intent(this, NewActivityLog.class));
        } else if (id == R.id.log_calendar) {
            startActivity(new Intent(this, ActivityLog.class));
        } else if (id == R.id.new_record) {
            startActivity(new Intent(this, NewThoughtRecord.class));
        } else if (id == R.id.record_calendar) {
            startActivity(new Intent(this, RecordDatePicker.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        app_db.close();
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
