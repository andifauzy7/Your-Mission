package com.example.yourmission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailTask extends AppCompatActivity {
    TextView title, desc, dateDeadline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Detail Mission");
        // Passing Data Bundle diterima dari CardViewTaskAdapter
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        title = (TextView)findViewById(R.id.namaTugas);
        title.setText(bundle.getString("namaTugas"));
        desc = (TextView)findViewById(R.id.descTugas);
        desc.setText(bundle.getString("descTugas"));
        dateDeadline = (TextView)findViewById(R.id.deadlineTugas);
        dateDeadline.setText(bundle.getString("deadlineTugas"));
    }
}
