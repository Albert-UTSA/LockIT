package edu.utsa.cs3443.lockit_v2;
/**
 *
 * MainActivity
 *
 * This is where we login at the start of the application.
 * It checks for password LockIT123 at login it
 * initializes the Notes class and navigates to the NotesActivity.
 * This activity sets up a user interface with a login button and password input field,
 * Also will display a toast message for incorrect password entries.
 *
 *
 * */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lockit_v2.model.Notes;

/**
 * MainActivity class
 * It provides a login interface and validates the user password.
 */
public class MainActivity extends AppCompatActivity {

    private static final String HARDCODED_PASSWORD = "LockIT123"; //HARDCORDED password only you use your phone so it works
    /**
     * Initializes activity with a login interface.
     * Sets up the login button and its click listener to validate password.
     * @param savedInstanceState The saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Login button click event handling
                EditText passwordInput = findViewById(R.id.passwordInput);
                String enteredPassword = passwordInput.getText().toString();

                if (!enteredPassword.equals(HARDCODED_PASSWORD)) {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If password is correct
                Notes.password = enteredPassword;
                Notes.populate();

                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }
}
