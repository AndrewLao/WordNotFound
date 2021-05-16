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

/**
 * Handles the position of letters in the grid
 */
public class GridAdapter extends ArrayAdapter<String> {
    public GridAdapter(@NonNull Context context, ArrayList<String> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    /**
     *
     * Sets the text of the textview for each letter in GridView
     *
     * @param position
     * position of letter
     * @param convertView
     * view of instance
     * @param parent
     * parent views
     * @return View
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.letter, parent, false);
        }
        String letter = getItem(position);
        TextView text = listitemView.findViewById(R.id.let);
        text.setText(letter);
        return listitemView;
    }
}