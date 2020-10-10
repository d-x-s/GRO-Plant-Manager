package ui.frames;

import persistence.ManagerJson;
import ui.GroApplicationGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePlantFrame {

    private JFrame mainFrame;

    private JPanel mainPanel;

    private JLabel instructions;
    private JPanel p1; //contains the instructions
    private JPanel p2; //contains a search bar and submit button

    private JTextField textFieldPlantName;
    private JButton removePlantSubmitButton;

    // EFFECTS: constructor for new plant adder frame
    public RemovePlantFrame() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                //instantiate main panel
                mainPanel = new JPanel();
                mainPanel.setLayout(new GridBagLayout());
                mainPanel.setBackground(GroApplicationGUI.programColor);
                GridBagConstraints c = new GridBagConstraints();

                p1 = new JPanel();
                setUpPanelOne(p1);

                p2 = new JPanel();
                setUpPanelTwo(p2);

                addPanelOneToMainPanel(p1, c);

                addPanelTwoToMainPanel(p2, c);

                //final frame setup
                mainFrame = new JFrame("Remove Plant");
                setUpFrame(mainFrame, mainPanel);

            }
        });
    }

    // EFFECTS: setup the frame containing the panels
    private void setUpFrame(JFrame mainFrame, JPanel mainPanel) {
        mainFrame.setPreferredSize(new Dimension(400, 150));
        mainFrame.setBackground(GroApplicationGUI.programColor);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(GroApplicationGUI.logo);
        textFieldPlantName.requestFocusInWindow();
        mainFrame.setVisible(true);
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

    // EFFECTS: format panel 2
    private void setUpPanelTwo(JPanel p2) {
        textFieldPlantName = new JTextField(" Type plant name here ");
        textFieldPlantName.selectAll();

        removePlantSubmitButton = new JButton("Search");
        removePlantSubmitButton.addActionListener(new ClickButton());

        p2.setBackground(GroApplicationGUI.programColor);
        p2.add(textFieldPlantName);
        p2.add(removePlantSubmitButton);
    }

    // EFFECTS: format panel 1
    private void setUpPanelOne(JPanel p1) {
        instructions = new JLabel("Type the exact name of the plant you wish to remove.");
        p1.setBackground(GroApplicationGUI.programColor);
        p1.add(instructions);
    }

    // EFFECTS: detect button presses, triggering an action depending on the button pressed
    public class ClickButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent submitClicked) {

            if (removePlantSubmitButton == (submitClicked.getSource())) {
                String inputPlantName = textFieldPlantName.getText();

                if (ManagerJson.findAndRemoveEntryFromJson(inputPlantName, "Schedule")) {
                    Component frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "Confirming " + inputPlantName + " to be removed!");
                } else {
                    Component frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "The plant " + inputPlantName + " does not exist in the schedule!");
                }
            }
        }
    }
}
