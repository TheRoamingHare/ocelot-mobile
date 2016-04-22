package com.mobile.ocelot.ocelot_mobile_test;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.MotionEvent;
import android.widget.TextView;

import com.mobile.ocelot.ocelot_mobile_test.MainActivity;
import com.mobile.ocelot.ocelot_mobile_test.R;
import com.mobile.ocelot.ocelot_mobile_test.RecordDatePicker;

import java.util.Calendar;

import dalvik.annotation.TestTargetClass;

import static android.support.v4.app.ActivityCompat.startActivity;


public class ApplicationTest extends ActivityInstrumentationTestCase2<RecordDatePicker> {

    TextView date_shown;
    private RecordDatePicker rdpActivity;
    private Instrumentation rdpActivityInstrumentation = null;

    public ApplicationTest() {
        super("com.mobile.ocelot.ocelot_mobile_test", RecordDatePicker.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent i = new Intent("com.mobile.ocelot.ocelot_mobile_test");
        rdpActivity.startActivity(i);
        rdpActivity = getActivity();
        rdpActivityInstrumentation = getInstrumentation();
        setActivityInitialTouchMode(false);

        date_shown = (TextView) rdpActivity.findViewById(R.id.textView3);
    }

    public void testPreconditions() {
        assertNotNull(rdpActivity);
        assertNotNull(date_shown);
    }

    @UiThreadTest
    public void testDateShown () {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        String right_date = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        String page_date = date_shown.getText().toString();
        assertEquals("Default date shown should be today", right_date, page_date);
    }

    protected void tearDown() {
        rdpActivity.finish();
    }

}
