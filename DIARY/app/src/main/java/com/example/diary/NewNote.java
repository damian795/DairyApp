package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewNote extends AppCompatActivity {


    private TextView tvTime, tvDate;
    int hour, minute;
    String date;
    private DatePickerDialog datePickerDialog;
    private EditText etTitle, etNote;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        tvTime = findViewById(R.id.tvTimePicker);
        initDatePicker();
        tvDate = findViewById(R.id.tvDatePicker);
        tvDate.setText(getTodaysDate());

        databaseReference = FirebaseDatabase.getInstance().getReference().child("New notes");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.setData:
                openDatePicker(item);
                break;
            case R.id.setTime:
                popTimePicker(item);
                break;
            case R.id.saveNote:
                String ID, title, note, time, date;
                ID = databaseReference.push().getKey();
                title = etTitle.getText().toString();
                note = etNote.getText().toString();
                time = hour + ":" + minute;
                date = this.date;

                if(title.equals("") || date.equals("")){
                    Toast.makeText(NewNote.this, "Empty data", Toast.LENGTH_SHORT).show();
                }else{
                    Note n = new Note(ID, title, time, date);
                    databaseReference.child(ID).setValue(n);
                    Toast.makeText(NewNote.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

        return true;
    }



    public void popTimePicker(MenuItem item) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                tvTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                //System.out.println("CZAS: " + hour + ":" + minute);
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("                Select Time");
        timePickerDialog.show();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                date = makeDateString(day, month, year);
                //System.out.println("DATA: " + date + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                tvDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //System.out.println("DATA: " + datePickerDialog);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(MenuItem item) { datePickerDialog.show(); }
}