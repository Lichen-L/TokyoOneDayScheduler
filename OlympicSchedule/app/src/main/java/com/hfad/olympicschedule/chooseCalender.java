package com.hfad.olympicschedule;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class chooseCalender extends AppCompatActivity {
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_calender);
        //action bar setting
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //listener to catch(listen) the selected date
        final CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                final SimpleDateFormat sdf = new SimpleDateFormat("MMM d.yyyy(EEE)",Locale.ENGLISH);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                selectedDate = sdf.format(calendar.getTime());

                Toast.makeText(getApplicationContext(), "you just chose "+ selectedDate, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickConfirmButton(View view){
        Intent intent = new Intent(this, timeLine.class);
        intent.putExtra("date",selectedDate);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int itemId = item.getItemId();
        if (itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
