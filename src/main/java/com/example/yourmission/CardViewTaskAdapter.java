package com.example.yourmission;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CardViewTaskAdapter extends RecyclerView.Adapter<CardViewTaskAdapter.CardViewViewHolder> {
    private List<Task> listTask;
    private final LayoutInflater mInflater;

    void setListTask(List<Task> task) {
        listTask = task;
        notifyDataSetChanged();
    }

    CardViewTaskAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, final int position) {

        final Task task = listTask.get(position);
        holder.cardView.setBackgroundColor(Color.rgb(255,255,255));
        holder.namaTugas.setText(task.getTaskName());
        holder.descTugas.setText(task.getDescTask());
        holder.tanggalDeadline.setText(task.getTanggal());
        holder.bulanDeadline.setText(task.getBulan());
        holder.tahunDeadline.setText(task.getTahun());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lokasi Passing Data
                Bundle bundle = new Bundle();
                bundle.putLong("rowId", task.getRowId());
                bundle.putString("namaTugas", task.getTaskName());
                bundle.putString("descTugas", task.getDescTask());
                bundle.putString("deadlineTugas", task.getTanggal() + "-" + task.getBulan() + "-" + task.getTahun());
                Intent moveIntent = new Intent(v.getContext(), DetailTask.class);
                moveIntent.putExtras(bundle);
                v.getContext().startActivity(moveIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView namaTugas, descTugas, tanggalDeadline, bulanDeadline, tahunDeadline;
        CardView cardView;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView        = itemView.findViewById(R.id.cardView);
            namaTugas       = itemView.findViewById(R.id.namaTugas);
            descTugas       = itemView.findViewById(R.id.descTugas);
            tanggalDeadline = itemView.findViewById(R.id.tanggalDeadline);
            bulanDeadline   = itemView.findViewById(R.id.bulanDeadline);
            tahunDeadline   = itemView.findViewById(R.id.tahunDeadline);
        }
    }
}
