package com.cs151.wordnotfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<Word> {
    public GridAdapter(@NonNull Context context, ArrayList<Word> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.letter, parent, false);
        }
        Word lettor = getItem(position);
        TextView text = listitemView.findViewById(R.id.let);
        text.setText(lettor.getWord());
        /*text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(text.getText());
            }
        });*/
        return listitemView;
    }
}