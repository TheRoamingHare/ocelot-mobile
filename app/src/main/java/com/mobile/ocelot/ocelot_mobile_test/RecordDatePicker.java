package com.mobile.ocelot.ocelot_mobile_test;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Abby on 3/29/16.
 */
public class RecordDatePicker extends MainActivity implements View.OnClickListener {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    public int year, month, day;

    public int sendYear, sendMonth, sendDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_date_picker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));

        sendDay = day;
        sendMonth = month;
        sendYear = year;

        Button tr_button = (Button) findViewById(R.id.ButtonSendDate);
        tr_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, OldThoughtRecordViewer.class);
        Bundle b = new Bundle();
        b.putInt("day", sendDay);
        b.putInt("month", sendMonth);
        b.putInt("year", sendYear);
        Log.d("SEND DATE --->",Integer.toString(sendDay));
        Log.d("SEND DATE --->",Integer.toString(sendMonth));
        Log.d("SEND DATE --->",Integer.toString(sendYear));
        intent.putExtras(b);
        startActivity(intent);
        finish();
        //startActivity(new Intent(this, OldThoughtRecordViewer.class));
    }


}
