package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.AddableScheduleEntry;
import model.Plant;
import model.Schedule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that contains the functionality necessary to read and save to a JSON file.
 */

public class ManagerJson {

    //EFFECT: Write a Plant object to the file, at it the ArrayList corresponding to day
    public static void writeNewEntryToJson(Plant entry, Integer day, String file) {

        ObjectMapper objectMapper = new ObjectMapper();
        Schedule schedule;

        try {
            // retrieve the schedule from the JSON
            schedule = objectMapper.readValue(new File("./data/" + file + ".json"), Schedule.class);

            // retrieve ArrayList corresponding to a day of the week, then add the new plant
            (schedule.getDay(day)).add(entry);

            // update the JSON with the newly modified schedule
            objectMapper.writeValue(new File("./data/schedule.json"), schedule);

        } catch (IOException e) {
            System.out.println("Error: The plant could not be added to the schedule!");
            e.printStackTrace();
        }
    }

    // EFFECT: Remove a plant object from the file it is stored in, returning true if successful; otherwise false
    public static boolean findAndRemoveEntryFromJson(String name, String file) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Schedule schedule = objectMapper.readValue(new File("./data/" + file + ".json"), Schedule.class);
            ArrayList<? extends AddableScheduleEntry> day;

            // loop over each day of the schedule, corresponding to indexes 0-6
            for (int i = 0; i < 7; i++) {

                day = (schedule.getDay(i));

                // loop over the list of plants, searching for the plant to be removed; if found, remove it
                for (AddableScheduleEntry p : day) {
                    if (p.getName().equals(name)) {
                        day.remove(p);

                        //update the schedule, now that the plant has been removed
                        objectMapper.writeValue(new File("./data/schedule.json"), schedule);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // EFFECT: retrieves the list of Plants at the specified day/index
    public ArrayList<? extends AddableScheduleEntry> getListOfEntriesAtWeekDayFromJson(int i) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Schedule schedule;

        schedule = objectMapper.readValue(new File("./data/schedule.json"), Schedule.class);
        return schedule.getDay(i);

    }

//    // EFFECT: clears the schedule
//    public void resetSchedule() {
//
//    }

//    //
//    // EFFECT: wipes all stored information in the program
//    public void resetProgram() {
//
//    }

}
