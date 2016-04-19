package com.mobile.ocelot.ocelot_mobile_test;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import junit.framework.TestCase;

import java.util.Calendar;

/**
 * Created by Abby on 4/18/16.
 */
public class RecordDatePickerTest extends ActivityInstrumentationTestCase2<RecordDatePicker> {

    TextView date_shown;
    private RecordDatePicker rdpActivity;
    private Instrumentation rdpActivityInstrumentation = null;

    public RecordDatePickerTest() {
        super("com.mobile.ocelot.ocelot_mobile_test", RecordDatePicker.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        Intent i = new Intent("com.mobile.ocelot.ocelot_mobile_test");
        rdpActivity.startActivity(i);
        rdpActivity = getActivity();
        rdpActivityInstrumentation = getInstrumentation();
        setActivityInitialTouchMode(false);

        date_shown = (TextView) rdpActivity.findViewById(R.id.textView3);
    }

    public void tearDown() throws Exception {
        rdpActivity.finish();
    }

    public void testOnCreate() throws Exception {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        String right_date = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        String page_date = date_shown.getText().toString();
        assertEquals("Default date shown should be today", right_date, page_date);
    }

}