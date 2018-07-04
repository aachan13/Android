package com.example.user.kelasinid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CalenderEditor extends AppCompatActivity {
    private static final String TAG = "TAG";
    @TargetApi(Build.VERSION_CODES.N)
    Calendar dateTime = Calendar.getInstance();
    DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    Button btnSave;
    TextView tvDate;
    TextView tvTime;
    EditText titleRemarks;
    String dateTimeString;
    String time;
    String date;
    String title;
    static String EXTRA_DATE = "extra_date", EXTRA_TIME = "extra_Time", EXTRA_TITLEREMAKS = "extra_title_remarks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_editor);
        titleRemarks = (EditText) findViewById(R.id.edtTitleRemarks);

        btnSave = (Button) findViewById(R.id.ButtonSave);
        tvDate = (TextView) findViewById(R.id.date);
        tvTime = (TextView) findViewById(R.id.time);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString(toDoAdapter.EXTRA_TITLEREMAKS);
            date = extras.getString(toDoAdapter.EXTRA_DATE);
            time = extras.getString(toDoAdapter.EXTRA_TIME);
            titleRemarks.setText(title);
            tvDate.setText("Date: " + date);
            tvTime.setText("Time: " + time);
        }

        tvDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                updateTime();


            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewToDo();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfyear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfyear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int Minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, Minute);
            updateTextLabel();
        }


    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateTextLabel() {

        time = timeFormat.format(dateTime.getTime());
        date = dateFormat.format(dateTime.getTime());
        tvDate.setText("Date: " + date);
        tvTime.setText("Time: " + time);
    }


    private void addNewToDo() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_TIME, time);
        returnIntent.putExtra(EXTRA_DATE, date);
        title = titleRemarks.getText().toString();
        returnIntent.putExtra(EXTRA_TITLEREMAKS, title);
        Log.d(TAG, "title: " + titleRemarks.getText().toString());
        Log.d(TAG, "Date: " + date);
        Log.d(TAG, "time: " + time);

        addAlarmScheduler();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }

    private void addAlarmScheduler() {
        AlarmScheduler alarmScheduler = new AlarmScheduler();
        alarmScheduler.setOneTimeAlarm(this, alarmScheduler.TYPE_ONE_TIME, date, time, title);
    }


}
