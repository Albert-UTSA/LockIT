package edu.utsa.cs3443.lockit_v2.controller;
/**
 * The NewNoteController
 *
 * class implements the View.OnClickListener interface
 * to handle the creation of a new note upon a click event.
 */
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import edu.utsa.cs3443.lockit_v2.EditNoteActivity;
import edu.utsa.cs3443.lockit_v2.R;
import edu.utsa.cs3443.lockit_v2.model.Note;
import edu.utsa.cs3443.lockit_v2.model.Notes;

public class NewNoteController implements  View.OnClickListener {

    /**
     * Invoked when a view associated with this listener is clicked.
     * @param view The view that was clicked
     */
    public void onClick(View view) {

        // Find the TextView with the ID idView within the clicked view
        TextView idView = view.findViewById(R.id.idView);

        // Get the current activity as an AppCompatActivity from the clicked view's context
        AppCompatActivity curActivity = (AppCompatActivity) view.getContext();

        // Create a new Note with default values and the current timestamp and add it to the Notes collection.
        Note newNote = new Note("Untitled Note", "  Enter your content here.", Calendar.getInstance().getTime());
        Notes.addNote(newNote);

        // Create an intent to start the EditNoteActivity and pass the new note's ID as extra data
        Intent intent = new Intent(view.getContext(), EditNoteActivity.class);
        intent.putExtra("NOTEID", newNote.getId());

        // Starts the activity with the new intent
        curActivity.startActivity(intent);
    }
}