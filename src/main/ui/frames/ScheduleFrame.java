package ui.frames;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.AddableScheduleEntry;
import model.Plant;
import model.Schedule;
import ui.GroApplicationGUI;
import ui.WeekDaysName;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * GUI for Watering Schedule
 * Source(s): https://stackoverflow.com/questions/21419283/java-gridbaglayout-resizing-and-fixed-size
 */

public class ScheduleFrame {

    private JPanel mainPanel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;

    private JFrame mainFrame;

    private JButton addPlantButton;
    private JButton removePlantButton;
    private JButton quitScheduleButton;
    private JButton refreshButton;

    // EFFECTS: Constructor for a new ScheduleFrame
    public ScheduleFrame() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                mainPanel = new JPanel();
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                //P1: Add 7 text areas, each corresponding to a day
                p1 = new JPanel();
                setUpPanelOne(p1);

                //P2: Add 4 buttons; add plant, remove plant, return to previous page, and quit application
                p2 = new JPanel();
                setUpPanelTwo(p2);

                //P3: Add a spacer
                p3 = new JPanel();
                setUpPanelThree(p3);

                //ADD component panels to mainPanel
                addPanelOneToMainPanel(p1, c);
                addPanelTwoToMainPanel(p2, c);
                addPanelThreeToMainPanel(p3, c);

                //Final frame setup
                mainFrame = new JFrame("GRO Watering Schedule");
                setUpFrame(mainFrame, mainPanel);
            }
        });
    }

    // EFFECTS: setup, format, add 7 text areas corresponding to days to panel 1
    private void setUpPanelOne(JPanel p1) {
        p1.setLayout(new GridLayout(1, 7));
        Font font = new Font("Julius Sans One", Font.ITALIC, 20);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();

        for (int i = 0; i < 7; i++) {
            createWeekDay(new JTextArea(), WeekDaysName.values()[i].name(), i, font, raisedBevel);
        }

    }

    // Old code:
//    monday = new JTextArea();
//    monday..setBorder(raisedBevel);
//    monday.setText("monday" + "\n");
//    monday.setFont(font);
//    monday.setEditable(false);
//
//
//    tuesday = new JTextArea();
//    etc.
//
//    wednesday = new JTextArea();
//    etc.
//
//    thursday = new JTextArea();
//    etc.
//
//    friday = new JTextArea();
//    etc.
//
//    saturday = new JTextArea();
//    etc.
//
//    sunday = new JTextArea();
//    etc.

    // EFFECTS: Creates a text area that corresponds to a weekday
    private void createWeekDay(JTextArea weekDayTextArea, String nameOfDay,
                               int indexOfDay,
                               Font font,
                               Border raisedBevel) {
        ObjectMapper objectMapper = new ObjectMapper();
        weekDayTextArea.setBorder(raisedBevel);
        weekDayTextArea.setText(nameOfDay + "\n");
        weekDayTextArea.setFont(font);
        weekDayTextArea.setEditable(false);

        Schedule schedule = null;
        try {
            schedule = objectMapper.readValue(new File("./data/schedule.json"), Schedule.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            //Get the ArrayList<Plant> from the JSON, with index corresponding to day
            assert schedule != null;

            ArrayList<? extends AddableScheduleEntry> plantList =
                    schedule.getListOfEntriesAtWeekDayFromJson(indexOfDay);

            // Print each plant in the plant list
            for (AddableScheduleEntry p : plantList) {
                weekDayTextArea.append(p.getName() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        p1.add(weekDayTextArea);
    }

    // EFFECTS: setup, format, add buttons to panel 2
    private void setUpPanelTwo(JPanel p2) {
        p2.setLayout(new GridLayout(4, 1));
        p2.setMinimumSize(new Dimension(150, 100));
        p2.setMaximumSize(new Dimension(150, 100));
        p2.setPreferredSize(new Dimension(150, 100));

        addPlantButton = new JButton("Add Plant");
        removePlantButton = new JButton("Remove Plant");
        refreshButton = new JButton("Refresh");
        quitScheduleButton = new JButton("Quit Program");

        addPlantButton.addActionListener(new ClickButton());
        removePlantButton.addActionListener(new ClickButton());
        refreshButton.addActionListener(new ClickButton());
        quitScheduleButton.addActionListener(new ClickButton());

        addPlantButton.setBackground(GroApplicationGUI.programColor);
        removePlantButton.setBackground(GroApplicationGUI.programColor);
        refreshButton.setBackground(GroApplicationGUI.programColor);
        quitScheduleButton.setBackground(GroApplicationGUI.programColor);

        p2.add(addPlantButton);
        p2.add(removePlantButton);
        p2.add(refreshButton);
        p2.add(quitScheduleButton);
    }

    // EFFECTS: setup panel 3
    private void setUpPanelThree(JPanel p3) {
        p3.setMinimumSize(new Dimension(100, 150));
        p3.setMaximumSize(new Dimension(100, 150));
        p3.setPreferredSize(new Dimension(100, 150));
        p3.setBackground(GroApplicationGUI.programColor);
    }

    // EFFECTS: adds panel 1 to the main panel
    private void addPanelOneToMainPanel(JPanel p, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(p1, c);
    }

    // EFFECTS: adds panel 2 to the main panel
    private void addPanelTwoToMainPanel(JPanel p, GridBagConstraints c) {
        c.gridx = 1;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(p2, c);
    }

    // EFFECTS: adds panel 3 to the main panel
    private void addPanelThreeToMainPanel(JPanel p, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        mainPanel.add(p3, c);

    }

    // EFFECTS: set up the frame containing the main panel
    private void setUpFrame(JFrame frame, JPanel mainPanel) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setIconImage(GroApplicationGUI.logo);
        frame.setVisible(true);
    }

    // EFFECTS: detect button clicks, triggering an action based on the button
    public class ClickButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent submitClicked) {

            if (addPlantButton == (submitClicked.getSource())) {
                new AddPlantFrame();

            } else if (removePlantButton == (submitClicked.getSource())) {
                Component frame = new JFrame();
                new RemovePlantFrame();

            } else if (refreshButton == (submitClicked.getSource())) {
                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Updating Schedule...");
                mainFrame.dispose();
                new ScheduleFrame();

            } else if (quitScheduleButton == (submitClicked.getSource())) {
                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Closing GRO!");
                System.exit(0);
            }
        }
    }

}