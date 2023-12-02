package edu.utsa.cs3443.lockit_v2.controller;

/**
 * The NotesController class implements the View.OnClickListener interface
 * to handle the opening of a note for editing upon a click event.
 */


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lockit_v2.EditNoteActivity;
import edu.utsa.cs3443.lockit_v2.R;

public class NotesController implements  View.OnClickListener {

    /**
     * Invoked when a view associated with this listener is clicked.
     *
     * @param view The view that was clicked
     */
    public void onClick(View view) {

        // Find the TextView with the ID idView within the clicked view
        TextView idView = view.findViewById(R.id.idView);

        // Get the current activity as an AppCompatActivity from the clicked view's context
        AppCompatActivity curActivity = (AppCompatActivity) view.getContext();

        // Create an intent to start the EditNoteActivity and pass the note ID as extra data
        Intent intent = new Intent(view.getContext(), EditNoteActivity.class);
        intent.putExtra("NOTEID", idView.getText());

        // Starting the activity with new intent
        curActivity.startActivity(intent);
    }
}