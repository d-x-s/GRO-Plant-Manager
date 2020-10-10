package ui.frames;

/**
 * The frame displayed to a saved user, with option to load a save or reset the program
 *
 * Source(s): https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/
 *            src/layout/GridBagLayoutDemo.java
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.GroApplicationGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

public class WelcomeSavedUserFrame {
    private static final boolean shouldFill = true;
    private static final boolean shouldWeightX = true;
    private static final boolean RIGHT_TO_LEFT = false;

    JButton button1;
    JButton button2;
    JFrame frame;
    Border raisedBevel;

    // EFFECTS: constructor for WelcomeSavedUserFrame that starts the frmae
    public WelcomeSavedUserFrame() {
        run();
    }

    // EFFECTS: adds components to the pane
    public void addComponentsToPane(Container pane) {

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        pane.setBackground(GroApplicationGUI.programColor);
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        // Welcome message
        JTextArea welcome;
        welcome = new JTextArea("\n" + "                 Welcome back to GRO, " + getUserName() + "!");
        createWelcomeMessage(c, pane, welcome);

        // Button to load the saved program
        button1 = new JButton("Load Saved Program");
        button1.addActionListener(new ClickButton());
        createSaveButton(c, pane, button1);

        // Button to reset the program
        button2 = new JButton("Reset Program");
        button2.addActionListener(new ClickButton());
        createResetButton(c, pane, button2);

    }

    // EFFECTS: setup all componenets contained within the window and start the window
    private void run() {
        //Create and set up the window.
        frame = new JFrame("GRO APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(500, 280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Set the logo
        Image icon = Toolkit.getDefaultToolkit().getImage("./data/Leaf-Transparent-Background.png");
        frame.setIconImage(icon);

    }

    // EFFECT: format and create the button that loads in a saved schedule
    private void createSaveButton(GridBagConstraints c, Container pane, JButton button) {

        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 40; //make this component tall
        c.gridwidth = 3; //take up 3 grid spaces

        //button style
        button.setBackground(GroApplicationGUI.programColor);
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        button.setBorder(raisedBevel);

        pane.add(button, c);

    }

    // EFFECTS: format and create the button that resets the program
    public void createResetButton(GridBagConstraints c, Container pane, JButton button) {

        // spacing and size
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;    //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3; //take up 3 grid spaces
        c.gridx = 0;
        c.gridy = 2;

        // button style
        raisedBevel = BorderFactory.createRaisedBevelBorder();
        button.setBorder(raisedBevel);
        button.setBackground(GroApplicationGUI.programColor);

        pane.add(button, c);

    }

    // EFFECTS: creates a JTextArea with a welcome message
    private static void createWelcomeMessage(GridBagConstraints c, Container pane, JTextArea welcome) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;    //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3; //take up 3 grid spaces
        c.gridx = 0;
        c.gridy = 0;

        // style
        welcome.setFont(welcome.getFont().deriveFont(20f));

        pane.add(welcome, c);
    }

    // EFFECTS: returns the username stored in file
    private static String getUserName() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("./data/userName.json"), String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // EFFECTS: close frame
    private void closeFrame() {
        frame.dispose();
    }

    // EFFECTS: detects when a button has been clicked, triggering an action depending on the button
    public class ClickButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent submitClicked) {

            if (button1 == (submitClicked.getSource())) {
                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Now loading the GRO Watering Schedule.");
                closeFrame();

                new ScheduleFrame();

            } else if (button2 == (submitClicked.getSource())) {
                ObjectMapper objectMapper = new ObjectMapper();
                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Now clearing your saved files. Please restart GRO!");

                try {
                    objectMapper.writeValue(new File("./data/firstTimeUser.json"), true);
                    objectMapper.writeValue(new File("./data/schedule.json"), null);
                    objectMapper.writeValue(new File("./data/userName.json"), null);
                    System.exit(0);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}



