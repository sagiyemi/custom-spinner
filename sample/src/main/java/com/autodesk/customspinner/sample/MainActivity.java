package com.autodesk.customspinner.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.autodesk.customspinner.CustomSpinner;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomSpinner spinner = (CustomSpinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
//        adapter.addAll(Arrays.asList("Only one item"));
        adapter.addAll(Arrays.asList("First", "Second", "Third", "Forth", "Fifth", "Some longer title"));
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
