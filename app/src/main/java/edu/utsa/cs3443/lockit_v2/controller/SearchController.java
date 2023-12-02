package edu.utsa.cs3443.lockit_v2.controller;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.recyclerview.widget.RecyclerView;

import edu.utsa.cs3443.lockit_v2.adapter.NotesAdapter;
import edu.utsa.cs3443.lockit_v2.model.Notes;
/**
 *  Contains methods for seaching through notes by keywords.
 *
 */
public class SearchController implements TextWatcher {

    private RecyclerView recyclerView;

    public SearchController(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    /**
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String newText = charSequence.toString(); // Get the new text as a string
        this.recyclerView.setAdapter(new NotesAdapter(Notes.searchNotes(newText)));
    }

    /**
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {

    }
}
