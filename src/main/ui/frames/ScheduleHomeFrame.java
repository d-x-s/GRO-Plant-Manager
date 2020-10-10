/**
 * Archived for future reference
 */

//package ui.frames;
//
//import ui.GroApplicationGUI;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import javax.swing.border.Border;
//
///**
// * GUI for the plant watering schedule
// */
//
//public class ScheduleHomeFrame {
//    private static final boolean shouldFill = true;
//    private static final boolean shouldWeightX = true;
//    private static final boolean RIGHT_TO_LEFT = false;
//
//    JButton addPlantButton;
//    JButton removePlantButton;
//    JButton viewScheduleButton;
//    JButton quitScheduleButton;
//    Border raisedBevel;
//
//
//    public ScheduleHomeFrame() {
//        createAndShowGUI();
//    }
//
//    public void addComponentsToPane(Container pane) {
//        if (RIGHT_TO_LEFT) {
//            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//        }
//
//        pane.setLayout(new GridBagLayout());
//        pane.setBackground(GroApplicationGUI.programColor);
//        GridBagConstraints c = new GridBagConstraints();
//        if (shouldFill) {
//            //natural height, maximum width
//            c.fill = GridBagConstraints.HORIZONTAL;
//        }
//
//        // BUTTON: ADDING PLANTS
//        setUpAddPlantButton();
//
//        addPlantButton = new JButton("Add Plant");
//        // attach a listener
//        addPlantButton.addActionListener(new ClickButton());
//        // button style
//        raisedBevel = BorderFactory.createRaisedBevelBorder();
//        addPlantButton.setBorder(raisedBevel);
//        addPlantButton.setBackground(GroApplicationGUI.programColor);
//        // Grid layout formatting
//        if (shouldWeightX) {
//            c.weightx = 0.5;
//        }
//        c.gridx = 0;
//        c.gridy = 0;
//        pane.add(addPlantButton, c);
//
//        // BUTTON: REMOVING PLANTS
//        setUpRemovePlantButton();
//
//        removePlantButton = new JButton("Remove Plant");
//        //attach a listener
//        removePlantButton.addActionListener(new ClickButton());
//        // button style
//        raisedBevel = BorderFactory.createRaisedBevelBorder();
//        removePlantButton.setBorder(raisedBevel);
//        removePlantButton.setBackground(GroApplicationGUI.programColor);
//        // Grid layout formatting
//        c.gridx = 1;
//        c.gridy = 0;
//        pane.add(removePlantButton, c);
//
//        // BUTTON: VIEWING SCHEDULE
//        setUpViewScheduleButton();
//
//        viewScheduleButton = new JButton("View Schedule");
//        //attach a listener
//        viewScheduleButton.addActionListener(new ClickButton());
//        // button style
//        raisedBevel = BorderFactory.createRaisedBevelBorder();
//        viewScheduleButton.setBorder(raisedBevel);
//        viewScheduleButton.setBackground(GroApplicationGUI.programColor);
//        //Grid layout formatting
//        c.gridx = 2;
//        c.gridy = 0;
//        pane.add(viewScheduleButton, c);
//
//        // BUTTON: QUITTING SCHEDULER
//        setUpQuitScheduleButton();
//
//        quitScheduleButton = new JButton("Quit GRO");
//        //attach a listener
//        quitScheduleButton.addActionListener(new ClickButton());
//        // button style
//        raisedBevel = BorderFactory.createRaisedBevelBorder();
//        quitScheduleButton.setBorder(raisedBevel);
//        quitScheduleButton.setBackground(GroApplicationGUI.programColor);
//        //Grid layout formatting
//        c.ipady = 0;       //reset to default
//        c.weighty = 1.0;   //request any extra vertical space
//        c.anchor = GridBagConstraints.LAST_LINE_START; //bottom of space
//        c.insets = new Insets(10, 0, 0, 0);  //top padding
//        c.gridx = 0;       //aligned with button 2
//        c.gridwidth = 3;   //2 columns wide
//        c.gridy = 2;       //third row
//        pane.add(quitScheduleButton, c);
//
//        // text area to display schedule
//        JTextArea display = new JTextArea("");
//        c.ipady = 500;
//        c.weightx = 0.0;
//        c.gridwidth = 3;
//        c.gridx = 0;
//        c.gridy = 2;
//        display.setEditable(false);
//        pane.add(display, c);
//    }
//
//    private void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("GRO Watering Schedule");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Set up the content pane.
//        addComponentsToPane(frame.getContentPane());
//
//        //Display the window.
//        frame.pack();
//        frame.setSize(1000, 700);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    public class ClickButton implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent submitClicked) {
//
//            if (addPlantButton == (submitClicked.getSource())) {
//                Component frame = new JFrame();
//                JOptionPane.showMessageDialog(frame, "Clicked remove button");
//                new AddPlantFrame();
//
//            } else if (removePlantButton == (submitClicked.getSource())) {
//                Component frame = new JFrame();
//                JOptionPane.showMessageDialog(frame, "Clicked remove button");
//                new RemovePlantFrame();
//
//            } else if (viewScheduleButton == (submitClicked.getSource())) {
//                Component frame = new JFrame();
//                JOptionPane.showMessageDialog(frame, "Clicked view button");
//
//            } else if (quitScheduleButton == (submitClicked.getSource())) {
//                Component frame = new JFrame();
//                JOptionPane.showMessageDialog(frame, "Closing GRO!");
//                System.exit(0);
//            }
//        }
//    }
//}