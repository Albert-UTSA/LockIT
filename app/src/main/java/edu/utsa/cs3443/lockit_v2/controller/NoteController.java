package edu.utsa.cs3443.lockit_v2.controller;

/**
 * NoteController
 *
 * handles click events for editing a note,
 * identified by its unique ID.
 * it updates the title, content, and date of a note,
 * and then goes back to the NotesActivity.
 *
 *
 * */

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import edu.utsa.cs3443.lockit_v2.NotesActivity;
import edu.utsa.cs3443.lockit_v2.R;
import edu.utsa.cs3443.lockit_v2.model.Note;
import edu.utsa.cs3443.lockit_v2.model.Notes;

/**
 * NoteController
 * implements OnClickListener to handle click events
 * identified by its ID.
 */
public class NoteController implements  View.OnClickListener {


    private String id;

    /**
     * Constructs a NoteController with a specified note ID.
     * @param id The ID of the note.
     */
    public NoteController(String id) {
        this.id = id;
    }

    /**
     * Handles click events by updating the title, content, and date of the targeted note
     * and then goes to the NotesActivity.
     */
    public void onClick(View view) {

        AppCompatActivity curActivity = (AppCompatActivity) view.getContext();

        EditText editNoteTitle = curActivity.findViewById(R.id.editNoteTitle);
        EditText editNoteContent = curActivity.findViewById(R.id.editNoteDescription);

        ArrayList<Note> notesList = Notes.notes;

        for (Note note : notesList) {
            if (note.getId().equals(this.id)) {
                // Modify the title and content of the Note object
                note.setTitle(editNoteTitle.getText().toString());
                note.setContent(editNoteContent.getText().toString());
                note.setDate(Calendar.getInstance().getTime());
                break; // Break out of the loop once the target Note object is found
            }
        }

        // Create an intent to start the NotesActivity
        Intent intent = new Intent(view.getContext(), NotesActivity.class);

        curActivity.startActivity(intent);
    }
}