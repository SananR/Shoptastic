package me.shoptastic.app.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.StoresAdapter;

public class StoresActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        this.recyclerView = findViewById(R.id.storeRecyclerView);

        StoresAdapter adapter = new StoresAdapter(this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}