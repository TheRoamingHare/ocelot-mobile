package com.mobile.ocelot.ocelot_mobile_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abby on 3/29/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    private static String dbName = "ocelot-db";
    private static String tr_table = "Thought_Records";
    private static String col_tr_id = "tr_ID";
    private static String col_tr_activity = "Activity";
    private static String col_emotion = "Emotion";
    private static String col_strength = "Strength_Before";
    private static String col_thoughts = "Thoughts";
    private static String col_alternatives = "Alternatives";
    private static String col_strength2 = "Strength_After";
    private static String col_tr_date = "Date";

    private static String al_table = "Activity Log";
    private static String col_al_id = "ID";
    private static String col_al_activity = "Activity";
    private static String col_mood = "Mood";
    private static String col_al_date = "Date";

    private Context cont;

    public DatabaseHelper(Context context) {
        super(context, dbName, null,1);
        this.cont = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + al_table + " (" + col_al_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_al_activity + " TEXT, " + col_mood + " INTEGER, " +
                col_al_date + " DEFAULT CURRENT_TIMESTAMP NOT NULL);");
        db.execSQL("CREATE TABLE "+ tr_table +"("+col_tr_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                col_tr_activity+ " TEXT, " + col_emotion + " TEXT, " + col_strength +" TEXT, " +
                col_thoughts +" TEXT, " + col_alternatives +" TEXT, "+ col_strength2 +" TEXT, " +
                col_tr_date +" DEFAULT CURRENT_TIMESTAMP NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}
