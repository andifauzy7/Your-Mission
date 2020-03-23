package com.example.yourmission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    // Kamus Data.
    public static final int REQUEST_CODE_ADD = 1;
    DBAdapter db = new DBAdapter(this);
    private ArrayList<Task> list = new ArrayList<>();
    FloatingActionButton addButton;
    private RecyclerView recyclerView;
    CardViewTaskAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectAllTaskFromDatabase();
        // Menampilkan Card View.
        recyclerView = findViewById(R.id.rv_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new CardViewTaskAdapter(list, MainActivity.this);
        recyclerView.setAdapter(Adapter);
        // Swipe to Delete.
        deleteData();
        // Tap tombol add.
        setupAddButton();
    }

    // Swipe delete
    private void deleteData(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Integer aRowId = Integer.parseInt(list.get(position).getRowId());
                final String aTaskName = list.get(position).getTaskName();
                final String aTaskDesc = list.get(position).getDescTask();
                final String aTaskDate = list.get(position).getsDateTask();
                list.remove(position);
                Toast.makeText(MainActivity.this, "Item Removed" + position, Toast.LENGTH_SHORT).show();
                Adapter.notifyItemRemoved(position);
                db.open();
                db.deleteContact(aRowId);
                db.close();
                Snackbar.make(recyclerView, aTaskName, Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    list.add(position, new Task(aTaskName, aTaskDesc, aTaskDate));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                db.open();
                                db.insertTask(aRowId.toString(), aTaskName, aTaskDesc, aTaskDate);
                                db.close();
                                Adapter.notifyItemInserted(position);
                            }
                        }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    // Jika tombol Add task di tap.
    private void setupAddButton(){
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, addTask.class);
                startActivityForResult(moveIntent, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD:
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
                    // Coba ke DB.
                    insertTaskToDatabase(aTask);
                    selectAllTaskFromDatabase();
                    Adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Data berhasil disimpan.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Tambah Task dibatalkan.", Toast.LENGTH_LONG).show();
                }
        }
    }

    // Insert data Task ke dalam Database.
    private void insertTaskToDatabase(Task aTask){
        db.open();
        long id = db.insertTask(aTask.getTaskName(), aTask.getDescTask(), aTask.getsDateTask());
        db.close();
    }

    // Select semua data dari Database.
    private void selectAllTaskFromDatabase(){
        db.open();
        Cursor c = db.getAllTask();
        if(c.moveToFirst()){
            do {
                try {
                    list.add(new Task(c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while (c.moveToNext());
        }
        db.close();
    }

}
