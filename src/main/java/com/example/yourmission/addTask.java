package com.example.yourmission;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addTask extends AppCompatActivity {
    private TextView dateView;
    private EditText namaTugas, descTugas;
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
        this.dateView = (TextView)findViewById(R.id.dateView);

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
                datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
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
                namaTugas = (EditText)findViewById(R.id.txNamaTugas);
                descTugas = (EditText)findViewById(R.id.txDescTugas);
                String sNamaTugas  = namaTugas.getText().toString();
                String sDescTugas  = descTugas.getText().toString();
                String sDateTugas  = dateView.getText().toString();

                // Pass data.
                if(sNamaTugas.equals("") && sDescTugas.equals("") && sDateTugas.equals("")){
                    Toast.makeText(addTask.this,"Data task kurang lengkap.",Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("namaTugas", sNamaTugas);
                    bundle.putString("descTugas", sDescTugas);
                    bundle.putString("dateTugas", sDateTugas);
                    Intent moveIntent = new Intent();
                    moveIntent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, moveIntent);
                    finish();
                }
            }
        });
    }

}
