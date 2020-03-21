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
import java.text.ParseException;
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
        try {
            list.addAll(TaskData.getListData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        showRecyclerCardView();
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, addTask.class);
                startActivityForResult(moveIntent, REQUEST_CODE_GETMESSAGE);
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
        switch (requestCode) {
            case REQUEST_CODE_GETMESSAGE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String namaTugas = bundle.getString("namaTugas");
                    String descTugas = bundle.getString("descTugas");
                    String dateTugas = bundle.getString("dateTugas");
                    Task aTask = null;
                    try {
                        aTask = new Task(namaTugas, descTugas, dateTugas);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // Refresh ulang isi Card.
                    list.add(aTask);
                    showRecyclerCardView();

                    // Coba ke DB.
                    DBAdapter db = new DBAdapter(this);
                    db.open();
                    long id = db.insertTask(namaTugas, descTugas, dateTugas);
                    db.close();

                    Toast.makeText(this, "Data berhasil disimpan.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Tambah Task dibatalkan.", Toast.LENGTH_LONG).show();
                }
        }
    }
}
