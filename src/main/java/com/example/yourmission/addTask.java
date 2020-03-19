package com.example.yourmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Mission");

        Button submitData = findViewById(R.id.submitData);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }
}
