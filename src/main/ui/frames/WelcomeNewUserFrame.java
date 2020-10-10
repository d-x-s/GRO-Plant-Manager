package ui.frames;

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.GroApplicationGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import static ui.GroApplication.initializeNewSchedule;

/**
 * Welcome message window for new users
 * <p>
 * Source(s):
 * https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as
 * -opposed-to-only-using-mo
 * <p>
 * http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
 */


public class WelcomeNewUserFrame extends JFrame {

    JTextField textBoxToEnterName;
    JButton submit;
    JTextField userInputKey;
    JTextArea textArea1;
    JTextArea display;

    // EFFECTS: constructor for a new WelcomeNewUserFrame
    public WelcomeNewUserFrame() {

        // Setup
        setLayout(new BorderLayout());
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("GRO APP");
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();

        // Set colors
        setPanelColors(topPanel, botPanel, mainPanel);

        // Username input
        JLabel enterYourName = new JLabel("Enter Your Name Here:");
        textBoxToEnterName = new JTextField("", 15);
        textBoxToEnterName.selectAll();
        topPanel.add(enterYourName);
        topPanel.add(textBoxToEnterName);

        // Button to submit
        submit = new JButton("Submit");
        submit.addActionListener(new SubmitButton(textBoxToEnterName));
        submit.addKeyListener(new SubmitButton(textBoxToEnterName));
        botPanel.add(submit);

        // Change default icon to GRO logo
        setLogo();

        // Wrap-up
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(botPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);

    }

    // EFFECTS: set the panel colors
    private void setPanelColors(JPanel top, JPanel bot, JPanel main) {
        top.setBackground(GroApplicationGUI.programColor);
        bot.setBackground(GroApplicationGUI.programColor);
        main.setBackground(GroApplicationGUI.programColor);
    }

    // EFFECTS: set the window icon to the GRO logo
    private void setLogo() {
        this.setIconImage(GroApplicationGUI.logo);
    }

    // EFFECTS: Detects button presses, plays a sound when the user submits their username
    class SubmitButton implements ActionListener, KeyListener {

        JTextField nameInput;

        public SubmitButton(JTextField textfield) {
            nameInput = textfield;
        }

        @Override
        public void actionPerformed(ActionEvent submitClicked) {
            ObjectMapper objectMapper = new ObjectMapper();
            Component frame = new JFrame();
            playSound("success.wav");
            JOptionPane.showMessageDialog(frame, "Your username is now saved as:  " + nameInput.getText());

            //save the username to JSON
            //save that the user is no longer a 'first time user'
            try {
                objectMapper.writeValue(new File("./data/userName.json"), nameInput.getText());
                objectMapper.writeValue(new File("./data/firstTimeUser.json"), false);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //initializes a new schedule
            initializeNewSchedule();
            closeFrame();

            new ScheduleFrame();

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent arg0) {

        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }
    }

    // EFFECTS: closes the frame
    private void closeFrame() {
        this.dispose();
    }

    // EFFECTS: retrieve WAV file and play it's sound
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("./data/" + soundName).getAbsoluteFile());

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}

