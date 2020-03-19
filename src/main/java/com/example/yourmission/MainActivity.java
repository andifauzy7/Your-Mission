package com.example.yourmission;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    private RecyclerView rvTask;
    private ArrayList<Task> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTask = findViewById(R.id.rv_task);
        rvTask.setHasFixedSize(true);

        list.addAll(TaskData.getListData());
        showRecyclerCardView();

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, addTask.class);
                startActivity(moveIntent);
            }
        });
    }

    private void showRecyclerCardView(){
        rvTask.setLayoutManager(new LinearLayoutManager(this));
        CardViewTaskAdapter cardViewHeroAdapter = new CardViewTaskAdapter(list);
        rvTask.setAdapter(cardViewHeroAdapter);
    }

}
