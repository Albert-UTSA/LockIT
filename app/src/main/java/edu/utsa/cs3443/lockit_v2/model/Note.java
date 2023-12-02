package edu.utsa.cs3443.lockit_v2.model;
/***
 *
 * Note
 *
 * represents a single note, encapsulating details such as its ID, title, content, creation date, and a flag indicating if it's starred.
 * It includes methods for getting and setting attributes,
 * with the ID being automatically generated upon UUID.
 *
 * */
import java.util.Date;
import java.util.UUID;
/**
 * The Note class represents an individual note with a unique ID, title, content, and creation date.
 * It allows for manipulation and retrieval of these note properties, including a starred status.
 */
public class Note {
    private String id;
    private String title;
    private String content;
    private Date date;

    private boolean starred;

    /**
     * Constructs a new Note with the specified name, content, and creation date.
     * The notes unique ID is automatically generated.
     *
     * @param title   The title of the note.
     * @param content contents of the note.
     * @param date    date of the note.
     */
    public Note(String title, String content, Date date) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.date = date;
        this.starred = false;
    }

    //Getters and setters

    /**
     * Returns ID of the note.
     * @return The note's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the note.
     * @return The notes name/title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the contents of the note.
     *
     * @return The notes content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns date of the note.
     *
     * @return The notes date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the title of the note.
     * @param title The title for the note.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the note.
     * @param content The new content for the note.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the date of the note.
     * @param date The new date for the note.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}