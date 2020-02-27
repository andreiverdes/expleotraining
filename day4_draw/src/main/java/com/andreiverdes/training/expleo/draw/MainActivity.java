package com.andreiverdes.training.expleo.draw;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static int ROWS = 50;
    private static int COLUMNS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setAdapter(new CirclesAdapter(ROWS, COLUMNS));
        recycler.setLayoutManager(new GridLayoutManager(this,  COLUMNS));
    }
}
