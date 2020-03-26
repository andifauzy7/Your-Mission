package com.example.yourmission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity{
    // Kamus Data.
    List<Task> cloneTasks;
    private TaskViewModel mTaskViewModel;
    public static final int REQUEST_CODE_ADD = 1;
    FloatingActionButton addButton;
    private RecyclerView recyclerView;
    CardViewTaskAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Menampilkan Card View.
        recyclerView = findViewById(R.id.rv_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new CardViewTaskAdapter(this);
        recyclerView.setAdapter(Adapter);

        // Instansiasi ViewModel & Read Data dari DB.
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Adapter.setListTask(tasks);
                cloneTasks = tasks;

            }
        });

        // Swipe to Delete.
        deleteData();
        // Tap tombol add.
        setupAddButton();

    }

    // Swipe delete
    private void deleteData(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            // Memberi Warna saat animasi swipe.
            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Task aTask = cloneTasks.get(position);
                Task bTask = new Task();
                bTask.setRowId(aTask.getRowId());
                bTask.setTaskName(aTask.getTaskName());
                bTask.setDescTask(aTask.getDescTask());
                bTask.setTextDateTask(aTask.getTextDateTask());
                mTaskViewModel.delete(aTask);
                Toast.makeText(MainActivity.this, "Item Removed" + position, Toast.LENGTH_SHORT).show();
                Snackbar.make(recyclerView, bTask.getTaskName(), Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mTaskViewModel.insert(bTask);
                            }
                        }).show();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    // Jika tombol Add task di tap.
    private void setupAddButton(){
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            Intent moveIntent = new Intent(MainActivity.this, addTask.class);
            startActivityForResult(moveIntent, REQUEST_CODE_ADD);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Bundle bundle = data.getExtras();
                assert bundle != null;
                String namaTugas = bundle.getString("namaTugas");
                String descTugas = bundle.getString("descTugas");
                String dateTugas = bundle.getString("dateTugas");
                Task aTask = new Task();
                aTask.setTaskName(namaTugas);
                aTask.setDescTask(descTugas);
                aTask.setTextDateTask(dateTugas);
                // Coba ke DB.
                mTaskViewModel.insert(aTask);
                Toast.makeText(this, "SUKSES : Data berhasil disimpan.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "GAGAL : Tambah Task dibatalkan.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
