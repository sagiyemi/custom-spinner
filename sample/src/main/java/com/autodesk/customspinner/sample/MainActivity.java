package com.autodesk.customspinner.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.autodesk.customspinner.CustomSpinner;
import com.autodesk.customspinner.SpinnerDropDownItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomSpinner spinner = (CustomSpinner) findViewById(R.id.spinner);
        ArrayAdapter<SpinnerDropDownItem> adapter = new DescriptionAdapter<>(this, R.layout.spinner_dropdown_item);
//        adapter.addAll(Arrays.asList("Only one item"));
        adapter.addAll(Arrays.asList(
                new Item("First", "Description"),
                new Item("Second", "Second Second Second description"),
                new Item("Third", "Third Description"),
                new Item("Forth", "Forth-description"),
                new Item("Fifth", "Fifth Description"),
                new Item("Some longer title", "Some longer description")));
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private class Item implements SpinnerDropDownItem {

        private final String mTitle;
        private final String mDescription;

        private Item(String title, String description) {
            this.mTitle = title;
            this.mDescription = description;
        }

        @Override
        public String title() {
            return this.mTitle;
        }

        @Override
        public String description() {
            return this.mDescription;
        }
    }

    private class DescriptionAdapter<T extends SpinnerDropDownItem> extends ArrayAdapter<T> {

        public DescriptionAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.spinner_dropdown_item, null);
            final T item = getItem(position);
            final TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(item.title());
            final TextView description = (TextView) view.findViewById(R.id.item_description);
            description.setText(item.description());

            return view;
        }
    }
}
