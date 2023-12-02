package edu.utsa.cs3443.lockit_v2;
/**
 * EditNoteActivity
 *
 * Editing an existing note, retrieves and displays the notes title and content.
 * Uses NoteController for saving of the edited
 * saveNoteToFile for writing the note to a file.
 * An encryption view is to display the encrypted of the note using the Crypto class.
 *
 *
 *
 *
 * */
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lockit_v2.controller.NoteController;
import edu.utsa.cs3443.lockit_v2.model.Crypto;
import edu.utsa.cs3443.lockit_v2.model.Note;
import edu.utsa.cs3443.lockit_v2.model.Notes;

import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;
import java.io.File;
import android.util.Log;
/**
 *  EditNoteActivity
 *  This is for editing and saving a specific note.
 *  It  will show and display the note and content, allows editing and then saves the edited note .
 */
public class EditNoteActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText contentInput;
    /**
     * Initializes activity, sets up the UI, for note editing.
     * gets the note by its ID, sets up the text fields, and save button.
     * @param savedInstanceState The save the application state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Activity initialization and UI setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Load intent stuff
        Intent intent = getIntent();
        String noteid = intent.getStringExtra("NOTEID");

        Note note = Notes.getNoteById(noteid);

        titleInput = findViewById(R.id.editNoteTitle);
        titleInput.setText(note.getTitle());

        contentInput = findViewById(R.id.editNoteDescription);
        contentInput.setText(note.getContent());

        ImageButton saveButton = findViewById(R.id.saveNoteButton);
        saveButton.setOnClickListener(view -> {
            saveNoteToFile();
            new NoteController(noteid).onClick(view); //
        });

        TextView encView = findViewById(R.id.encView);
        encView.setText(Crypto.encrypt(note.getContent(), Notes.password));
    }

    //OLD CODE THAT KIND OF WORKS BUT NOT REALLY GOOD
    /**
     * private void saveNoteToFile() {
     * String filename = "notes_data.txt";
     * String noteContent = contentInput.getText().toString();
     * FileOutputStream outputStream;
     * <p>
     * try {
     * outputStream = openFileOutput(filename, MODE_APPEND);
     * outputStream.write((noteContent + "\n").getBytes());
     * outputStream.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */

    /**
     * Saves the edited note to a file .
     * The file is named after the note title in enternal file directory.
     */
    private void saveNoteToFile() {
        // Get the note title and content
        String noteTitle = titleInput.getText().toString();
        String noteContent = contentInput.getText().toString();

        // Replace spaces underscores and and add the .txt extension
        String filename = noteTitle.replace(" ", "_") + ".txt";

        // Use the external storage directory for saving the note
        File file = new File(getExternalFilesDir(null), filename);

        try {
            // Open a file output stream for writing the note content
            FileOutputStream fos = new FileOutputStream(file, false); // Set false to overwrite the file
            fos.write(noteContent.getBytes());
            fos.close();
            Log.i("EditNoteActivity", "Note saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("EditNoteActivity", "File write failed", e);
        }
    }

}
