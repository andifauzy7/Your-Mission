package com.example.yourmission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GETMESSAGE = 1;

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
                startActivityForResult(moveIntent, REQUEST_CODE_GETMESSAGE);
                //startActivity(moveIntent);
            }
        });
    }

    private void showRecyclerCardView(){
        rvTask.setLayoutManager(new LinearLayoutManager(this));
        CardViewTaskAdapter cardViewHeroAdapter = new CardViewTaskAdapter(list);
        rvTask.setAdapter(cardViewHeroAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_CODE_GETMESSAGE:
                if(resultCode==Activity.RESULT_OK){
                    Toast.makeText(this, "Mantul",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Mencet tombol back",Toast.LENGTH_LONG).show();
                }
        }
    }
}
