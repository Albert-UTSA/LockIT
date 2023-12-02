package edu.utsa.cs3443.lockit_v2;
/**
 *
 * SearchActivity
 *
 * This class is used for searching notes
 * With the search button to display search note results.
 *
 * */
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.utsa.cs3443.lockit_v2.controller.SearchController;

/**
 * SearchActivity provides a user interface for searching notes.
 * It displays the list of search results using a RecyclerView.
 */
public class SearchActivity extends AppCompatActivity {

    /**
     * Initializes the activity and sets up the search input field
     * and the RecyclerView for displaying search results.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView myNotesList = findViewById(R.id.searchNotesContainer);
        myNotesList.setLayoutManager(new LinearLayoutManager(this));
//        myNotesList.setAdapter(new NotesAdapter(Notes.searchNotes()));


        EditText editText = findViewById(R.id.searchEditText);

        editText.addTextChangedListener(new SearchController(myNotesList));

    }
}