package com.my.gobike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class calendar extends AppCompatActivity {
    private static final String TAG ="calendar";
    private CalendarView mcalenderview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mcalenderview = findViewById(R.id.calendar);
        mcalenderview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                     String date = year + "/" + (month+1) + "/" + dayOfMonth;


                Log.d(TAG, "onSelectedDayChange: date " + date);
                Intent intent = new Intent(calendar.this, AddAnnonceActivity.class);
                intent.putExtra("date", date);

                startActivity(intent);
            }
            });
    }
}