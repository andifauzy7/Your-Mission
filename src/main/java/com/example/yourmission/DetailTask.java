package com.example.yourmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailTask extends AppCompatActivity {
    public static final String ID_TASK="";
    private Integer idTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        this.idTask = Integer.parseInt(getIntent().getStringExtra(ID_TASK));

        TextView id = findViewById(R.id.id);
        id.setText(this.idTask.toString());
    }
}
