package com.autodesk.customspinner.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.autodesk.customspinner.CustomSpinner;
import com.autodesk.customspinner.CustomSpinnerAdapter;
import com.autodesk.customspinner.SpinnerDropDownItem;
import com.autodesk.customspinner.ViewBinder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomSpinner spinner = (CustomSpinner) findViewById(R.id.spinner);

        final List<Item> items = Arrays.asList(
                new Item("First", "Description"),
                new Item("Second", "Second Second Second description"),
                new Item("Third", "Third Description"),
                new Item("Forth", "Forth-description"),
                new Item("Fifth", "Fifth Description"),
                new Item("Some longer title", "Some longer description")
        );

        CustomSpinnerAdapter<Item> adapter = new CustomSpinnerAdapter<>(R.layout.spinner_dropdown_item,
                new ViewBinder<Item>() {
                    @Override
                    public void bindView(View view, Item item) {
                        final TextView title = (TextView) view.findViewById(R.id.item_title);
                        title.setText(item.spinnerTitle());
                        final TextView description = (TextView) view.findViewById(R.id.item_description);
                        description.setText(item.description());
                    }
                });

        adapter.addAll(items);

        spinner.setAdapter(adapter);
    }

    private class Item implements SpinnerDropDownItem {

        private final String mTitle;
        private final String mDescription;

        private Item(String title, String description) {
            this.mTitle = title;
            this.mDescription = description;
        }

        @Override
        public String spinnerTitle() {
            return this.mTitle;
        }

        public String description() {
            return this.mDescription;
        }
    }
}
