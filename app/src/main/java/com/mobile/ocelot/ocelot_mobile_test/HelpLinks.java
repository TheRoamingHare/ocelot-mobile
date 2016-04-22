package com.mobile.ocelot.ocelot_mobile_test;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Abby on 3/29/16.
 */
public class HelpLinks extends MainActivity {

    String chat_link = "http://www.suicidepreventionlifeline.org/gethelp/lifelinechat.aspx";
    String help_links = "http://www.suicidepreventionlifeline.org/gethelp/yourself.aspx";
    String help_centers = "http://www.suicidepreventionlifeline.org/getinvolved/locator.aspx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_links);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView s_number = (TextView) findViewById(R.id.help_line_number);
        final String number = s_number.getText().toString();
        s_number.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });

        final TextView chat = (TextView) findViewById(R.id.chat_link);
        assert chat != null;
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chat_link));
                startActivity(browserIntent);
            }
        });

        final TextView other_resources = (TextView) findViewById(R.id.help_page);
        assert other_resources != null;
        other_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(help_links));
                startActivity(browserIntent);
            }
        });

        final TextView locator = (TextView) findViewById(R.id.center_locator);
        assert locator != null;
        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(help_centers));
                startActivity(browserIntent);
            }
        });

    }
}
