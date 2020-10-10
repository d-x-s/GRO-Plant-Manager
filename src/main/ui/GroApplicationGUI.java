package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.frames.WelcomeNewUserFrame;
import ui.frames.WelcomeSavedUserFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Center of control for GUI implementation
 */
public class GroApplicationGUI {

    // color for the entire program
    public static Color programColor = new java.awt.Color(146, 232, 132);
    public static Image logo = Toolkit.getDefaultToolkit().getImage("./data/Leaf-Transparent-Background.png");

    // EFFECTS: Start the GUI
    public GroApplicationGUI() {
        runGUI();
    }

    // MODIFIES: this
    // EFFECTS: user interface, display information and allow user to navigate program
    private void runGUI() {
        if (newOrSavedUser()) {
            new WelcomeNewUserFrame();
        } else {
            new WelcomeSavedUserFrame();
        }

    }

    // EFFECTS: identify if a user is new or returning
    private boolean newOrSavedUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("./data/firstTimeUser.json"), boolean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
