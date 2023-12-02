package edu.utsa.cs3443.lockit_v2.model;

/**
 * The Notes class
 *
 * Manages a collection of Note objects,
 * providing static methods to add, remove, search, and retrieve notes.
 * Allows for operations like searching notes by text and fetching a note by its unique ID.
 *
 * */


import java.util.ArrayList;
import java.util.Calendar;
import edu.utsa.cs3443.lockit_v2.model.Note;
import edu.utsa.cs3443.lockit_v2.model.Notes;


/**
 * The {@code Notes} class represents a collection of objects.
 * It provides methods to manipulate and retrieve notes.
 */
public class Notes {
    public static ArrayList<Note> notes = new ArrayList<>();
    public static String password;

    /**
     * Populates the notes collection with this data.
     */
    public static void populate() {
        addNote(new Note("Welcome to BlackBox", "Welcome to Blackbox. This is an app that lets you encrypt your notes.", Calendar.getInstance().getTime()));
        addNote(new Note("How to use", "To save changes tap the save icon in the right hand corner.\nTo remove a note tap the red \"X\" icon next to the save button. ", Calendar.getInstance().getTime()));

    }

    /**
     * Retrieves the list of notes.
     * @return ArrayList of { Note}.
     */
    public static ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * Adds a new note to the notes collection.
     * @param note The { Note}.
     */
    public static void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes a note from the notes collection.
     * @param note The { Note} object to be removed.
     */
    public static void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Retrieves a note by its ID.
     * @param id The ID of the note.
     * @return The specified ID, or if not found.
     */
    public static Note getNoteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    /**
     * Searches for notes containing the specified text in their content or title.
     * @param searchText The text to search for within note contents and titles.
     * @return ArrayList of {@code Note} objects that match the search criteria.
     */
    public static ArrayList<Note> searchNotes(String searchText) {
        ArrayList<Note> foundNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getContent().contains(searchText)) {
                foundNotes.add(note);
                continue;
            }

            if (note.getTitle().contains(searchText)) {
                foundNotes.add(note);
            }
        }
        return foundNotes;
    }


}
