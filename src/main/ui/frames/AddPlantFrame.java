package ui.frames;

import java.awt.*;

import exceptions.InvalidDayException;
import model.Plant;
import model.Schedule;
import persistence.ManagerJson;
import ui.GroApplicationGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class AddPlantFrame {

    private JFrame mainFrame;

    private JPanel mainPanel;
    private JPanel p1;
    private JPanel p2;

    private JTextField textFieldPlantName;
    private JComboBox<String> listOfWeekDay;

    private JButton addPlantSubmitButton;

    // EFFECTS: constructor for new plant adder frame
    public AddPlantFrame() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                //instantiate main panel
                mainPanel = new JPanel();
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                //setup p1
                p1 = new JPanel();
                setUpPanelOne(p1);

                //setup p2
                p2 = new JPanel();
                setUpPanelTwo(p2);

                //add p1 panel to mainPanel
                addPanelOneToMainPanel(p1, c);

                //add p2 panel to mainPanel
                addPanelTwoToMainPanel(p2, c);

                //final frame setup
                mainPanel.setBackground(GroApplicationGUI.programColor);
                mainFrame = new JFrame("Add Plant");
                setUpFrame(mainFrame, mainPanel);

            }
        });
    }

    // EFFECTS: adds panel 2 to the main panel
    private void addPanelTwoToMainPanel(JPanel p2, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(p2, c);
    }

    // EFFECTS: adds panel 1 to the main panel
    private void addPanelOneToMainPanel(JPanel p1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(p1, c);
    }

    // EFFECTS: format panel two
    private void setUpPanelTwo(JPanel p2) {
        p2.setLayout(new GridLayout(1, 1));
        addPlantSubmitButton = new JButton("Add to Schedule");
        addPlantSubmitButton.addActionListener(new ClickButton());

        p2.setBackground(GroApplicationGUI.programColor);
        p2.add(addPlantSubmitButton);
    }

    // EFFECTS: format panel one
    private void setUpPanelOne(JPanel p1) {
        p1.setLayout(new GridLayout(1, 2));
        textFieldPlantName = new JTextField(" Type plant name here ");
        textFieldPlantName.requestFocusInWindow();
        textFieldPlantName.selectAll();

        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        listOfWeekDay = new JComboBox<>(weekDays);

        p1.add(textFieldPlantName);
        p1.add(listOfWeekDay);
    }

    // EFFECTS: set up the frame containing the panels
    private void setUpFrame(JFrame frame, JPanel mainPanel) {
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setBackground(GroApplicationGUI.programColor);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(GroApplicationGUI.logo);
        frame.setVisible(true);
    }

    // EFFECTS: detect button presses, triggering an action depending on the button pressed
    public class ClickButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent submitClicked) {

            if (addPlantSubmitButton == (submitClicked.getSource())) {
                String inputPlantName = textFieldPlantName.getText();
                String dayOfWeek = Objects.requireNonNull(listOfWeekDay.getSelectedItem()).toString();

                Component frame = new JFrame();
                JOptionPane.showMessageDialog(frame,
                        "Confirming " + inputPlantName + " to be watered on " + dayOfWeek + "!");

                try {
                    ManagerJson.writeNewEntryToJson(new Plant(inputPlantName, dayOfWeek),
                            Schedule.dayToIndex(dayOfWeek), "Schedule");
                } catch (InvalidDayException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
