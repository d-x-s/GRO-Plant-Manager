package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidDayException;
import model.AddableScheduleEntry;
import model.Plant;
import model.Schedule;
import ui.frames.WelcomeNewUserFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GroApplication class is responsible for receiving user input, providing a user interface, and to call other
 * elements of the program as necessary
 **/
public class GroApplication extends Schedule {
    private static Schedule schedule;
    private Scanner input;
    Scanner reader = new Scanner(System.in);

    // EFFECTS: start the GRO application
    public GroApplication() {
        runGroApplication();
    }

    // MODIFIES: this
    // EFFECTS: read user input, allowing user to navigate program
    // SOURCE: this console UI functionality is adapted from the TellerApp class of the TellerApp project
    private void runGroApplication() {

        new WelcomeNewUserFrame();

        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            welcomeMessage();

            printInstructions();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Closing GRO!");
    }

    // EFFECTS: print console instructions
    private void printInstructions() {
        System.out.println("Instructions");
        System.out.println(" - Input 's' to access the plant watering schedule");
        System.out.println(" - Input 'f to access the fertilizer calculator");
        System.out.println(" - Input 'q' to quit the application");
    }

    // EFFECTS: welcome either a new user or a saved user
    public void welcomeMessage() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (objectMapper.readValue(new File("./data/firstTimeUser.json"), boolean.class)) {
                welcomeNewUserMessage();
            } else {
                welcomeSavedUserMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECT: Welcomes back the saved user
    public void welcomeSavedUserMessage() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String name = objectMapper.readValue(new File("./data/userName.json"), String.class);
            System.out.println("Welcome back to the GRO homepage, " + name + "!");
            initializeLoadStoredSchedule();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //EFFECT: Greet a new user and initialize a new schedule
    public void welcomeNewUserMessage() {
        Scanner reader = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println("Hi! Welcome to GRO plant management system. What's your name?");
            String name = reader.nextLine();
            objectMapper.writeValue(new File("./data/userName.json"), name);

            System.out.println("Hi " + name + ", nice to meet you! Press Enter to continue.");
            String blank = reader.nextLine();
            objectMapper.writeValue(new File("./data/firstTimeUser.json"), false);

            initializeNewSchedule();

        } catch (IOException e) {
            System.out.println("Error at welcome messsage");
            e.printStackTrace();
        }

    }

    //EFFECTS: Process the user input
    public void processCommand(String command) {
        if (command.equals("s")) {
            scheduleConsole();

        } else if (command.equals("f")) {
            System.out.println("Unfortunately, the calculator is currently under development. Come back another time.");

        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: Print out instructions for operating the water scheduler (upon opening the scheduler)
    public void welcomeWaterSchedulerMessage() {

        System.out.println("Welcome to the watering scheduler!");
        System.out.println("What would you like to do?");
        System.out.println(" - Input 'add' to add a new plant to the schedule!");
        System.out.println(" - Input 'remove' to remove a plant from the schedule!");
        System.out.println(" - Input 'view' to view the schedule!");
        System.out.println(" - Input 'quit' to return to home! ");
    }

    // EFFECTS: Print out the instructions for operating the water schedule, if user loops back to do more
    public void welcomeAgainWaterSchedulerMessage() {
        System.out.println("What else do you want to do?");
        System.out.println(" - Input 'add' to add a new plant to the schedule!");
        System.out.println(" - Input 'remove' to remove a plant from the schedule!");
        System.out.println(" - Input 'view' to view the schedule!");
        System.out.println(" - Input 'quit' to return to home! ");
    }

    //EFFECTS: Displays the actions the users can take in the scheduler
    public void scheduleConsole() {

        welcomeWaterSchedulerMessage();
        boolean keepGoing = false;

        while (true) {
            if (keepGoing) {
                welcomeAgainWaterSchedulerMessage();
            }

            String input = reader.nextLine();

            if (input.equals("add")) {
                addPlant();
                keepGoing = true;

            } else if (input.equals("remove")) {
                removePlant();
                keepGoing = true;

            } else if (input.equals("view")) {
                viewDayInSchedule();
                keepGoing = true;

            } else if (input.equals("quit")) {
                break;

            } else {
                System.out.println("Sorry, your input was not valid. Try again.");
                keepGoing = false;
            }
        }
    }

    //EFFECT: initialize a new schedule in the schedule.json, set firstTimeUser to false
    public static void initializeNewSchedule() {
        schedule = new Schedule();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("./data/schedule.json"), schedule);
            objectMapper.writeValue(new File("./data/firstTimeUser.json"), false);

        } catch (IOException e) {
            System.out.println("Error at initNewSchedule");
            e.printStackTrace();
        }

    }

    // EFFECT: load up the schedule stored within the JSON file
    public static void initializeLoadStoredSchedule() {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Loading...");
        try {
            schedule = objectMapper.readValue(new File("./data/schedule.json"), Schedule.class);
        } catch (IOException e) {
            System.out.println("Error at initLoadSchedule");
            e.printStackTrace();
        }

    }

    //EFFECT: adds a plant entry to the schedule
    public void addPlant() {
        Scanner reader = new Scanner(System.in);

        System.out.println("What is the name of the plant you are adding? Type the name of your plant: ");
        String nameOfPlant = reader.nextLine();
        System.out.println("On what day would you like to water this plant?");
        String dayToWater = reader.nextLine();

        try {
            schedule.addEntry(nameOfPlant, dayToWater);
            System.out.println("Successfully added " + nameOfPlant + " to the Schedule.");

        } catch (InvalidDayException e) {
            System.out.println("That is not a valid day! Please try again.");
            addPlant();
        }

    }

    //EFFECT: remove a plant entry from the schedule
    public void removePlant() {
        System.out.println("Search for? Type the name of your plant: ");
        Scanner reader = new Scanner(System.in);
        String nameOfPlant = reader.nextLine(); // the user inputs the name of the Plant to be removed

        if (removeEntry(nameOfPlant)) {
            System.out.println("Successfully removed " + nameOfPlant);
        } else {
            System.out.println("The plant " + nameOfPlant + " could not be found within the schedule!");
        }

    }

    //EFFECT: view a day within the schedule
    public void viewDayInSchedule() {

        System.out.println("What day do you want to view?");
        System.out.println("Type in a day of the week, choosing from: ");
        System.out.println("Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or Sunday");

        Scanner scanner = new Scanner(System.in);
        String day = scanner.nextLine();

        try {

            //Get the ArrayList<Plant> from the JSON, with index corresponding to day
            ArrayList<Plant> plantList = (ArrayList<Plant>) getListOfEntriesAtWeekDayFromJson(dayToIndex(day));

            //If the ArrayList<Plant> is empty, display warning, otherwise print all the plants in this plantList
            if (plantList.isEmpty()) {

                System.out.println("No plants to water on this day!");

            } else {

                System.out.println("You need to water: ");
                for (AddableScheduleEntry p : plantList) {
                    System.out.println("- " + p.getName());
                }
                System.out.println("");
            }

        } catch (InvalidDayException e) {
            System.out.println("That is not a valid day! Please try typing it again!");
            viewDayInSchedule();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



