package model;

import exceptions.InvalidDayException;
import persistence.ManagerJson;

import java.util.ArrayList;

/**
 * Schedule class stores entries
 **/
public class Schedule extends ManagerJson {

    // point of control, switch type here
    ArrayList<ArrayList<Plant>> listOfDays;

    // EFFECT: initialize each new schedule as an empty schedule, with each day containing the plants to be watered
    public Schedule() {
        listOfDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {

            //point of control, switch type here
            ArrayList<Plant> p = new ArrayList<>();
            listOfDays.add((p));
        }
    }

    // REQUIRES: an index from 0-6
    // EFFECTS: return the ArrayList of entries at the specified index
    // point of control
    public ArrayList<Plant> getDay(Integer i) {
        return listOfDays.get(i);
    }

    // MODIFIES: this
    // EFFECTS: Add a Plant to the watering schedule
    public void addEntry(String name, String day) throws InvalidDayException {

        String schedule = "schedule";
        Plant entry = new Plant(name, day);
        writeNewEntryToJson(entry, dayToIndex(day), schedule);

    }

    // EFFECTS: remove an entry from a day in the schedule
    public boolean removeEntry(String name) {

        String file = "schedule";
        return findAndRemoveEntryFromJson(name, file);

    }

    // EFFECTS: converts the user input of a day to it's respective index in Schedule
    public static Integer dayToIndex(String day) throws InvalidDayException {

        switch (day.toLowerCase()) {
            case "monday":
                return 0;
            case "tuesday":
                return 1;
            case "wednesday":
                return 2;
            case "thursday":
                return 3;
            case "friday":
                return 4;
            case "saturday":
                return 5;
            case "sunday":
                return 6;
            default:
                throw (new InvalidDayException());
        }
    }

    // Getter: Necessary for Jackson functionality
    // point of control, switch type here
    public ArrayList<ArrayList<Plant>> getListOfDays() {
        return listOfDays;
    }

    // Setter: Necessary for Jackson functionality
    // point of control
    public void setListOfDays(ArrayList<ArrayList<Plant>> listOfDays) {
        this.listOfDays = listOfDays;
    }
}