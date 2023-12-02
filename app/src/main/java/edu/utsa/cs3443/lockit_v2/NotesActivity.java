package edu.utsa.cs3443.lockit_v2;
/***
 *
 * NotesActivity
 *
 * Displays a list of notes
 * allows users to interact with the notes.
 * It loads notes from the file system and uses a RecyclerView to display them.
 * You can create a new note and search existing notes
 *
 * */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.utsa.cs3443.lockit_v2.adapter.NotesAdapter;
import edu.utsa.cs3443.lockit_v2.controller.NewNoteController;
import edu.utsa.cs3443.lockit_v2.model.Note;
import edu.utsa.cs3443.lockit_v2.model.Notes;

import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * NotesActivity class is responsible for displaying a list of notes in the application.
 * It loads notes from the file system, displays them using a RecyclerView, and gives options to add new notes and search existing ones.
 */
public class NotesActivity extends AppCompatActivity {

    private NotesAdapter notesAdapter;

    /**
     * Initializes the activity
     * sets up the RecyclerView for displaying notes
     * configures buttons for adding and searching notes.
     * Loads notes from the file system and displays them in the RecyclerView.
     * @param savedInstanceState The saves the state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        List<Note> loadedNotes = loadNotesFromFileSystem();
        Notes.notes.clear(); // Clear the existing notes
        Notes.notes.addAll(loadedNotes); // Add the loaded notes

        RecyclerView myNotesList = findViewById(R.id.myNotesContainer);
        myNotesList.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(Notes.getNotes());
        myNotesList.setAdapter(notesAdapter);

        Button newNoteButton = findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(new NewNoteController());

        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  when button is clicked
                Notes.populate();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * Loads notes from the file system and returns them as a list.
     * Reads note files from storage and creates a Note object for each file.
     *
     * @return A list of Note objects loaded from the file system.
     */
    private List<Note> loadNotesFromFileSystem() {
        List<String> noteFiles = listAllNoteFiles();
        List<Note> loadedNotes = new ArrayList<>();

        for (String filename : noteFiles) {
            Note note = readNoteFromFile(filename);
            if (note != null) {
                loadedNotes.add(note);
            }
        }

        return loadedNotes;
    }
    /**
     * @return A Note object with the content read from the file, or null if there is an error or no file
     * reads the file also.
     */
    private Note readNoteFromFile(String filename) {
        File file = new File(getExternalFilesDir(null), filename);

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder contentBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }

            reader.close();

            // Strip the .txt extension from the filename for the note title
            String noteTitle = filename.substring(0, filename.lastIndexOf('.'));

            Date currentDate = new Date();

            // Creates a new Note object with title and content
            Note note = new Note(filename, noteTitle, currentDate);

            //
            note.setContent(contentBuilder.toString().trim());

            return note;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * Lists all note files stored.
     * Filters files with a .txt extension.
     * @return A list of filenames .
     */
    private List<String> listAllNoteFiles() {
        File directory = getExternalFilesDir(null);
        List<String> fileList = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    fileList.add(file.getName());
                }
            }
        }

        return fileList;
    }

    /**
     * Updates the display of notes in the RecyclerView.
     * Clears existing notes
     * loads new ones
     */
    // Calls this method when dataset changes
    public void updateNotesDisplay() {
        List<Note> newNotes = loadNotesFromFileSystem();
        Notes.notes.clear(); // Clear the existing notes
        Notes.notes.addAll(newNotes); // Add the new notes
        notesAdapter.notifyDataSetChanged();
    }

}
