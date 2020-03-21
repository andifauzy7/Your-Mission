package com.example.yourmission;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Mission");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setupDateButton();
        setupSubmitButton();
    }

    private void setupDateButton(){
        Button datePicker  = (Button)findViewById(R.id.datePicker);
        final TextView dateView = (TextView)findViewById(R.id.dateView);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(addTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                        dateView.setText(dateFormatter.format(newDate.getTime()).toUpperCase());
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
    }

    private void setupSubmitButton(){
        Button btn = (Button)findViewById(R.id.submitData);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract data.
                // String sNamaTugas  = namaTugas.getText().toString();
                // String sDescTugas  = descTugas.getText().toString();


                // Pass data.
                Intent moveIntent = new Intent();
                setResult(Activity.RESULT_OK, moveIntent);
                finish();
            }
        });
    }

}
