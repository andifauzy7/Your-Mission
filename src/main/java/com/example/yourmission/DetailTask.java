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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailTask extends AppCompatActivity {
    EditText namaTugas, descTugas;
    TextView deadlineTugas;
    DBAdapter db = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Detail Mission");
        // Passing Data Bundle diterima dari CardViewTaskAdapter
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        namaTugas = (EditText) findViewById(R.id.txNamaTugas);
        descTugas = (EditText) findViewById(R.id.txDescTugas);
        deadlineTugas = (TextView) findViewById(R.id.dateView);
        namaTugas.setText(bundle.getString("namaTugas"));
        descTugas.setText(bundle.getString("descTugas"));
        deadlineTugas.setText(bundle.getString("deadlineTugas"));
        setupDateButton();
        setupSubmitButton(bundle.getString("rowId"));
    }

    // Jika tombol setting tanggal ditap.
    private void setupDateButton(){
        Button datePicker  = (Button)findViewById(R.id.datePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                        deadlineTugas.setText(dateFormatter.format(newDate.getTime()).toUpperCase());
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    // Jika tombol sumbit ditap.
    private void setupSubmitButton(final String srowId){
        Button btn = (Button)findViewById(R.id.submitData);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract data.
                int rowId = Integer.parseInt(srowId);
                namaTugas = (EditText)findViewById(R.id.txNamaTugas);
                descTugas = (EditText)findViewById(R.id.txDescTugas);
                String sNamaTugas  = namaTugas.getText().toString();
                String sDescTugas  = descTugas.getText().toString();
                String sDateTugas  = deadlineTugas.getText().toString();
                // Pass data.
                if(sNamaTugas.equals("") || sDescTugas.equals("") || sDateTugas.equals("")){
                    Toast.makeText(DetailTask.this,"Data task kurang lengkap.",Toast.LENGTH_SHORT).show();
                } else {
                    db.open();
                    db.updateTask(rowId, sNamaTugas, sDescTugas, sDateTugas);
                    db.close();
                    Toast.makeText(DetailTask.this,"Update data berhasil!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailTask.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
